package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.Convertor.ButtonConvertor;
import ir.ord.application.ResponseStatus;
import ir.ord.application.accessories.*;
import ir.ord.application.biz_layer.validation.AccountValidation;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import ir.ord.application.Convertor.AccountInfoConvertor;
import ir.ord.application.dto.AccountInfoDto;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 4/22/17.
 */
@Stateless
//@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Transactional
public class AccountBiz {

//    @Inject
//    private CompositeSmsGatewayService compositeSmsGatewayService;
    @Inject
    private AccountValidation accountValidation;
    @Inject
    private ActivationDao activationDao;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private AccountInfoConvertor accountInfoConvertor;

    @Inject
    private PhoneLogDao phoneLogDao;
    @Inject
    private QRDao qrDao;
    @Inject
    private ButtonDao buttonDao;
    @Inject
    private ButtonConvertor buttonConvertor;


    public AccountInfoDto registerAccountInfo(AccountInfoDto accountInfoDto, String sessionId) throws CustomValidationException, DaoException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = accountValidation.registerAccountInfoValidation(accountInfoDto, sessionId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);

        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        AccountInfoEntity oldAccountInfoEntity =
                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());
        accountInfoEntity = accountInfoConvertor.dtoToEntity(accountInfoDto, accountInfoEntity);
        accountInfoEntity.setInfoRequired(false);
        if (accountInfoEntity.getAccountState() == AccountState.NEW_ACCOUNT.getCode())
            accountInfoEntity.setAccountState(AccountState.ACTIVE_ACCOUNT.getCode());

        final AccountInfoEntity finalAccountInfoEntity = accountInfoEntity;
        callableRowList.add(new CallableRow<AccountInfoEntity>(
                OperationEnum.UPDATE.getCode(),
                oldAccountInfoEntity.get_id(),
                oldAccountInfoEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        accountInfoDao.update(finalAccountInfoEntity.get_id(), finalAccountInfoEntity);
                        return null;
                    }
                }
        ));


        final DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());
        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendContentUpdate(
                        UpdateNotifType.ACCOUNT.getCode(),
                        UpdateNotifAccountEvent.UPDATE_INFO.getCode(),
                        UpdateNotifPriority.HIGHT.getCode(),
                        null,
                        devicePushToken
                );
            }
        });

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);

        return accountInfoConvertor.entityToDto(accountInfoEntity);
    }

    public Integer signIn(String phoneNumber, String deviceId) throws CustomValidationException, DaoException, InvalidAccountException, TooManyActivationCodeGenerated {
        List<String> validationResultList = accountValidation.signInValidation(phoneNumber, deviceId);
        if(validationResultList.size()!=0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        Long activationCodeCount = activationDao.getDailyUnusedActivationCodeCount(deviceId);
        if (activationCodeCount > 10)
            throw new TooManyActivationCodeGenerated(ResponseMessages.tooManyActivationCodeGenerated);
        String activationCode = Helper.getActivationCode();

//        AccountInfoEntity accountInfoEntity = accountInfoDao.getByPhoneNumber(phoneNumber);
//        if ((accountInfoEntity == null) || (accountInfoEntity.getAccountState() != AccountState.ACTIVE_ACCOUNT.getCode()) )
//            throw new InvalidAccountException();
        if(isInActivationWhiteList(phoneNumber, deviceId)){
            return ResponseStatus.OK.getCode();
        }
        ActivationEntity activationEntity = new ActivationEntity();
        activationEntity.setActivationCode(activationCode);
        activationEntity.setDeviceId(deviceId);
        activationEntity.setPhoneNumber(phoneNumber);
        activationEntity.setUsed(false);
        activationEntity.setCreationTime(System.currentTimeMillis());
        activationDao.save(activationEntity);
        Helper.Notification.sendPureSMS(
            phoneNumber,
            NotificationMessages.getActivationMessage(activationCode)
        );

        return ResponseStatus.OK.getCode();
    }

    public Map<String, String> activate(String activationCode,
                                        String deviceId,
                                        Integer platform,
                                        String platformVersion,
                                        String appVersion,
                                        String deviceName,
                                        String deviceModel,
                                        String ip) throws CustomValidationException, DaoException, ActivationCodeNotFoundException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();

        Map<String, String> responseObject = new HashMap<String, String>();
        List<String> validationResultList = accountValidation.activateValidation(activationCode, deviceId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        final ActivationEntity activationEntity = activationDao.get(activationCode, deviceId, false);

//        if ((activationEntity == null || activationEntity.getActivationCode() == null) )
//            throw new ActivationCodeNotFoundException();


        ActivationEntity oldActivationEntity =
                (ActivationEntity) DaoHelper.getNewInstanceFromExisting(activationEntity, activationEntity.getClass());
        activationEntity.setUsed(true);

        callableRowList.add(new CallableRow<ActivationEntity>(
                OperationEnum.UPDATE.getCode(),
                oldActivationEntity.get_id(),
                oldActivationEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        activationDao.update(activationEntity.get_id(), activationEntity);
                        return null;
                    }
                }
        ));


        AccountInfoEntity accountInfoEntity = accountInfoDao.getByPhoneNumber(activationEntity.getPhoneNumber());
        final PhoneLogEntity phoneLogEntity = new PhoneLogEntity();
        if (accountInfoEntity == null || accountInfoEntity.get_id() == null){
            accountInfoEntity = new AccountInfoEntity();
            accountInfoEntity.setAccountState(AccountState.NEW_ACCOUNT.getCode());
            accountInfoEntity.setPhoneNumber(activationEntity.getPhoneNumber());
            accountInfoEntity.setInfoRequired(true);

            final AccountInfoEntity finalAccountInfoEntity = accountInfoEntity;
            callableRowList.add(new CallableRow<AccountInfoEntity>(
                    OperationEnum.INSERT.getCode(),
                    accountInfoEntity.get_id(),
                    accountInfoEntity,
                    new Callable<Void>() {
                        public Void call() throws Exception {
                            accountInfoDao.save(finalAccountInfoEntity);
                            return null;
                        }
                    }
            ));
            responseObject.put("responseCode", String.valueOf(ResponseStatus.CREATED.getCode()));
            phoneLogEntity.setReason(PhoneLogReason.SIGNUP.getCode());
        }else if(accountInfoEntity.getInfoRequired() == true){
            responseObject.put("responseCode", String.valueOf(ResponseStatus.PARTIAL_CONTENT.getCode()));
            phoneLogEntity.setReason(PhoneLogReason.SIGNIN.getCode());
        }else{
            responseObject.put("responseCode", String.valueOf(ResponseStatus.OK.getCode()));
            phoneLogEntity.setReason(PhoneLogReason.SIGNIN.getCode());
        }

        phoneLogEntity.setPhoneNumber(accountInfoEntity.getPhoneNumber());
        phoneLogEntity.setAccountId(accountInfoEntity.get_id());
        phoneLogEntity.setActivationDate(Helper.getCurrentTime());
        callableRowList.add(new CallableRow<PhoneLogEntity>(
                OperationEnum.INSERT.getCode(),
                phoneLogEntity.get_id(),
                phoneLogEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        phoneLogDao.save(phoneLogEntity);
                        return null;
                    }
                }
        ));

