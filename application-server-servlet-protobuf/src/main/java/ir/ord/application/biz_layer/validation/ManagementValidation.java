package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dto.CategoryDto;
import ir.ord.application.dto.PackageDto;
import ir.ord.application.dto.TimePeriodDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
@ApplicationScoped
public class ManagementValidation {

    @Inject
    private TimePeriodValidation timePeriodValidation;

    public List<String> addCategoryValidation(CategoryDto categoryDto) {
        List<String> validationResult = new ArrayList<String>();
        if(categoryDto.getName() == null || categoryDto.getName().equals(""))
            validationResult.add(ValidationMessages.categoryNameEmpty);
        return validationResult;
    }

    public List<String> addPackagevalidation(PackageDto packageDto) {
        List<String> validationResult = new ArrayList<String>();
        if(packageDto.getName() == null || packageDto.getName().equals(""))
            validationResult.add(ValidationMessages.packageNameEmpty);
        if(packageDto.getPrice() == null || packageDto.getPrice() <0)
            validationResult.add(ValidationMessages.invalidPrice);
        if(packageDto.getCategoryId() == null || packageDto.getCategoryId().equals(""))
            validationResult.add(ValidationMessages.packageIdEmpty);
        return validationResult;
    }

    public List<String> addTimePeriodValiadtion(TimePeriodDto timePeriodDto) {
        return  timePeriodValidation.getValidatioinresult(timePeriodDto);
    }
}
