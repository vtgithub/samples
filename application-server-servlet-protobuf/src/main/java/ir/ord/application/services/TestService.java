package ir.ord.application.services;

import ir.ord.application.accessories.*;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import ir.ord.application.dto.CategoryDto;

import ir.ord.application.dto.protoes.ResponseProto;
import ir.ord.application.dto.protoes.TestDto;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.beans.IntrospectionException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by vahid on 4/29/17.
 */
@Path("/rest/test")
public class TestService {

    @Inject
    ButtonActivationDao buttonActivationDao;
    @Inject
    AccountInfoDao accountInfoDao;
    @Inject
    ActivationDao activationDao;
    @Inject
    ButtonDao buttonDao;
    @Inject
    CategoryDao categoryDao;
    @Inject
    SessionInfoDao sessionInfoDao;
//    @Inject
//    GiftDao giftDao;

//    @Inject
//    OrderDao orderDao;

//    @Inject
//    PackageDao packageDao;

    @Inject
    TimePeriodDao timePeriodDao;

    @Inject
    OrderDao orderDao;
//
//    @Inject
//    private CountersDao countersDao;
//
//    @Inject
//    private CreditDao creditDao;

    @Path("/test")
    @GET
    @Produces("application/json")
    public Response testPost( @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) throws DaoException, IllegalAccessException, IntrospectionException, InstantiationException, ServletException, IOException, URISyntaxException {

        List<OrderEntity> expiredPaiedOrders = orderDao.getExpiredPaiedOrders();
        return Response.status(200).entity(expiredPaiedOrders).build();
    }

    @Path("/ver")
    @POST
    @Produces("*/*")
    public Response testVerPost(@Context HttpServletRequest servletRequest, String val) throws DaoException, IllegalAccessException, IntrospectionException, InstantiationException {


//        DaoHelper.getNewInstanceFromExisting(AccountInfoEntity.class, null);
//        return Response.status(200).entity(Helper.getJsonStr(paymentRequestObject)).build();
//        PaymentRequestObject paymentRequestObject = new PaymentRequestObject();
//        paymentRequestObject.setOrderId(bankPaymentEntity.get_id());
//        Double sum = creditDao.getBalanceSum(
//                sessionInfoDao.getById(
//                        servletRequest.getHeader("sessionId")
//                ).getAccountId()
//        );

        return Response.status(200).entity(DaoHelper.getVerificationSignData(val)).build();
    }


//    @Inject
//    NativeDao nativeDao;
    @Path("/add")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response testPost(TimePeriodEntity timePeriodEntity) {
        try {
            timePeriodDao.save(timePeriodEntity);
            return Response.status(200).entity("ok").build();
        } catch (DaoException e) {
            e.printStackTrace();
            return Response.status(200).entity("nok").build();

        }


    }

//    @Path("/search")
//    @POST
//    @Consumes("application/json")
//    @Produces("application/json")
//    public Response testSearch(Map<String, String> input) {
//        try {
//
////            ActivationEntity activationEntity = activationDao.get(
////                    activationMap.get("activationCode"),
////                    activationMap.get("deviceId"),
////                    false);
//
//            SessionInfoEntity sessionInfoEntity= sessionInfoDao.get(input.get("deviceId"), input.get("accountId"));
//            return Response.status(200).entity(sessionInfoEntity).build();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Response.status(200).entity("nok").build();
//
//        }
//
//    }


    @Path("/searchAll")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response testSearchAll() {
        try {
            List<ButtonEntity> buttonEntityList = buttonDao.getAll();
            return Response.status(200).entity(buttonEntityList).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(200).entity("nok").build();

        }

    }

    @Path("/removeById")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response remove(String id) {
        try {
            buttonDao.removeById(id);
            return Response.status(200).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(200).entity("nok").build();

        }

    }

    @Path("/remove")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response remove(ButtonEntity buttonEntity) {
        try {
            buttonDao.remove(buttonEntity);
            return Response.status(200).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(200).entity("nok").build();

        }

    }

    @Path("/edit")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response testEdit(ButtonEntity buttonEntity) {
        try {
            buttonDao.update(buttonEntity.get_id() , buttonEntity);
            return Response.status(200).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(200).entity("nok").build();

        }

    }



    @Path("/proto")
    @POST
    public Response protoTest(@Context HttpServletRequest servletRequest,
                              byte[] input) throws IOException, ClassNotFoundException, ConvertorMethodCallException {
        TestDto.Test.Builder dataBuilder = TestDto.Test.newBuilder();
        dataBuilder  = (TestDto.Test.Builder) ComHelper.consumeInput(
                servletRequest.getHeader("Content-Type"),
                dataBuilder ,
                CategoryDto.class, input
        );

        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        responseBuilder.setData(dataBuilder.build().toByteString());
        responseBuilder.setMessage("success on build");

//        byte[] result= ComHelper.produceOutput(servletRequest.getHeader("Accept"), responseBuilder, dataBuilder);
//        return Response.status(200).entity(result).build();
        return Response.status(200).entity("d").build();
//        }
//        TestDto.Test test = TestDto.Test.parseFrom(input);
//        test.toBuilder().setAge(23);
//
//        return Response.status(200).entity(test.toString()).build();
    }


}