//        SessionInfoEntity sessionInfoEntity = sessionInfoDao.get(deviceId, accountInfoEntity.getAccountId());
        List<SessionInfoEntity> sessionInfoEntityList = sessionInfoDao.getActiveSessionsByDeviceId(deviceId);
        previouseSessionListDeactivator(sessionInfoEntityList, callableRowList);

        final SessionInfoEntity sessionInfoEntity = new SessionInfoEntity();
        sessionInfoEntity.setAccountId(accountInfoEntity.get_id());
        sessionInfoEntity.setDeviceId(deviceId);
        sessionInfoEntity.setPlatform(platform);
        sessionInfoEntity.setPlatformVersion(platformVersion);
        sessionInfoEntity.setAppVersion(appVersion);
        sessionInfoEntity.setDeviceName(deviceName);
        sessionInfoEntity.setDeviceModel(deviceModel);
        sessionInfoEntity.setActivationTime(Helper.getCurrentTime());
        sessionInfoEntity.setIp(ip);

        callableRowList.add(new CallableRow<SessionInfoEntity>(
                OperationEnum.INSERT.getCode(),
                sessionInfoEntity.get_id(),
                sessionInfoEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        sessionInfoDao.save(sessionInfoEntity);
                        return null;
                    }
                }
        ));

        responseObject.put("sessionId", sessionInfoEntity.get_id());

        Helper.callCallableList(callableRowList);

        return responseObject;
    }



    public AccountInfoDto getAccountInfo(String sessionId) throws DaoException {
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        AccountInfoDto accountInfoDto = accountInfoConvertor.entityToDto(accountInfoEntity);
        return accountInfoDto;

    }

    public Integer changePhoneNumber(String sessionId, String phoneNumber) throws CustomValidationException, DaoException, PhoneNumberAlreadyRegisteredException {
        List<String> validationResultList = accountValidation.changePhoneNumberValidation(sessionId, phoneNumber);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ","));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getByPhoneNumber(phoneNumber);
        if (accountInfoEntity != null)
            throw new PhoneNumberAlreadyRegisteredException();
        String activationCode = Helper.getActivationCode();
        ActivationEntity activationEntity = new ActivationEntity();
        activationEntity.setActivationCode(activationCode);
        activationEntity.setDeviceId(sessionInfoEntity.getDeviceId());
        activationEntity.setPhoneNumber(phoneNumber);
        activationEntity.setUsed(false);
        //TODO check time locality
        activationEntity.setCreationTime(Helper.getCurrentTime());
        activationDao.save(activationEntity);

