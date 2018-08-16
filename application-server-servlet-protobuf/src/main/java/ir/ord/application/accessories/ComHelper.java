package ir.ord.application.accessories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import ir.ord.application.Convertor.*;
import ir.ord.application.dto.ObjectList;
import ir.ord.application.dto.protoes.*;
import ir.ord.application.services.ResponseObject;
import org.zeromq.ZMQ;

import javax.ws.rs.BadRequestException;
import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 5/28/17.
 */
public class ComHelper {

    private static ZMQ.Socket ZMQSocket = ZMQ.context(20).socket(ZMQ.PUSH);

    public static Map<String, String> builderConverterMap = new HashMap<String, String>();
    static {
        builderConverterMap.put(AccountInfoProto.AccountInfoRequest.Builder.class.getName(), AccountInfoConvertor.class.getName());
        builderConverterMap.put(AddressProto.AccountAddressResquest.Builder.class.getName(), AddressConvertor.class.getName());
        builderConverterMap.put(ButtonProto.OrdButton.Builder.class.getName(), ButtonConvertor.class.getName());
        builderConverterMap.put(CategoryProto.CategoryRequest.Builder.class.getName(), CategoryConvertor.class.getName());
        builderConverterMap.put(GiftProto.Gift.Builder.class.getName(), GiftConvertor.class.getName());
        builderConverterMap.put(OrderProto.Order.Builder.class.getName(), OrderConvertor.class.getName());
        builderConverterMap.put(PackageProto.PackageRequest.Builder.class.getName(), PackageConvertor.class.getName());
//        builderConverterMap.put(StatusObjectProto.StatusObject.Builder.class.getName(), StatusObjectConvertor.class.getName());
        builderConverterMap.put(TimePeriodProto.TimePeriodRequest.Builder.class.getName(), TimePeriodConvertor.class.getName());
        builderConverterMap.put(SessionInfoProto.SessionDeactivateRequest.Builder.class.getName(), SessionInfoConvertor.class.getName());
        builderConverterMap.put(OrderProto.OrderFeedback.Builder.class.getName(), OrderFeedbackConverter.class.getName());
    }

    static {
        ZMQSocket.connect("tcp://localhost:5555");
    }
    public static ZMQ.Socket getZMQSocket(){
        return ZMQSocket;
    }
//    public static ZMQ.Socket getZMQSocket(){
//        return null;
//    }
    private static String getConvertorClassName(String name) {
        return builderConverterMap.get(name);
    }

    public static Object consumeInput(String header, Message.Builder builder, Class<?> returnedClass, byte[] input) throws IOException, ClassNotFoundException, ConvertorMethodCallException {

        if (header.trim().toLowerCase().equals("application/json")) {
            String json = new String(input, "UTF-8");

            Object object = getObjectFromJson(json, returnedClass);
            return object;
        } else if (header.trim().toLowerCase().equals("application/octet-stream")) {
            builder = builder.mergeFrom(input);
//            String json = JsonFormat.printer().print(builder);
//            Object object = getObjectFromJson(json, returnedClass);
            Object dtoObject = getDtoObject(getConvertorClassName(builder.getClass().getName()), builder);
            return dtoObject;
        }
        return builder;
    }



    //-------------------- pure consume
    public static Message.Builder pureConsumeInput(String header, Message.Builder builder, byte[] input) throws InvalidProtocolBufferException {
        if (header.trim().toLowerCase().equals("application/json")) {
            String json = new String(input);
            JsonFormat.parser().merge(json, builder);
            return builder;
        } else if (header.trim().toLowerCase().equals("application/octet-stream")) {
            Message.Builder resultBuilder = builder.mergeFrom(input);
            return resultBuilder;
        }
        return null;
    }

