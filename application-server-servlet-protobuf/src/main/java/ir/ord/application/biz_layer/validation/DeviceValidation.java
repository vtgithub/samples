package ir.ord.application.biz_layer.validation;


import ir.ord.application.accessories.ValidationMessages;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/11/17.
 */
@ApplicationScoped
public class DeviceValidation {
    public List<String> sendDeviceNotifValidation(String sessionId, String pushToken) {
        List<String> validationResult = new ArrayList<String>();
        if (sessionId == null || sessionId.equals(""))
            validationResult.add(ValidationMessages.SessionIdEmpty);
        if (pushToken == null || pushToken.equals(""))
            validationResult.add(ValidationMessages.appDeviceTokenEmpty);
        return validationResult;
    }
}
