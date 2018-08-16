package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.AddressConvertor;
import ir.ord.application.Convertor.ButtonConvertor;
import ir.ord.application.Convertor.TimePeriodConvertor;
import ir.ord.application.UpdateNotifAddressEvent;
import ir.ord.application.UpdateNotifPriority;
import ir.ord.application.UpdateNotifType;
import ir.ord.application.accessories.Helper;
import ir.ord.application.biz_layer.validation.AddressValidation;
import ir.ord.application.dal.dao.AccountInfoDao;
import ir.ord.application.dal.dao.ButtonDao;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.SessionInfoDao;
import ir.ord.application.dal.entities.*;
import ir.ord.application.dto.AddressDto;
import ir.ord.application.dto.ButtonDto;
import ir.ord.application.dto.TimePeriodDto;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by vahid on 5/1/17.
 */
@Stateless
@Transactional
public class AddressBiz {

    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private ButtonDao buttonDao;
    @Inject
    private AddressConvertor addressConvertor;
    @Inject
    private AddressValidation addressValidation;
    @Inject
    private TimePeriodConvertor timePeriodConvertor;
    @Inject
    private ButtonConvertor buttonConvertor;

    public AddressDto registerAddress(AddressDto addressDto, String sessionId) throws CustomValidationException, DaoException {
        List<String> validationResultList = addressValidation.registerAddressValidation(addressDto, sessionId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());

        List<AddressObject> addressObjectList = accountInfoEntity.getAddressObjectList();
        if (addressObjectList == null || addressObjectList.size() == 0)
            addressObjectList = new ArrayList<AddressObject>();
        AddressObject addressObject = addressConvertor.getEntity(addressDto);
        // for returning object
        addressDto.setId(addressObject.get_id());
        addressObjectList.add(addressObject);
        accountInfoEntity.setAddressObjectList(addressObjectList);

        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);

        DevicePushToken devicePushToken =  sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

        Helper.Notification.sendContentUpdate(
                UpdateNotifType.ADDRESS.getCode(),
                UpdateNotifAddressEvent.INSERT.getCode(),
                UpdateNotifPriority.HIGHT.getCode(),
                addressObject.get_id(),
                devicePushToken
        );
        return addressDto;
    }

    public AddressDto changeAddress(AddressDto addressDto, String sessionId) throws CustomValidationException, DaoException {
        List<String> validationResultList = addressValidation.registerAddressValidation(addressDto, sessionId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());

        List<AddressObject> addressObjectList = accountInfoEntity.getAddressObjectList();
        if (addressObjectList != null && addressObjectList.size() != 0){
            for (int i = 0; i < addressObjectList.size() ; i++){
                if (addressObjectList.get(i).get_id().equals(addressDto.getId())){
                    AddressObject addressObject = addressConvertor.getEntity(addressDto);
                    addressObject.setTimePeriodEntityList(addressObjectList.get(i).getTimePeriodEntityList());
                    addressObjectList.set(i , addressObject);
                }
            }
        }
        accountInfoEntity.setAddressObjectList(addressObjectList);
        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);
        AddressObject addressObject = accountInfoDao.getAddress(accountInfoEntity.get_id(), addressDto.getId());
        addressDto = addressConvertor.getDto(addressObject);

        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

        Helper.Notification.sendContentUpdate(
                UpdateNotifType.ADDRESS.getCode(),
                UpdateNotifAddressEvent.UPDATE.getCode(),
                UpdateNotifPriority.HIGHT.getCode(),
                addressDto.getId(),
                devicePushToken
        );
        return addressDto;
    }

    public List<AddressDto> getAllAddressOfAccount(String sessionId) throws DaoException {
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        List<AddressDto> addressDtoList = addressConvertor.getDtoList(accountInfoEntity.getAddressObjectList());
        return addressDtoList;
    }

    public AddressDto changeTimePeriodList(String sessionId, String addressID, List<TimePeriodDto> timePeriodDtoList) throws CustomValidationException, DaoException {
        List<String> validationResultList = addressValidation.changeTimePeriodList(addressID, timePeriodDtoList);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        AddressDto addressDto = null;
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        List<AddressObject> addressObjectList = accountInfoEntity.getAddressObjectList();
        if (addressObjectList != null && addressObjectList.size() != 0){
            for (int i = 0; i < addressObjectList.size() ; i++){
                if (addressObjectList.get(i).get_id().equals(addressID)){
                    AddressObject addressObject = addressObjectList.get(i);
                    addressObject.setTimePeriodEntityList(
                            timePeriodConvertor.getTimePeriodEntityList(timePeriodDtoList)
                    );
                    addressDto = addressConvertor.getDto(addressObject);
                    addressObjectList.set(i, addressObject);
                }
            }
        }
        accountInfoEntity.setAddressObjectList(addressObjectList);
        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);

        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

        Helper.Notification.sendContentUpdate(
                UpdateNotifType.ADDRESS.getCode(),
                UpdateNotifAddressEvent.TIME_PERIOD_UPDATE.getCode(),
                UpdateNotifPriority.HIGHT.getCode(),
                addressID,
                devicePushToken
        );

        return addressDto;
    }

    public AddressDto getAccountAddressById(String sessionId, String addressId) throws CustomValidationException, DaoException {
        List<String> validationResultList = addressValidation.getAccountAddressByIdValidation(addressId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        List<AddressObject> addressObjectList = accountInfoEntity.getAddressObjectList();
        if (addressObjectList != null && addressObjectList.size() != 0){
            for (AddressObject addressObject : addressObjectList) {
                if (addressObject.get_id().equals(addressId.trim())){
                    AddressDto addressDto = addressConvertor.getDto(addressObject);
                    return addressDto;
                }
            }
        }
        return null;
    }

    public void deleteAddressById(String sessionId, String addressId) throws CustomValidationException, DaoException {
        List<String> validationResultList = addressValidation.deleteAddressById(addressId);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());

        List<AddressObject> addressObjectList = accountInfoEntity.getAddressObjectList();
        if (addressObjectList != null && addressObjectList.size() != 0){
            for (int i = 0; i < addressObjectList.size() ; i++){
                if (addressObjectList.get(i).get_id().equals(addressId)){
                    addressObjectList.remove(i);
                }
            }
        }
        accountInfoEntity.setAddressObjectList(addressObjectList);
        accountInfoDao.update(accountInfoEntity.get_id(), accountInfoEntity);


        DevicePushToken devicePushToken = sessionInfoDao.getDevicePushToken(accountInfoEntity.get_id());

        Helper.Notification.sendContentUpdate(
                UpdateNotifType.ADDRESS.getCode(),
                UpdateNotifAddressEvent.DELETE.getCode(),
                UpdateNotifPriority.HIGHT.getCode(),
                addressId,
                devicePushToken
        );
    }

    public List<ButtonDto> getAllAddressButtons(String sessionId, String addressID) throws CustomValidationException, DaoException {
        List<String> validationResultList = addressValidation.getAllAddressButtonsValidation(addressID);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList,", "));

        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        List<ButtonEntity> buttonEntityList = buttonDao.getListByAccountAndAddressId(sessionInfoEntity.getAccountId(), addressID);

        List<ButtonDto> buttonDtoList = buttonConvertor.getDtoList(buttonEntityList);
        return buttonDtoList;
    }
}