//        SendSmsResponse sendSmsResponse =
         Helper.Notification.sendPureSMS(
                phoneNumber,
                NotificationMessages.getActivationMessage(activationCode)
        );

        return Helper.getActivationCodeTimeOut();

    }

    public void changePhoneActivation(String sessionId, String activationCode) throws CustomValidationException, DaoException, ActivationCodeNotFoundException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = accountValidation.changePhoneActivationvalidation(sessionId, activationCode);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        final ActivationEntity activationEntity = activationDao.get(activationCode, sessionInfoEntity.getDeviceId(), false);
        ActivationEntity oldActivationEntity =
                (ActivationEntity) DaoHelper.getNewInstanceFromExisting(activationEntity, activationEntity.getClass());
        if (activationEntity == null)
            throw new ActivationCodeNotFoundException();
        activationEntity.setUsed(true);

        callableRowList.add(new CallableRow<ActivationEntity>(
                OperationEnum.UPDATE.getCode(),
                oldActivationEntity.get_id(),
                oldActivationEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        activationDao.update(activationEntity.get_id(), activationEntity);
                        return null;
                    }
                }
        ));


        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        AccountInfoEntity oldAccountInfoEntity =
                (AccountInfoEntity) DaoHelper.getNewInstanceFromExisting(accountInfoEntity, accountInfoEntity.getClass());

        final PhoneLogEntity phoneLogEntity = new PhoneLogEntity();
        phoneLogEntity.setPhoneNumber(activationEntity.getPhoneNumber());
        phoneLogEntity.setAccountId(accountInfoEntity.get_id());
        phoneLogEntity.setActivationDate(Helper.getCurrentTime());
        phoneLogEntity.setReason(PhoneLogReason.CHANGE_NUMBER.getCode());


        callableRowList.add(new CallableRow<PhoneLogEntity>(
                OperationEnum.INSERT.getCode(),
                phoneLogEntity.get_id(),
                phoneLogEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        phoneLogDao.save(phoneLogEntity);
                        return null;
                    }
                }
        ));

        accountInfoEntity.setPhoneNumber(activationEntity.getPhoneNumber());

        callableRowList.add(new CallableRow<AccountInfoEntity>(
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

//        SendSmsResponse sendSmsResponse =
        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendPureSMS(
                        activationEntity.getPhoneNumber(),
                        NotificationMessages.getChangePhoneNumber(accountInfoEntity.getFirstName(), accountInfoEntity.getLastName())
                );
            }
        });

        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());
        Helper.Notification.sendContentUpdate(
                UpdateNotifType.ACCOUNT.getCode(),
                UpdateNotifAccountEvent.UPDATE_PHONE_NUMBER.getCode(),
                UpdateNotifPriority.HIGHT.getCode(),
                null,
                devicePushToken
        );

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);

    }

    public Map<String, Object> getQRCode(String sessionId) throws DaoException {

        QREntity qrEntity = qrDao.getBySessionId(sessionId, false, Helper.getCurrentTime() - Helper.getQRCodeTTL()+30000);

        if (qrEntity == null) {
            qrEntity = new QREntity();
            qrEntity.setCode(Helper.getQRCode());
            qrEntity.setCreationTime(Helper.getCurrentTime());
            qrEntity.setEncryptedCode(Helper.AES.encrypt(Helper.AES.getKey(sessionId), Helper.AES.initVector, qrEntity.getCode()));
            qrEntity.setSessionId(sessionId);
            qrDao.save(qrEntity);
        }

        Map<String, Object> responseData = new HashMap<String, Object>();
        responseData.put("qrCode", qrEntity.getCode());
        responseData.put(
                "expirationTime",
                qrEntity.getCreationTime() + Helper.getQRCodeTTL()
        );

        return responseData;
    }

    public String signUpWithQRCode(String deviceId,
                                   String encryptedQRCode,
                                   Integer platform,
                                   String platformVersion,
                                   String appVersion,
                                   String deviceName,
                                   String deviceModel) throws CustomValidationException, DaoException, NoQRCodeException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> resultList = accountValidation.signUpWithQRCode(deviceId, encryptedQRCode);
        if (resultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(resultList, ","));
        String base64Encrypted = Helper.AES.decrypt(Helper.AES.getKey(deviceId), Helper.AES.initVector, encryptedQRCode);
        base64Encrypted = Helper.AES.realContent(base64Encrypted);
        final QREntity qrEntity = qrDao.getByEncryptedCode(
                base64Encrypted,
                false,
                Helper.getCurrentTime() - Helper.getQRCodeTTL()
        );

        if (qrEntity == null)
            throw new NoQRCodeException();
        QREntity oldQREntity = (QREntity) DaoHelper.getNewInstanceFromExisting(qrEntity, qrEntity.getClass());

        qrEntity.setUsed(true);

        callableRowList.add(new CallableRow<QREntity>(
                OperationEnum.UPDATE.getCode(),
                oldQREntity.get_id(),
                oldQREntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        qrDao.update(qrEntity.get_id(), qrEntity);
                        return null;
                    }
                }
        ));

        SessionInfoEntity app1SessionInfoEntity = sessionInfoDao.getById(qrEntity.getSessionId());
        Set<String> pushTokenSet = new HashSet<String>();
        pushTokenSet.add(app1SessionInfoEntity.getPushToken());
        final DevicePushToken devicePushToken = new DevicePushToken();
        devicePushToken.setPushTokenSet(pushTokenSet);
        devicePushToken.setPlatform(app1SessionInfoEntity.getPlatform());
        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendContentUpdate(
                        UpdateNotifType.ACCOUNT.getCode(),
                        UpdateNotifAccountEvent.QR_CODE_USED.getCode(),
                        UpdateNotifPriority.HIGHT.getCode(),
                        null,
                        devicePushToken//sessionInfoDao.getPushTokenList(app1SessionInfoEntity.getAccountId())
                );
            }
        });

        SessionInfoEntity previousDeviceSessionInfo = sessionInfoDao.getById(qrEntity.getSessionId());
        List<SessionInfoEntity> sessionInfoEntityList = sessionInfoDao.getActiveSessionsByDeviceId(deviceId);
        previouseSessionListDeactivator(sessionInfoEntityList, callableRowList);
        final SessionInfoEntity sessionInfoEntity = new SessionInfoEntity();
        sessionInfoEntity.setAccountId(previousDeviceSessionInfo.getAccountId());
        sessionInfoEntity.setActive(true);
        sessionInfoEntity.setDeviceId(deviceId);
        sessionInfoEntity.setPlatform(platform);
        sessionInfoEntity.setPlatformVersion(platformVersion);
        sessionInfoEntity.setAppVersion(appVersion);
        sessionInfoEntity.setDeviceName(deviceName);
        sessionInfoEntity.setDeviceModel(deviceModel);
        sessionInfoEntity.setActivationTime(Helper.getCurrentTime());
        sessionInfoEntity.setLastUpdate(Helper.getCurrentTime());

        callableRowList.add(new CallableRow<SessionInfoEntity>(
                OperationEnum.INSERT.getCode(),
                sessionInfoEntity.get_id(),
                sessionInfoEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        sessionInfoDao.save(sessionInfoEntity);
                        return null;
                    }
                }
        ));

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);
        return sessionInfoEntity.get_id();
    }

