package ir.ord.application.biz_layer.validation;

import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.dto.TimePeriodDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class TimePeriodValidation {

    List<String> getValidatioinresult(TimePeriodDto timePeriodDto){
        List<String> validationResult = new ArrayList<String>();
        if (timePeriodDto.getWeekDay() == null || timePeriodDto.getWeekDay()<1 || timePeriodDto.getWeekDay()>7)
            validationResult.add(ValidationMessages.wrongWeekDay);
        if (timePeriodDto.getFromTime() == null || timePeriodDto.getFromTime() < 0 || timePeriodDto.getFromTime() >86400)
            validationResult.add(ValidationMessages.wrongFromTime);
        if (timePeriodDto.getToTime() == null || timePeriodDto.getToTime() < 0 || timePeriodDto.getToTime() >86400)
            validationResult.add(ValidationMessages.wrongToTime);
        if (timePeriodDto.getFromTime() > timePeriodDto.getToTime())
            validationResult.add(ValidationMessages.wrongTimePeriod);

        return validationResult;

    }
}