    public static List<Object>  groupConsumeInput(String header, Message.Builder builder, Class<?> returnedClass, byte[] input) throws IOException, ClassNotFoundException, ConvertorMethodCallException {
        List<Object> resultObjectList = new ArrayList<Object>();
        if (header.trim().toLowerCase().equals("application/json")) {
            String json = new String(input, "UTF-8");
            ObjectList objectList = (ObjectList) getObjectFromJson(json, ObjectList.class);
            for (Object singleObject : objectList.getList()) {
                Object objectFromJson = getObjectFromJson(Helper.getJsonStr(singleObject), returnedClass);
                resultObjectList.add(objectFromJson);
            }
        } else if (header.trim().toLowerCase().equals("application/octet-stream")) {
            ParameterProto.ListRequest.Builder listBuilder = ParameterProto.ListRequest.newBuilder().mergeFrom(input);
            for (ByteString bytes : listBuilder.getListList()) {
                builder = builder.mergeFrom(bytes);
                Object dtoObject = getDtoObject(getConvertorClassName(builder.getClass().getName()), builder);
                resultObjectList.add(dtoObject);
            }
        }
        return resultObjectList;
    }

    public static byte[]  produceOutput(String accept, ResponseObject responseObject, Message.Builder dataBuilder) {
        try{
            if (accept.trim().toLowerCase().equals("application/json")) {
                String jsonStr = Helper.getJsonStr(responseObject);
                jsonStr = jsonStr.replace(",null", "");
                return jsonStr.getBytes("UTF-8");
            } else if (accept.trim().toLowerCase().equals("application/octet-stream")) {
                ResponseProto.Response.Builder responseBuilder = getResponseBuilderFromResponseObject(responseObject);
                if (dataBuilder != null && responseObject.getData()!=null){
                    String jsonStr = Helper.getJsonStr(responseObject.getData());
                    jsonStr = jsonStr.replace(",null", "");
                    if (jsonStr != null)
                            JsonFormat.parser().merge(jsonStr , dataBuilder);
                    responseBuilder.setData(dataBuilder.build().toByteString());
                }
                return responseBuilder.build().toByteArray();
            }
            return null;
        }catch (Exception e){
            Helper.appLogger.error("produceOutput", e);
            return null;
        }
    }

//-------------- pure produce
    public static byte[] pureProduceOutput(String accept, ResponseProto.Response.Builder responseBuilder, Message.Builder builder) throws InvalidProtocolBufferException {
        if (accept.trim().toLowerCase().equals("application/json")) {
            String json = JsonFormat.printer().print(responseBuilder);
            return json.getBytes();
        } else if (accept.trim().toLowerCase().equals("application/octet-stream")) {
            if (builder != null)
                responseBuilder.setData(builder.build().toByteString());
            return responseBuilder.build().toByteArray();
        }else{
            throw new BadRequestException();
        }
    }

//    public static Object consumeInput(String header, Message.Builder builder, byte[] input) throws InvalidProtocolBufferException {
//
//        if (header.trim().toLowerCase().equals("application/json")) {
//
//            com.google.protobuf.util.JsonFormat.parser().merge(new String(input), builder);
//        } else if (header.trim().toLowerCase().equals("application/octet-stream")) {
//            builder = builder.mergeFrom(input);
//
//        }
//        return builder;
//    }

    public static ResponseProto.Response.Builder getResponseBuilderFromResponseObject(ResponseObject responseObject) {
        ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
        if (responseObject.getMessage() != null)
            responseBuilder.setMessage(responseObject.getMessage());
        responseBuilder.setResponseCode(responseObject.getResponseCode());

        return responseBuilder;
    }


    private static ResponseObject getResponseObjectFromProto(ResponseProto.Response.Builder responseBuilder, Message.Builder dataBuilder) throws InvalidProtocolBufferException {
        ResponseObject responseObject = new ResponseObject();
        responseObject.setData(JsonFormat.printer().print(dataBuilder));
        responseObject.setMessage(responseBuilder.getMessage());
        responseObject.setResponseCode(responseBuilder.getResponseCode());
        return responseObject;
    }

