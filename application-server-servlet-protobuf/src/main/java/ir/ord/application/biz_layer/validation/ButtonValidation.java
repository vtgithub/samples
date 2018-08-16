package ir.ord.application.biz_layer.validation;


import ir.ord.application.accessories.ValidationMessages;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 5/1/17.
 */
@ApplicationScoped
public class ButtonValidation {

    @Inject
    private AddressValidation addressValidation;

    public List<String> buyButtonValidation(String addressId, String sessionId, String packageId) {
        List<String> validationresult = new ArrayList<String>();
        if(addressId == null || addressId.equals(""))
            validationresult.add(ValidationMessages.addressEmpty);
        if (sessionId == null || sessionId.equals(""))
            validationresult.add(ValidationMessages.SessionIdEmpty);
        if (packageId == null || packageId.equals(""))
            validationresult.add(ValidationMessages.PackageIdEmpty);
        return validationresult;
    }

    public List<String> getActivationCodeValidation(String name, String sessionId, String deviceToken, Map<Integer, String> funcMap) {
        List<String> validationResult = getPackagesValidation(sessionId, deviceToken);
        if (name == null || name.equals(""))
            validationResult.add(ValidationMessages.buttonNameEmpty);
        if(funcMap == null )
            validationResult.add(ValidationMessages.funcMapEmpty);
        return validationResult;
    }

    public List<String> getPackagesValidation(String sessionId, String deviceToken) {
        List<String> validationResult = new ArrayList<String>();
        if(sessionId == null || sessionId.equals(""))
            validationResult.add(ValidationMessages.SessionIdEmpty);
        if (deviceToken == null || deviceToken.equals(""))
            validationResult.add(ValidationMessages.deviceTokenEmpty);
        return validationResult;
    }

    public List<String> activateButtonValidation(String deviceToken, String activationCode) {
        List<String> validationResult = new ArrayList<String>();
        if (deviceToken == null || deviceToken.equals(""))
            validationResult.add(ValidationMessages.deviceTokenEmpty);
        if (activationCode == null || activationCode.equals(""))
            validationResult.add(ValidationMessages.activationCodeEmpty);
        return validationResult;
    }

    public List<String> buttonRequestvalidation(byte[] body) {
        List<String> validationResult = new ArrayList<String>();
        String bodyStr =  new String(body);
        if(body == null || bodyStr == null || bodyStr.equals(""))
            validationResult.add(ValidationMessages.buttonBodyEmpty);
        return validationResult;
    }

    public List<String> getFunctionalityvalidation(String deviceToken) {
        List<String> validationResult = new ArrayList<String>();
        if(deviceToken == null || deviceToken.trim().equals(""))
            validationResult.add(ValidationMessages.deviceTokenEmpty);
        return validationResult;
    }

}
