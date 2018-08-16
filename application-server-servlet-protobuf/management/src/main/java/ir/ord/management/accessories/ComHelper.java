package ir.ord.management.accessories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import ir.ord.management.dto.protoes.ResponseProto;
import ir.ord.management.services.ResponseObject;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.ws.rs.BadRequestException;
import java.io.UnsupportedEncodingException;

/**
 * Created by vahid on 5/28/17.
 */
public class ComHelper {

    public static Logger managementLogger = Logger.getLogger("managementLogger");
    static {
        managementLogger.setLevel(Level.INFO);
    }
    //-------------------- pure consume
    public static Message.Builder pureConsumeInput(String header, Message.Builder builder, byte[] input) throws InvalidProtocolBufferException, UnsupportedEncodingException {
        if (header.trim().toLowerCase().equals("application/json")) {
            String json = new String(input, "UTF-8");
            JsonFormat.parser().merge(json, builder);
            return builder;
        } else if (header.trim().toLowerCase().equals("application/octet-stream")) {
            Message.Builder resultBuilder = builder.mergeFrom(input);
            return resultBuilder;
        }
        return null;
    }

//-------------- pure produce
    public static byte[] pureProduceOutput(String accept, ResponseProto.Response.Builder responseBuilder, Message.Builder builder) throws InvalidProtocolBufferException {
        if (accept.trim().toLowerCase().equals("application/json")) {
            ResponseObject responseObject = getResponseObjectFromResponseBuilder(responseBuilder);
            if(builder != null){
                builder.mergeFrom(responseBuilder.getData());
                responseObject.setData(getJsonStrFromBuilder(builder));
            }
            String jsonStr = getJsonStr(responseObject);
//            jsonStr = jsonStr.replaceAll("\n", "");
//            jsonStr = jsonStr.replaceAll(, "" );
            return jsonStr.getBytes();
        } else if (accept.trim().toLowerCase().equals("application/octet-stream")) {
            return responseBuilder.build().toByteArray();
        }else{
            throw new BadRequestException();
        }
    }

    //--------------------------   Helper functions
    public static String getJsonStr(Object object) {
        if (object == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            return jsonStr;
        } catch (JsonProcessingException e) {
            managementLogger.error("getJsonStr", e);
        }
        return null;
    }


    private static ResponseObject getResponseObjectFromResponseBuilder(ResponseProto.Response.Builder responseBuilder) {
        if (responseBuilder == null)
            return null;
        ResponseObject responseObject = new ResponseObject();
        responseObject.setResponseCode(responseBuilder.getResponseCode());
        responseObject.setMessage(responseBuilder.getMessage());
        return responseObject;
    }

    public static String getJsonStrFromBuilder(Message.Builder builder) {
        try {
            return JsonFormat.printer().print(builder);
        } catch (InvalidProtocolBufferException e) {
            managementLogger.error("getJsonStrFromBuilder", e);
            e.printStackTrace();

        }
        return null;
    }


}