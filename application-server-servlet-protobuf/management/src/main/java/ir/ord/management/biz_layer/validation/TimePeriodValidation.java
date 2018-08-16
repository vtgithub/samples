package ir.ord.management.biz_layer.validation;

import ir.ord.management.accessories.ValidationMessages;
import ir.ord.management.dto.protoes.TimePeriodProto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class TimePeriodValidation {

    List<String> getValidatioinresult(TimePeriodProto.TimePeriodRequest.Builder timePeriodRequestBuilder){
        List<String> validationResult = new ArrayList<String>();
        if (timePeriodRequestBuilder.getWeekDay()<0 || timePeriodRequestBuilder.getWeekDay()>7)
            validationResult.add(ValidationMessages.wrongWeekDay);
        if ( timePeriodRequestBuilder.getFromTime() <= 0 || timePeriodRequestBuilder.getFromTime() >86400)
            validationResult.add(ValidationMessages.wrongFromTime);
        if (timePeriodRequestBuilder.getToTime() <= 0 || timePeriodRequestBuilder.getToTime() >86400)
            validationResult.add(ValidationMessages.wrongToTime);
        if (timePeriodRequestBuilder.getFromTime() > timePeriodRequestBuilder.getToTime())
            validationResult.add(ValidationMessages.wrongTimePeriod);

        return validationResult;

    }
}
