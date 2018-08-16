package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.ValidationMessages;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/2/17.
 */
@ApplicationScoped
public class WalletValidation {

    public List<String> chargeWalletvalidation(String sessionId, Double amount) {
        List<String> validationResult = new ArrayList<String>();
        if(sessionId == null || sessionId.equals(""))
            validationResult.add(ValidationMessages.SessionIdEmpty);
        if(amount == null)
            validationResult.add(ValidationMessages.amountEmpty);
        return validationResult;
    }
    public List<String> walletReportValidation(String sessionId){
        List<String> validationResultList = new ArrayList<String>();
        if(sessionId == null || sessionId.equals(""))
            validationResultList.add(ValidationMessages.SessionIdEmpty);
        return validationResultList;
    }

    public List<String> giftChargeWalletValidation(String sessionId, String code) {
        List<String> validationResult = new ArrayList<String>();
        if(sessionId == null || sessionId.equals(""))
            validationResult.add(ValidationMessages.SessionIdEmpty);
        if (code == null || code.equals(""))
            validationResult.add(ValidationMessages.chargeCodeEmpty);
        return validationResult;
    }
}
