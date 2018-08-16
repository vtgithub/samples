package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dto.AccountInfoDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 4/24/17.
 */
@ApplicationScoped
public class AccountValidation {
    public List<String> signUpValidation(String phoneNumber, String deviceId){
        List<String> validationResult = new ArrayList<String>();
        if (phoneNumber == null || phoneNumber.equals("")){
            validationResult.add(ValidationMessages.phoneNumberEmpty);
        }else if(!phoneNumber.matches("^09\\d{9}$")){
            validationResult.add(ValidationMessages.phoneNumberIsNotCorrect);
        }
        if (deviceId == null || deviceId.equals("")){
            validationResult.add(ValidationMessages.deviceIdEmpty);
        }
        return validationResult;
    }

    public List<String> activateValidation(String activationCode, String deviceId){
        List<String> validationResult = new ArrayList<String>();
        if (activationCode == null || activationCode.equals("")){
            validationResult.add(ValidationMessages.activationCodeEmpty);
        }
        if (deviceId == null || deviceId.equals("")){
            validationResult.add(ValidationMessages.deviceIdEmpty);
        }
        return validationResult;
    }

    public List<String> registerAccountInfoValidation(AccountInfoDto accountInfoDto,String sessionId){
        List<String> validationResult = new ArrayList<String>();
        if (sessionId == null || sessionId.equals(""))
            validationResult.add(ValidationMessages.SessionIdEmpty);
        if(accountInfoDto.getFirstName() == null || accountInfoDto.getFirstName().equals("")){
            validationResult.add(ValidationMessages.firstNameEmpty);
        }

        if(accountInfoDto.getLastName() == null || accountInfoDto.getLastName().equals("")){
            validationResult.add(ValidationMessages.lastNameEmpty);
        }
//        if (accountInfoDto.getPhoneNumber() == null || accountInfoDto.getPhoneNumber().equals("")){
//            validationResult.add(ValidationMessages.phoneNumberEmpty);
//        }else if(!accountInfoDto.getPhoneNumber().matches("^09\\d{9}$")){
//            validationResult.add(ValidationMessages.phoneNumberIsNotCorrect);
//        }

        return validationResult;
    }

    public List<String> signInValidation(String phoneNumber, String deviceId) {
         return signUpValidation(phoneNumber, deviceId);
    }

    public List<String> changePhoneNumberValidation(String sessionId, String phoneNumber) {
        List<String> validationResult = new ArrayList<String>();
        if (sessionId == null || sessionId.equals(""))
            validationResult.add(ValidationMessages.SessionIdEmpty);
        if (phoneNumber == null || phoneNumber.equals(""))
            validationResult.add(ValidationMessages.phoneNumberEmpty);
        else if(!phoneNumber.matches("^09\\d{9}$"))
            validationResult.add(ValidationMessages.phoneNumberIsNotCorrect);

        return validationResult;
    }

    public List<String> changePhoneActivationvalidation(String sessionId, String activationCode) {
        List<String> validationResult = new ArrayList<String>();
        if (activationCode == null || activationCode.equals("")){
            validationResult.add(ValidationMessages.activationCodeEmpty);
        }
        if (sessionId == null || sessionId.equals("")){
            validationResult.add(ValidationMessages.SessionIdEmpty);
        }
        return validationResult;
    }

    public List<String> signUpWithQRCode(String deviceId, String encryptedQRCode) {
        List<String> validationResult = new ArrayList<String>();
        if (deviceId == null || deviceId.equals(""))
            validationResult.add(ValidationMessages.deviceIdEmpty);
        if (encryptedQRCode == null ||  encryptedQRCode.equals(""))
            validationResult.add(ValidationMessages.QRCodeEmpty);
        return validationResult;
    }
}
