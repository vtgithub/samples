package ir.ord.management.biz_layer.validation;

import ir.ord.management.accessories.ValidationMessages;
import ir.ord.management.dto.protoes.CategoryProto;
import ir.ord.management.dto.protoes.GiftProto;
import ir.ord.management.dto.protoes.PackageProto;
import ir.ord.management.dto.protoes.TimePeriodProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.jsp.tagext.ValidationMessage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@ApplicationScoped
public class ManagementValidation {

    @Inject
    private TimePeriodValidation timePeriodValidation;

    public List<String> addCategoryValidation(CategoryProto.CategoryRequest.Builder categoryRequestBuilder) {
        List<String> validationResult = new ArrayList<String>();
        if(categoryRequestBuilder.getName() == null || categoryRequestBuilder.getName().equals(""))
            validationResult.add(ValidationMessages.categoryNameEmpty);
        return validationResult;
    }

    public List<String> addPackagevalidation(PackageProto.PackageRequest.Builder packageRequestBuilder) {
        List<String> validationResult = new ArrayList<String>();
        if(packageRequestBuilder.getName() == null || packageRequestBuilder.getName().equals(""))
            validationResult.add(ValidationMessages.packageNameEmpty);
        if(packageRequestBuilder.getPrice() <0)
            validationResult.add(ValidationMessages.invalidPrice);
        if(packageRequestBuilder.getCategoryId() == null || packageRequestBuilder.getCategoryId().equals(""))
            validationResult.add(ValidationMessages.packageIdEmpty);
        return validationResult;
    }

    public List<String> addTimePeriodValiadtion(TimePeriodProto.TimePeriodRequest.Builder timePeriodRequestBuilder) {
        return  timePeriodValidation.getValidatioinresult(timePeriodRequestBuilder);
    }

    public List<String> addGiftValidation(GiftProto.GiftRequest.Builder giftRequestBuilder) {
        List<String> validationResult = new ArrayList<String>();
        if (giftRequestBuilder.getValue() <= 0)
            validationResult.add(ValidationMessages.invalidPrice);
        if (giftRequestBuilder.getAccountIdListList() == null || giftRequestBuilder.getAccountIdListList().size() == 0)
            validationResult.add(ValidationMessages.gifAccountListEmpty);
        return validationResult;
    }
}
