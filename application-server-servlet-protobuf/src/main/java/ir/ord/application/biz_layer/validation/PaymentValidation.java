package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dal.entities.SuccessPaymentObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/8/17.
 */
@ApplicationScoped
public class PaymentValidation {
    public List<String> paymentInitializeValidation(String sessionId, Long amount, String specificOrderId) {
        List<String> validationResult = new ArrayList<String>();
        if( (specificOrderId == null || specificOrderId.equals("")) && (amount == null || amount == 0) )
            validationResult.add(ValidationMessages.amountAndOrderEmpty);
        return validationResult;
    }

    public List<String> processRedirectResponsevalidation(SuccessPaymentObject successPaymentObject) {
        List<String> validationResult = new ArrayList<String>();
        if (successPaymentObject.getToken() == null || successPaymentObject.getToken().equals(""))
            validationResult.add(ValidationMessages.bankTokenEmpty);
        if (successPaymentObject.getOrderId() == null || successPaymentObject.getOrderId().equals(""))
            validationResult.add(ValidationMessages.bankOrderIdEmpty);
        if (successPaymentObject.getResCode() == null || successPaymentObject.getResCode() == -1)
            validationResult.add(ValidationMessages.bankResponseCodeInvalid);
        return validationResult;
    }
}
