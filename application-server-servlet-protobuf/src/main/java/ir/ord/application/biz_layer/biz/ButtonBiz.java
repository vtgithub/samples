package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.Convertor.AddressConvertor;
import ir.ord.application.Convertor.ButtonConvertor;
import ir.ord.application.Convertor.PackageConvertor;
import ir.ord.application.Convertor.StatusObjectConvertor;
import ir.ord.application.accessories.*;
import ir.ord.application.biz_layer.validation.ButtonValidation;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import ir.ord.application.dal.entities.source_entities.AppSourceEntity;
import ir.ord.application.dal.entities.source_entities.ButtonSourceEntity;
import ir.ord.application.dto.ButtonDto;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 5/1/17.
 */
@Stateless
@Transactional
public class ButtonBiz {

    @Inject
    private ButtonValidation buttonValidation;
    @Inject
    private CreditDao creditDao;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private PackageDao packageDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private ButtonDao buttonDao;
    @Inject
    private ButtonActivationDao buttonActivationDao;
    @Inject
    private UnpaiedDao unpaiedDao;
    @Inject
    private CategoryDao categoryDao;
    @Inject
    private AddressConvertor addressConvertor;
    @Inject
    private PackageConvertor packageConvertor;
    @Inject
    private ButtonConvertor buttonConvertor;
    @Inject
    private StatusObjectConvertor statusObjectConvertor;
    @Inject
    private OrderBiz orderBiz;
    @Inject
    private WalletBiz walletBiz;

    public void buyButton(String addressId,
                          String sessionId,
                          String packageId,
                          String appVersion,
                          String appVersionCode,
                          String devicePlatform,
                          String deviceSdk,
                          String deviceName,
                          String page,
                          String lat,
                          String lng,
                          Map<Integer, String> funcNumberCatIdMap) throws CustomValidationException,DaoException, NotEnoghMonyException {
        List<CallableRow> callableList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = buttonValidation.buyButtonValidation(
                addressId, sessionId, packageId
        );
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        PackageEntity packageEntityOfButton = packageDao.getById(packageId);

        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        Double balanceResult = creditDao.getBalanceSum(sessionInfoEntity.getAccountId()) - packageEntityOfButton.getPrice();

        if (balanceResult < 0)
            throw new NotEnoghMonyException();

        // callable insert

        AccountInfoEntity oldAccountInfoEntity =
                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());

        accountInfoEntity.setBalance(balanceResult);
        // callable update account info

        final OrderEntity orderEntity = new OrderEntity();
        orderEntity.setAccountId(accountInfoEntity.get_id());
        AddressObject addressObject = accountInfoDao.getAddressByEntity(accountInfoEntity, addressId);
        orderEntity.setAddressObject(addressObject);

        orderEntity.setDemandTime(Helper.getCurrentTime());

        orderEntity.setPackageEntity(packageEntityOfButton);
        orderEntity.setSourceType(SourceTypeEnum.APP.getCode());

        List<StatusObject> statusObjectList = new ArrayList<StatusObject>();
        StatusObject statusObject = new StatusObject();
        statusObject.setDescription(StatusMessages.buttonBought);
        statusObject.setState(ButtonState.BOUGHT.getCode());
        statusObject.setTimeStamp(Helper.getCurrentTime());
        statusObject.setActor(ActorEnum.USER.getCode());
        statusObjectList.add(statusObject);

        orderEntity.setStatusObjectList(statusObjectList);
        orderEntity.setCurrentStatus(statusObjectConvertor.getCurrentStatusObject(statusObjectList));
        AppSourceEntity appSourceEntity = getAppSourceEntity(
                appVersion,appVersionCode,deviceName,Integer.parseInt(devicePlatform),deviceSdk,page,lat,lng);
        orderEntity.setAppSourceEntity(appSourceEntity);

        List<TimePeriodObject> timePeriodObjectList = new ArrayList<TimePeriodObject>();

        TimePeriodObject timePeriodObject = getTimePeriodListAndMakeProperTimePeriodObject(
                addressObject.getTimePeriodEntityList()
        );
        timePeriodObjectList.add(timePeriodObject);
//        orderEntity.setTimePeriodObjectList(timePeriodObjectList);
         // callable insert order