//    public List<ButtonDto> getButtonList(String sessionId) throws DaoException {
//        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
//        List<ButtonEntity> buttonEntityList = buttonDao.getListByAccountId(sessionInfoEntity.getAccountId());
//        List<ButtonDto> buttonDtoList = buttonConvertor.getDtoList(buttonEntityList);
//        return buttonDtoList;
//    }


    //--------------------
    private void previouseSessionListDeactivator(List<SessionInfoEntity> sessionInfoEntityList, List<CallableRow> callableRowList) throws DaoException {
        if (sessionInfoEntityList != null && sessionInfoEntityList.size() > 0){
            for (final SessionInfoEntity sessionInfoEntity:sessionInfoEntityList){
                SessionInfoEntity oldSessionInfoEntity = (SessionInfoEntity) DaoHelper.getNewInstanceFromExisting(sessionInfoEntity, sessionInfoEntity.getClass());
                sessionInfoEntity.setActive(false);
                callableRowList.add(new CallableRow<SessionInfoEntity>(
                        OperationEnum.UPDATE.getCode(),
                        sessionInfoEntity.get_id(),
                        oldSessionInfoEntity,
                        new Callable<Void>() {
                            public Void call() throws Exception {
                                sessionInfoDao.update(sessionInfoEntity.get_id(), sessionInfoEntity);
                                return null;
                            }
                        }
                ));
            }
        }
    }

    public boolean isInActivationWhiteList(String phoneNumber, String deviceId) {
        List<ActivationEntity> activationEntityList = DaoHelper.getActivationWhiteList();
        for (ActivationEntity activationEntity : activationEntityList) {
            if ( activationEntity.getPhoneNumber().trim().equals(phoneNumber) && activationEntity.getDeviceId().trim().equals(deviceId)){
                return true;
            }
        }
        return false;
    }



}
