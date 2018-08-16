package ir.ord.application.biz_layer.validation;


import ir.ord.application.accessories.ValidationMessages;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 9/12/17.
 */
@ApplicationScoped
public class SessionValidation {
    public List<String> deactivateValidation(String secondaryId){
        List<String> result = new ArrayList<String>();
        if (secondaryId == null || secondaryId.equals(""))
            result.add(ValidationMessages.secondarySessionIdEmpty);
        return result;
    }
}
