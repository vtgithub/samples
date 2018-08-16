package ir.ord.application.Convertor;

import ir.ord.application.dto.OrderFeedbackDto;
import ir.ord.application.dto.protoes.OrderProto;
import ir.ord.application.dto.protoes.ParameterProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 10/3/17.
 */
@ApplicationScoped
public class OrderFeedbackConverter {

//    @Inject
//    FeedBackConverter feedBackConverter;

    public OrderFeedbackDto getDtoFromBuilder(OrderProto.OrderFeedback.Builder builder){
        if (builder == null)
            return null;
        OrderFeedbackDto orderFeedbackDto = new OrderFeedbackDto();
        ParameterProto.FeedbackObject deliveryFeedback = builder.getDeliveryFeedback();
        orderFeedbackDto.setDeliveryFeedback(FeedBackConverter.getDtoFromBuilder(builder.getDeliveryFeedback().toBuilder()));
        orderFeedbackDto.setPackingFeedback(FeedBackConverter.getDtoFromBuilder(builder.getPackingFeedback().toBuilder()));
        orderFeedbackDto.setProductFeedback(FeedBackConverter.getDtoFromBuilder(builder.getProductFeedback().toBuilder()));

        return orderFeedbackDto;
    }
}
