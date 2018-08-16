package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dto.GiftDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class GiftValidation {

    public List<String> addGiftValidation(GiftDto giftDto) {
        List<String> validationresult = new ArrayList<String>();
//        if (giftDto.getCode() == null || giftDto.getCode().equals(""))
//            validationresult.add(ValidationMessages.giftCodeEmpty);
        if (giftDto.getValue() == null || giftDto.getValue() <= 0)
            validationresult.add(ValidationMessages.giftValueEmpty);
//        if (giftDto.getAccountId() == null || giftDto.getAccountId().equals(""))
//            validationresult.add(ValidationMessages.accountEmpty);
        if (giftDto.getIncludeOrExclude() == null)
            validationresult.add(ValidationMessages.includeOrExclueNull);
        if (giftDto.getExpirationTime() == null || giftDto.getExpirationTime() < Helper.getCurrentTime())
            validationresult.add(ValidationMessages.invalidExpirationDate);

        return validationresult;
    }
}