        final ButtonEntity buttonEntity = new ButtonEntity();
        buttonEntity.setAccountId(accountInfoEntity.get_id());
        List<FunctionalityObject> functionalityObjectList =
                getFunctionalityObjectListFromButtonFunctionalityMap(funcNumberCatIdMap);
        buttonEntity.setFunctionalityObjectList(functionalityObjectList);
        List<StatusObject> buttonStatusObjectList = new ArrayList<StatusObject>();
        StatusObject buttonStatusObject = getStatusObject(ButtonState.BOUGHT.getCode(),Helper.getCurrentTime(),ActorEnum.USER.getCode() );
        buttonStatusObjectList.add(buttonStatusObject);
        buttonEntity.setStateList(buttonStatusObjectList);

        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

        walletBiz.insertCreditRecord(
                callableList,
                notifRunnableList,
                -1 * packageEntityOfButton.getPrice(),
                balanceResult,
                sessionInfoEntity.getAccountId(),
                packageEntityOfButton.getName(),
                null,
                orderEntity.get_id(),
                UpdateNotifCreditEvent.ORDER.getCode(),
                devicePushToken
        );

        callableList.add(new CallableRow<AccountInfoEntity>(
                OperationEnum.UPDATE.getCode(),
                accountInfoEntity.get_id(),
                oldAccountInfoEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);
                        return null;
                    }
                }
        ));

        orderBiz.orderInsert(callableList, notifRunnableList, orderEntity, devicePushToken);


        callableList.add(new CallableRow<ButtonEntity>(
                OperationEnum.INSERT.getCode(),
                buttonEntity.get_id(),
                buttonEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        buttonDao.save(buttonEntity);
                        return null;
                    }
                }
        ));

        Helper.callCallableList(callableList);
        Helper.runRunnableList(notifRunnableList);

    }


    public String getActivationCode(
            String name,
            String sessionId,
            String deviceToken,
            String ipAddress, Map<Integer, String> funcNumberPackageIdMap) throws CustomValidationException, DaoException {
        List<CallableRow> callableList = new ArrayList<CallableRow>();

        List<String>  validationResultList = buttonValidation.getActivationCodeValidation(
                name, sessionId, deviceToken, funcNumberPackageIdMap
        );
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList , ","));

        final ButtonEntity buttonEntity = buttonDao.getByDeviceToken(deviceToken);
        ButtonEntity oldButtonEntity  = (ButtonEntity) DaoHelper.getNewInstanceFromExisting(buttonEntity, buttonEntity.getClass());
        buttonEntity.setName(name);

        callableList.add(new CallableRow<ButtonEntity>(
                OperationEnum.UPDATE.getCode(),
                oldButtonEntity.get_id(),
                oldButtonEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        buttonDao.update(buttonEntity.get_id(), buttonEntity);
                        return null;
                    }
                }
        ));

        String activationCode = Helper.getActivationCode();
        Map<Integer, Functionality> functionalityMap = generateFunctionalityMapFromFunctionalityObjectList(
                buttonEntity.getFunctionalityObjectList(), funcNumberPackageIdMap
        );
        final ButtonActivationEntity buttonActivationEntity = new ButtonActivationEntity();

        buttonActivationEntity.setCreationTime(Helper.getCurrentTime());
        buttonActivationEntity.setUsed(false);
        buttonActivationEntity.setActivatorIP(ipAddress);
        buttonActivationEntity.setDeviceToken(deviceToken);
        buttonActivationEntity.setActivationCode(activationCode);

        buttonActivationEntity.setFunctionalityMap(functionalityMap);

        callableList.add(new CallableRow<ButtonActivationEntity>(
                OperationEnum.INSERT.getCode(),
                buttonActivationEntity.get_id(),
                buttonActivationEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        buttonActivationDao.save(buttonActivationEntity);
                        return null;
                    }
                }
        ));

        Helper.callCallableList(callableList);

        return activationCode;
    }


    public Map<Integer, String> activateButton(String deviceToken, String activationCode) throws CustomValidationException, DaoException {

        List<CallableRow> callableList = new ArrayList<CallableRow>();

        List<String> validationResult = buttonValidation.activateButtonValidation(deviceToken, activationCode);
        if (validationResult.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResult, ", "));
        final ButtonActivationEntity buttonActivationEntity = buttonActivationDao.get(
                deviceToken, activationCode, false);

        ButtonActivationEntity oldButtonActivationEntity =
                (ButtonActivationEntity) DaoHelper.getNewInstanceFromExisting(buttonActivationEntity, buttonActivationEntity.getClass());

        buttonActivationEntity.setUsed(true);

        callableList.add(new CallableRow<ButtonActivationEntity>(
                OperationEnum.UPDATE.getCode(),
                oldButtonActivationEntity.get_id(),
                oldButtonActivationEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        buttonActivationDao.update(buttonActivationEntity.get_id(), buttonActivationEntity);
                        return null;
                    }
                 }
                )
         );


        final ButtonEntity buttonEntity = buttonDao.getByDeviceToken(deviceToken);
        ButtonEntity oldButtonEntity = (ButtonEntity) DaoHelper.getNewInstanceFromExisting(buttonEntity, buttonEntity.getClass());
        // get functionality from buttonActivation and set to entity and returned Map
        List<FunctionalityObject> functionalityObjectList = buttonEntity.getFunctionalityObjectList();

        Map<Integer, String> funcNumberFuncKeyMap = updateFunctionalityObjectListAndGenerateFuncNumberFuncKeyMap(
                functionalityObjectList, buttonActivationEntity.getFunctionalityMap()
        );

        buttonEntity.setFunctionalityObjectList(functionalityObjectList);
        // change status of button
        List<StatusObject> buttonStatusObjectList = buttonEntity.getStateList();
        if (buttonStatusObjectList == null)
            buttonStatusObjectList = new ArrayList<StatusObject>();

        buttonStatusObjectList.add(
                getStatusObject(ButtonState.ACTIVATED.getCode(), Helper.getCurrentTime(), ActorEnum.USER.getCode())
        );
        buttonEntity.setStateList(buttonStatusObjectList);
        //TODO get from User
        buttonEntity.setNumOfFuncs((byte) buttonEntity.getFunctionalityObjectList().size());

        callableList.add(new CallableRow<ButtonEntity>(
                OperationEnum.UPDATE.getCode(),
                oldButtonEntity.get_id(),
                oldButtonEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        buttonDao.update(buttonEntity.get_id(), buttonEntity);
                        return null;
                    }
                }

        ));

        Helper.callCallableList(callableList);

        return funcNumberFuncKeyMap;
    }


    public void proccessButtonRequest(
            String deviceToken,
            String deviceVersion,
            String appId,
            String reqKey,
            String ip,
            byte[] body) throws CustomValidationException, DaoException, NotEnoghMonyException {
        List<CallableRow> callableList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = buttonValidation.buttonRequestvalidation(body);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
            //TODO decrypt body
        String funcKey = new String(body);
        ButtonEntity buttonEntity = buttonDao.getByDeviceToken(deviceToken);
        String packageId = getPackageIdByFuncKey(buttonEntity.getFunctionalityObjectList(), funcKey);
        PackageEntity packageEntity = packageDao.getById(packageId);

        final OrderEntity orderEntity = new OrderEntity();
        ButtonSourceEntity buttonSourceEntity = new ButtonSourceEntity();
        buttonSourceEntity.setOrdButton(buttonEntity);
        buttonSourceEntity.setFuncKey(funcKey);
        buttonSourceEntity.setButtonVersion(deviceVersion);
        buttonSourceEntity.setIp(ip);

        orderEntity.setButtonSourceEntity(buttonSourceEntity);
        orderEntity.setAccountId(buttonEntity.getAccountId());
        orderEntity.setSourceType(SourceTypeEnum.BUTTON.getCode());
        orderEntity.setPackageEntity(packageEntity);
//        orderEntity.setFuncKey(funcKey);
        orderEntity.setDeviceToken(deviceToken);
        orderEntity.setDemandTime(Helper.getCurrentTime());
        AddressObject addressObject = accountInfoDao.getAddress(buttonEntity.getAccountId(), buttonEntity.getAddressId());
        orderEntity.setAddressObject(addressObject);
//        orderEntity.setTimePeriodObjectList();
        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(buttonEntity.getAccountId());
        AccountInfoEntity oldAccountInfoEntity =
                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());
        StatusObject requestedStatusObject = getStatusObject(CommodityState.REQUESTED.getCode(), Helper.getCurrentTime(), ActorEnum.USER.getCode());
        StatusObject statusObject = new StatusObject();
        statusObject.setActor(ActorEnum.BUTTON.getCode());
        List<StatusObject> statusObjectList = new ArrayList<StatusObject>();
        statusObjectList.add(requestedStatusObject);
        statusObjectList.add(statusObject);
        orderEntity.setStatusObjectList(statusObjectList);
        orderEntity.setCurrentStatus(statusObjectConvertor.getCurrentStatusObject(statusObjectList));



        //--- get pushToken info
        final DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendPureSMS(
                        accountInfoEntity.getPhoneNumber(),
                        NotificationMessages.getSuccessOrderMessage(orderEntity.getPackageEntity().getName(), orderEntity.get_id())
                );
                Helper.Notification.sendNotif(
                        NotificationMessages.successOrderTitle,
                        "",
                        NotificationMessages.getSuccessOrderMessage(orderEntity.getPackageEntity().getName(), orderEntity.get_id()),
                        devicePushToken
                );

            }
        });

        statusObject.setTimeStamp(Helper.getCurrentTime());
        if (accountInfoEntity.getBalance() >= packageEntity.getPrice()) {
            statusObject.setState(CommodityState.PAID.getCode());
            Double newBalance = creditDao.getBalanceSum(accountInfoEntity.get_id()) - packageEntity.getPrice();

            walletBiz.insertCreditRecord(
                    callableList,
                    notifRunnableList,
                    -1*packageEntity.getPrice(),
                    newBalance,
                    buttonEntity.getAccountId(),
                    packageEntity.getName(),
                    null,
                    orderEntity.get_id(),
                    UpdateNotifCreditEvent.ORDER.getCode(),
                    devicePushToken
            );

            accountInfoEntity.setBalance(newBalance);
            callableList.add(new CallableRow<AccountInfoEntity>(
                    OperationEnum.UPDATE.getCode(),
                    oldAccountInfoEntity.get_id(),
                    oldAccountInfoEntity,
                    new Callable<Void>() {
                        public Void call() throws Exception {
                            accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);
                            return null;
                        }
                    }
            ));
            //--------------- delivery time
            DeliveryEstimateObject currentDeliveryEstimateObject = getDeliveryEstimateObject(addressObject.getTimePeriodEntityList());
            orderEntity.setCurrentDeliveryEstimate(currentDeliveryEstimateObject);
            List<DeliveryEstimateObject> deliveryEstimateObjectList = new ArrayList<DeliveryEstimateObject>();
            deliveryEstimateObjectList.add(currentDeliveryEstimateObject);
            orderEntity.setDeliveryEstimateObjectList(deliveryEstimateObjectList);
        }
        else{
            statusObject.setState(CommodityState.UNPAID.getCode());
            final UnpaiedEntity unpaiedEntity = getUnpaiedEntity(
                    orderEntity.get_id(),
                    orderEntity.getAccountId(),
                    orderEntity.getPackageEntity().getPrice(),
                    orderEntity.getDemandTime(),
                    orderEntity.getPackageEntity().getName()
            );

            callableList.add(new CallableRow<UnpaiedEntity>(
                    OperationEnum.INSERT.getCode(),
                    unpaiedEntity.get_id(),
                    unpaiedEntity,
                    new Callable<Void>() {
                        public Void call() throws Exception {
                            unpaiedDao.save(unpaiedEntity);
                            return null;
                        }
                    }
            ));

            notifRunnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendGlobalNotificationToUserAfterSeconds(
                            Helper.getNotifWaiteTime(),
                            NotificationMessages.creditLowTitle,
                            "",
                            NotificationMessages.getCreditLowMessage(orderEntity.getPackageEntity().getName(), unpaiedEntity.getOrderId(), 24),
                            devicePushToken
                    );
                    Helper.Notification.sendPureSMSAfterSeconds(
                            Helper.getNotifWaiteTime(),
                            accountInfoEntity.getPhoneNumber(),
                            NotificationMessages.getCreditLowMessage(orderEntity.getPackageEntity().getName(), unpaiedEntity.getOrderId(), 24)
                    );
                }
            });

        }

        orderBiz.orderInsert(callableList, notifRunnableList, orderEntity, devicePushToken);

        Helper.callCallableList(callableList);
        Helper.runRunnableList(notifRunnableList);
    }

    public Map<Integer, String> getFunctionalityMap(String deviceToken) throws CustomValidationException, DaoException {
        List<String> validationResultList = buttonValidation.getFunctionalityvalidation(deviceToken);
        if (validationResultList.size()!=0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        ButtonEntity buttonEntity = buttonDao.getByDeviceToken(deviceToken);
        Map<Integer, String> functionalityMap =getFunctionalityMapFromFunctionalityObjectlist(
                buttonEntity.getFunctionalityObjectList()
        );
        return functionalityMap;
    }


    public List<ButtonDto> getButtonList(String sessionId) throws DaoException {
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        List<ButtonEntity> buttonEntityList = buttonDao.getListByAccountId(sessionInfoEntity.getAccountId());
        List<ButtonDto>  buttonDtoList = buttonConvertor.getDtoList(buttonEntityList);
        setCatName(buttonDtoList);
        return buttonDtoList;
    }


    // -----------helper functions


    private TimePeriodObject getTimePeriodListAndMakeProperTimePeriodObject(List<TimePeriodEntity> timePeriodEntityList) {
        TimePeriodObject timePeriodObject = new TimePeriodObject();
        // TODO select proper timePeriodEntity
        return timePeriodObject;
    }


    private List<FunctionalityObject> getFunctionalityObjectListFromButtonFunctionalityMap(
            Map<Integer, String> FuncNumberCatIdMap) {
        if (FuncNumberCatIdMap == null)
            return null;
        List<FunctionalityObject> functionalityObjectList = new ArrayList<FunctionalityObject>();
        FunctionalityObject functionalityObject;
        for( Map.Entry<Integer, String> entry:FuncNumberCatIdMap.entrySet()){
            functionalityObject = new FunctionalityObject();
            functionalityObject.setFuncNumber(entry.getKey());
            functionalityObject.setCatId(entry.getValue());
            functionalityObjectList.add(functionalityObject);
        }

        return functionalityObjectList;
    }

    private Map<Integer, String> getFunctionalityMapFromFunctionalityObjectlist(List<FunctionalityObject> functionalityObjectList) {
        if (functionalityObjectList == null)
            return null;
        Map<Integer, String> functionalityMap = new HashMap<Integer, String>();
        for (FunctionalityObject functionalityObject:functionalityObjectList){
            functionalityMap.put(functionalityObject.getFuncNumber(), functionalityObject.getCatId());
        }
        return functionalityMap;
    }


    private String getPackageIdByFuncKey(List<FunctionalityObject> functionalityObjectList, String funcKey) {
        if (functionalityObjectList == null)
            return null;
        for (FunctionalityObject functionalityObject: functionalityObjectList){
            if(functionalityObject.getFunctionalityList()!=null){
                Functionality functionality = functionalityObject.getFunctionalityList().get(functionalityObject.getFunctionalityList().size() - 1);
                if (functionality.getFuncKey().trim().equals(funcKey.trim()))
                    return functionality.getPackageId();
            }
        }
        return null;
    }



    private UnpaiedEntity getUnpaiedEntity(String orderId , String accountId, Double price, Long demandTime, String packageName) throws DaoException {
        UnpaiedEntity unpaiedEntity = new UnpaiedEntity();
        unpaiedEntity.setOrderId(orderId);
        unpaiedEntity.setAccountId(accountId);
        unpaiedEntity.setPrice(price);
        unpaiedEntity.setTimeStamp(demandTime);
        unpaiedEntity.setPackageName(packageName);
        return unpaiedEntity;
    }

    private Map<Integer, String> updateFunctionalityObjectListAndGenerateFuncNumberFuncKeyMap(
            List<FunctionalityObject> functionalityObjectList, Map<Integer, Functionality> functionalityMap) {
        Map<Integer, String> funcNumberFuncKeyMap = new HashMap<Integer, String>();
        for(int i = 0; i < functionalityObjectList.size(); i++){
            Functionality functionality = getNewFunctionalityFromFunctionality(
                    functionalityMap.get(functionalityObjectList.get(i).getFuncNumber())
            );
            funcNumberFuncKeyMap.put(functionalityObjectList.get(i).getFuncNumber(), functionality.getFuncKey());
            List<Functionality> functionalityObjectFunctionalityList = functionalityObjectList.get(i).getFunctionalityList();
            if (functionalityObjectFunctionalityList == null)
                functionalityObjectFunctionalityList = new ArrayList<Functionality>();
            functionalityObjectFunctionalityList.add(functionality);
            functionalityObjectList.get(i).setFunctionalityList(functionalityObjectFunctionalityList);
            functionalityObjectList.set(i, functionalityObjectList.get(i));
        }
        return funcNumberFuncKeyMap;

    }


    private Map<Integer, Functionality> generateFunctionalityMapFromFunctionalityObjectList(
            List<FunctionalityObject> functionalityObjectList, Map<Integer, String> funcNumberPackageIdMap) {

        if (functionalityObjectList == null || funcNumberPackageIdMap == null)
            return null;
        Map<Integer, Functionality> functionalityMap = new HashMap<Integer, Functionality>();
        for (FunctionalityObject functionalityObject: functionalityObjectList){
            Functionality functionality = new Functionality();
            functionality.setTimeStamp(Helper.getCurrentTime());
            functionality.setPackageId(funcNumberPackageIdMap.get(functionalityObject.getFuncNumber()));
            functionality.setFuncKey(Helper.getUUID());
            functionalityMap.put(functionalityObject.getFuncNumber() ,functionality);
        }

        return functionalityMap;
    }



    private Functionality getNewFunctionalityFromFunctionality(Functionality functionality) {
        Functionality newFunctionality = new Functionality();
        newFunctionality.setFuncKey(functionality.getFuncKey());
        newFunctionality.setPackageId(functionality.getPackageId());
        newFunctionality.setTimeStamp(Helper.getCurrentTime());
        return newFunctionality;
    }


    private void setCatName(List<ButtonDto> buttonDtoList) throws DaoException {
        for (int i=0;i<buttonDtoList.size();i++) {
            for (int j=0;j<buttonDtoList.get(i).getFunctionalityObjectDtoList().size();j++) {
                    CategoryEntity categoryEntity = categoryDao.findNonButtonById(
                            buttonDtoList.get(i).getFunctionalityObjectDtoList().get(j).getCatId()
                    );
                    buttonDtoList.get(i).getFunctionalityObjectDtoList().get(j).setCatName(categoryEntity.getName());
            }
        }

    }

    private StatusObject getStatusObject(Integer state, Long currentTime, Integer actor) {
        StatusObject statusObject = new StatusObject();
        if (state != null)
            statusObject.setState(state);
        if (currentTime != null)
            statusObject.setTimeStamp(Helper.getCurrentTime());
        if (actor != null)
            statusObject.setActor(actor);
        return statusObject;
    }


    private AppSourceEntity getAppSourceEntity(String appVersion, String appVersionCode, String deviceName, Integer devicePlatform, String deviceSdk, String page, String lat, String lng) {
        AppSourceEntity appSourceEntity = new AppSourceEntity();
        if (page != null && !page.equals(""))
            appSourceEntity.setPage(page);
        appSourceEntity.setDeviceName(deviceName);
        appSourceEntity.setDeviceSDK(deviceSdk);
        appSourceEntity.setAppVersion(appVersion);
        appSourceEntity.setAppVersionCode(appVersionCode);
        appSourceEntity.setDevicePlatform(devicePlatform);
        LocationObject locationObject = new LocationObject();
        locationObject.setLatitude(Double.parseDouble(lat));
        locationObject.setLongitude(Double.parseDouble(lng));
        appSourceEntity.setLocation(locationObject);
        return appSourceEntity;
    }

 //-----------------------------------------------
    public DeliveryEstimateObject getDeliveryEstimateObject(List<TimePeriodEntity> timePeriodEntityList) {
        DeliveryEstimateObject deliveryEstimateObject = new DeliveryEstimateObject();
        deliveryEstimateObject.setActor(ActorEnum.SYSTEM.getCode());
        deliveryEstimateObject.setTimeStamp(Helper.getCurrentTime());
        CustomDate currentCustomDate = Helper.Calendar.getCurrentCustomDate();
        currentCustomDate.setHour((byte) (currentCustomDate.getHour() + Helper.getSystemProccessHour()));
        // make 2D array from timePeriodEntityList
        List<List<TimePeriodEntity>> timePeriodEntityListList = new ArrayList<List<TimePeriodEntity>>();
        for (int i = 0; i < 7 ; i++){
            timePeriodEntityListList.add(new ArrayList<TimePeriodEntity>());
        }
        for (TimePeriodEntity timePeriodEntity : timePeriodEntityList) {
            timePeriodEntityListList.get(timePeriodEntity.getWeekDay()).add(timePeriodEntity);
        }
        // get best time period entity
        Long currentDayBaseTimeMillies = Helper.Calendar.getCurrentDayBaseTimeMillies();
        byte dow = currentCustomDate.getDayOfWeek();
        int dayCount = 0;
        do{
            for (TimePeriodEntity timePeriodEntity : timePeriodEntityListList.get(dow)) {
                if ( (dow == currentCustomDate.getDayOfWeek()) && (timePeriodEntity.getFromTime() > currentCustomDate.getHour()) ){
                    deliveryEstimateObject.setDeliveryTimeMin(
                            currentDayBaseTimeMillies + dayCount*Helper.getOneDayMiliSeconds() + timePeriodEntity.getFromTime()*Helper.getOneHourMiliSeconds()
                    );
                    deliveryEstimateObject.setDeliveryTimeMax(
                            currentDayBaseTimeMillies + dayCount*Helper.getOneDayMiliSeconds() + timePeriodEntity.getToTime()*Helper.getOneHourMiliSeconds()
                    );
                    return deliveryEstimateObject;
                }else if (dow != currentCustomDate.getDayOfWeek()){
                    deliveryEstimateObject.setDeliveryTimeMin(
                            currentDayBaseTimeMillies + dayCount*Helper.getOneDayMiliSeconds() + timePeriodEntity.getFromTime()*Helper.getOneHourMiliSeconds()
                    );
                    deliveryEstimateObject.setDeliveryTimeMax(
                            currentDayBaseTimeMillies + dayCount*Helper.getOneDayMiliSeconds() + timePeriodEntity.getToTime()*Helper.getOneHourMiliSeconds()
                    );
                    return deliveryEstimateObject;
                }
            }
            dayCount = (dayCount + 1)%7;
            dow = (byte) ((dow + 1)%7);
        }while (dow != currentCustomDate.getDayOfWeek() );
        if (timePeriodEntityListList.get(currentCustomDate.getDayOfWeek()).size() != 0){
            TimePeriodEntity timePeriodEntity = timePeriodEntityListList.get(currentCustomDate.getDayOfWeek()).get(0);
            deliveryEstimateObject.setDeliveryTimeMin(Long.valueOf(timePeriodEntity.getFromTime()));
            deliveryEstimateObject.setDeliveryTimeMax(Long.valueOf(timePeriodEntity.getToTime()));
            return deliveryEstimateObject;
        }
        return null;
    }
}
