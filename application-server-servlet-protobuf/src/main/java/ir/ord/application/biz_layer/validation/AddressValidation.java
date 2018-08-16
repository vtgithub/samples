package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dto.AddressDto;
import ir.ord.application.dto.TimePeriodDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/1/17.
 */
@ApplicationScoped
public class AddressValidation {

    public List<String> registerAddressValidation(AddressDto addressDto, String sessionId){
        List<String> validationResultList = new ArrayList<String>();
        if (sessionId == null || sessionId.equals("")){
            validationResultList.add(ValidationMessages.SessionIdEmpty);
        }

        if (addressDto.getLocation() == null){
            validationResultList.add(ValidationMessages.addressEmpty);
        }

        return validationResultList;
    }

    public List<String> changeTimePeriodList(String addressID, List<TimePeriodDto> timePeriodDtoList) {
        List<String> resultList = getAccountAddressByIdValidation(addressID);
        if (timePeriodDtoList == null || timePeriodDtoList.size() == 0){
            resultList.add(ValidationMessages.timePeriodListEmpty);
        }
        return resultList;
    }

    public List<String> getAccountAddressByIdValidation(String addressID) {
        List<String> resultList = new ArrayList<String>();
        if (addressID == null || addressID.equals("")){
            resultList.add(ValidationMessages.addressEmpty);
        }
        return resultList;
    }

    public List<String> deleteAddressById(String addressId) {
        return getAccountAddressByIdValidation(addressId);
    }

    public List<String> getAllAddressButtonsValidation(String addressID) {
        List<String> validationResult = new ArrayList<String>();
        if (addressID == null || addressID.equals(""))
            validationResult.add(ValidationMessages.addressEmpty);
        return validationResult;
    }
}