    public static Object getObjectFromJson(String jsonString, Class classType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(jsonString, classType);
        } catch (IOException e) {
            Helper.appLogger.error("getObjectFromJson:", e);
        }
        return null;
    }



    public static Object getDtoObject(String converterClassName, Message.Builder builder) throws ConvertorMethodCallException {
        try{
            Class<?> converterClass = Class.forName(converterClassName);
            Object converter = converterClass.newInstance();
            Class[] paramsType = new Class[1];
            paramsType[0] = builder.getClass();
            Method getDtoFromBuilder = converterClass.getDeclaredMethod("getDtoFromBuilder", paramsType);
            Object resultObject = getDtoFromBuilder.invoke(converter, builder);
            return resultObject;
        }catch (Exception e){
            throw new ConvertorMethodCallException(e);
        }
    }


    public static Message.Builder getBuilderObject(String converterClassName, Object dto) throws ConvertorMethodCallException {
        try{
            Class<?> converterClass = Class.forName(converterClassName);
            Object converter = converterClass.newInstance();
            Class[] paramsType = new Class[1];
            paramsType[0] = dto.getClass();
            Method getDtoFromBuilder = converterClass.getDeclaredMethod("getBuilderFromDto", paramsType);
            Object resultObject = getDtoFromBuilder.invoke(converter, dto);
            return (Message.Builder) resultObject;
        }catch (Exception e){
            throw new ConvertorMethodCallException(e);
        }
    }

    public static Message.Builder getBuilderFromInputParameter(String header,Message.Builder builder, byte[] input) throws InvalidProtocolBufferException, UnsupportedEncodingException {
        if (header.trim().toLowerCase().equals("application/json")) {
            String json = new String(input, "UTF-8");
            JsonFormat.parser().merge(json, builder);
            return builder;
        } else if (header.trim().toLowerCase().equals("application/octet-stream")) {
            builder = builder.mergeFrom(input);
            return builder;
        }
        return null;
    }

    // ------- to & from byte

    public static byte[] getByteArrayFromObject(Object object) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        out = new ObjectOutputStream(bos);
        out.writeObject(object);
        out.flush();
        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    public static Object getObjectFromByteArray(byte[] objectByteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(objectByteArray);
        ObjectInput in = new ObjectInputStream(bis);
        Object object = in.readObject();
        return object;
    }

//    public static Object getDtoOrByteString(
//            Object dtoObject, Message.Builder builder, String outputProtocol) throws InvalidProtocolBufferException {
//        Object returnedObject = null;
//        if (outputProtocol.trim().equals("application/json")){
//            returnedObject = dtoObject;
//        }else if (outputProtocol.trim().equals("application/octet-stream")){
//            JsonFormat.parser().merge(Helper.getJsonStr(dtoObject), builder);
//            returnedObject = builder.build().toByteString();
//        }
//        return returnedObject;
//    }

    public static Object getDtoOrByteString(
            Object object, Message.Builder builder, String outputProtocol) throws InvalidProtocolBufferException {
        Object returnedObject = null;
        if (outputProtocol.trim().equals("application/json")){
            returnedObject = object;
        }else if (outputProtocol.trim().equals("application/octet-stream")){
            String jsonStr = Helper.getJsonStr(object);
            JsonFormat.parser().merge(jsonStr, builder);
            returnedObject = builder.build().toByteString();
        }
        return returnedObject;
    }

    public static Object getDtoOrBuilder(
            Object object, Message.Builder builder, String outputProtocol) throws InvalidProtocolBufferException {
        Object returnedObject = null;
        if (outputProtocol.trim().equals("application/json")){
            returnedObject = object;
        }else if (outputProtocol.trim().equals("application/octet-stream")){
            String jsonStr = Helper.getJsonStr(object);
            JsonFormat.parser().merge(jsonStr, builder);
            returnedObject = builder.build();
        }
        return returnedObject;
    }

}