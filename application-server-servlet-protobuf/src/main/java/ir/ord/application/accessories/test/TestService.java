package ir.ord.application.accessories.test;

import com.google.protobuf.InvalidProtocolBufferException;
import ir.ord.application.accessories.PostRequestSendingException;
import ir.ord.application.dto.protoes.AccountInfoProto;
import ir.ord.application.dto.protoes.GiftProto;
import ir.ord.application.dto.protoes.OrderProto;
import ir.ord.application.dto.protoes.ParameterProto;

import javax.swing.event.ListDataEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 8/30/17.
 */
public class TestService {
    public static void main(String[] args) throws PostRequestSendingException, InvalidProtocolBufferException {

//        GiftProto.GiftCharge.Builder asserted = GiftProto.GiftCharge.newBuilder();
//        ResponseProto.Response.Builder assertedResponseBuilder = ResponseProto.Response.newBuilder();
//        assertedResponseBuilder.setResponseCode(200);
//        assertedResponseBuilder.setMessage("اطلاعات کارت با موفقیت ثبت شد");
//        assertedResponseBuilder.setData(assertedResponseBuilder.build().toByteString());
//
//        JsonFormat.parser().merge("{\"chargeCode\":\"pc9z5f1h\"}", asserted);
//        System.out.println(
//                TestServiceHelper.checkBoth(
//                        "POST",
//                        "http://172.22.32.16:8180/application-war/gift" ,
//                        headers,
//                        giftRequest,
//                        assertedResponseBuilder
//                )
//        );

        //but this will not work because of drandomness of chargeCode


//        GiftProto.Gift gift = GiftProto.Gift.newBuilder().setCode("123").setValue(12000).setExpirationTime(Helper.getCurrentTime()+3600000).setIncludeOrExclude(false).build();
//        GiftProto.GiftRequest.Builder giftRequest = GiftProto.GiftRequest.newBuilder().setPhoneNumber("09354212425").setGiftDto(gift);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("sessionId", "dfdb8f42-7bc7-4219-b266-693c25725111");
//        headers.put("sessionId", "dfdb8f42-7bc7-4219-b266-693c25725111");
//        headers.put("sessionId", "dfdb8f42-7bc7-4219-b266-693c25725111");

        OrderProto.OrderFeedback.Builder orderBuilder = OrderProto.OrderFeedback.newBuilder();
        ParameterProto.FeedbackObject.Builder feedbackBuilder = ParameterProto.FeedbackObject.newBuilder();
        ParameterProto.ComboElement.Builder comboBuilder = ParameterProto.ComboElement.newBuilder();
        comboBuilder.setVal("val");
        comboBuilder.setId(1);
        feedbackBuilder.addComboElementList(comboBuilder);
        feedbackBuilder.setOpinion("opinion");
        feedbackBuilder.setSatisfactionLevel(1);
        orderBuilder.setDeliveryFeedback(feedbackBuilder);
        orderBuilder.setPackingFeedback(feedbackBuilder);
        orderBuilder.setProductFeedback(feedbackBuilder);


//        GiftProto.GiftRequest gift = GiftProto.GiftRequest.newBuilder().setCode("123").setValue(111).addAllAccountId(accountIdList).setExpirationTime(System.currentTimeMillis()+3600000).setIncludeOrExclude(false).build();

                System.out.println(
                    TestServiceHelper.checkBoth(
                        "POST",
                        "http://localhost:8180/application-war/rest/order/2aabb99d-70a3-4e38-995a-2e649f0bd941/feedback" ,
                        headers,
                        orderBuilder,
                        null
                    )
                );






    }


}
