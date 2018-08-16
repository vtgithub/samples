package ir.ord.application.accessories.test;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.PostRequestSendingException;
import ir.ord.application.dto.protoes.ResponseProto;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by vahid on 8/30/17.
 */
public class TestServiceHelper {
    public static  TestResponse requestGenerator(String method, String inputUrl, Map<String, String> headerParameterMap, byte[] bodyContent) throws PostRequestSendingException {
        try{
            TestResponse testResponse =  new TestResponse();
            URL url = new URL(inputUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            boolean doOutput = (bodyContent == null);
            conn.setDoOutput(!doOutput);
            for (Map.Entry<String, String> entry : headerParameterMap.entrySet()){
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (!doOutput) {
                OutputStream os = conn.getOutputStream();
                os.write(bodyContent);
                os.flush();
            }

            byte[] responseBytes = IOUtils.toByteArray(conn.getInputStream());
            testResponse.setResponseBytes(responseBytes);
            testResponse.setResponseCode(conn.getResponseCode());
            conn.disconnect();
            return testResponse;
        }catch (IOException e) {
            throw new PostRequestSendingException(e);
        }
    }

    public static Object getObjectFromJson(String jsonString, Class classType){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        try {
            return objectMapper.readValue(jsonString, classType);
        } catch (IOException e) {
            Helper.appLogger.error("getObjectFromJson", e);
        }
        return null;
    }


    public static boolean checkProtoBuf(
            String method,
            String inputUrl,
            Map<String, String> headerParameterMap,
            Message.Builder bodyBuilder,
            Message.Builder assertedResponseBuilder) throws PostRequestSendingException, InvalidProtocolBufferException {
        headerParameterMap.put("Content-Type", "application/octet-stream");
        headerParameterMap.put("Accept", "application/octet-stream");
//        headerParameterMap.put("Accept", "application/json");

        TestResponse testResponse = requestGenerator(method, inputUrl, headerParameterMap, (bodyBuilder == null)?null:bodyBuilder.build().toByteArray());
//        TestResponse testResponse = requestGenerator(method, inputUrl, headerParameterMap, bodyBuilder.bui);
        System.out.println("testResponse proto ---------->"+testResponse.toString());
        if (testResponse.getResponseCode() == 200){
            ResponseProto.Response.Builder responseBuilder = ResponseProto.Response.newBuilder();
            responseBuilder.mergeFrom(testResponse.getResponseBytes());
            if (responseBuilder.getResponseCode() == 200){
                if (assertedResponseBuilder != null){
                    Message.Builder responseBodyBuilder = assertedResponseBuilder.clone();
                    responseBodyBuilder.mergeFrom(testResponse.getResponseBytes());
                    return responseBodyBuilder.equals(assertedResponseBuilder);
                }else {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean checkJson(
            String method,
            String inputUrl,
            Map<String, String> headerParameterMap,
            String bodyJson,
            String assertedJson) throws PostRequestSendingException {
        headerParameterMap.put("Content-Type", "application/json");
        headerParameterMap.put("Accept", "application/json");
        TestResponse testResponse = requestGenerator(method, inputUrl,headerParameterMap,(bodyJson==null)?null:bodyJson.getBytes());
//        TestResponse testResponse = requestGenerator(method, inputUrl,headerParameterMap,null);
        System.out.println("testResponse json ----------->"+ testResponse.toString());
        System.out.println(new String(testResponse.getResponseBytes()));
        Object objectFromJson = getObjectFromJson(testResponse.toString(), TestBodyResponse.class);
        System.out.println(objectFromJson);
        if (testResponse.getResponseCode() == 200){
//            TestBodyResponse testBodyResponse =
//                    (TestBodyResponse) getObjectFromJson(testResponse.toString(), TestBodyResponse.class);
//            if (testBodyResponse.getResponseCode() == 200){
                if (assertedJson != null){
                    return assertedJson.trim().equals((new String(testResponse.getResponseBytes())).trim());
                }else {
                    return true;
                }
//            }
        }
        return false;
    }

    public static TestResult checkBoth(
            String method,
            String inputUrl,
            Map<String, String> headerParameterMap,
            Message.Builder bodyBuilder,
            Message.Builder assertedBuilder
    ) throws InvalidProtocolBufferException, PostRequestSendingException {
        String bodyJson = (bodyBuilder == null)?null:JsonFormat.printer().print(bodyBuilder);
        String assertedJson = (assertedBuilder==null)?null:JsonFormat.printer().print(assertedBuilder);
        System.out.println("bodyJson ------- >" + bodyJson);
        TestResult testResult = new TestResult();
        testResult.setProtoResult(
                checkProtoBuf(method, inputUrl, headerParameterMap, bodyBuilder, assertedBuilder)
        );
//        testResult.setJsonResult(
//                checkJson(method, inputUrl, headerParameterMap, bodyJson, assertedJson)
//        );
        return testResult;
    }
}
