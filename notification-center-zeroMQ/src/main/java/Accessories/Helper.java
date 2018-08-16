package Accessories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ProtocolStringList;
import dto.GCMObject;
import dto.OneSignalObject;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by vahid on 6/19/17.
 */
public class Helper {

    public static Logger logger = Logger.getLogger("notifLogger");

    public static String getJsonStr(Object object) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("getJsonStr", e);
        }
        return null;
    }

    public static List<String> getStringList(ProtocolStringList segmentsList) {
        List<String> stringList = new ArrayList<String>();
        for (String s : segmentsList) {
            stringList.add(s);
        }
        return segmentsList;
    }

    public static String getSmsErrorMessage(SmsResponse smsResponse) {
        if (smsResponse != null){
            if ((smsResponse.getStatus() == 0 && smsResponse.getMsgIdArray() == 20) ){
                return Messages.numberInBlackList;
            }else if (smsResponse.getStatus() == 2){
                return Messages.insufficientCredit;
            }else if (smsResponse.getStatus() == 3){
                return Messages.disabledUser;
            }
        }
        return null;
    }


    public static class Notification{
        public static Integer sendContentUpdate(Integer platform,
                                                Integer type,
                                                Integer event,
                                                Integer priority,
                                                String id,
                                                List<String> includedSegments){

            try{
                if (platform == PlatformEnum.IOS.getCode()){
                    return sendOneSignalContentUpdate(type, event, priority, id, includedSegments);
                }else if (platform == PlatformEnum.ANDROID.getCode()){
                    return sendGcmContentUpdate(type, event, priority, id, includedSegments);
                }
                return -1;
            } catch(Throwable t) {
                logger.error("CONTENT_UPDATE",t);
                return -1;
            }


        }


        public static Integer sendNotif(Integer platform,
                                        String title,
                                        String subtitle,
                                        String message,
                                        List<String> includedSegments){
            try {
                if (platform == PlatformEnum.IOS.getCode()){
                    return sendOneSignalNotif(title, subtitle, message, includedSegments);
                }else if (platform == PlatformEnum.ANDROID.getCode()){
                    return sendGcmNotif(title, subtitle, message, includedSegments);
                }
                return -1;
            } catch(Throwable t) {
                logger.error("NOTIFICATION",t);
                return -1;
            }
        }


        //___________________________________One Signal___________________________________________
        private static Integer sendOneSignalContentUpdate(Integer type,
                                                         Integer event,
                                                         Integer priority,
                                                         String id,
                                                         List<String> includedSegments) throws IOException {
            String jsonResponse;
            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic MWYwYmMyNTktMjgzOS00ZjlkLWJjM2MtMmM3YTIzMDdkZmMw");
            con.setRequestMethod("POST");

            OneSignalObject oneSignalObject = new OneSignalObject();

            oneSignalObject.setInclude_ios_tokens(includedSegments);
            oneSignalObject.setData(type, event, id);
            oneSignalObject.setContent_available(true);
            oneSignalObject.setPriority(priority);

            String strJsonBody = Helper.getJsonStr(oneSignalObject);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            if (  httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            logger.info("CONTENT_UPDATE__REQUEST:"+ strJsonBody);
            logger.info("CONTENT_UPDATE__RESPONSE:"+jsonResponse+", RESPONSE_CODE:"+ httpResponse);
            return httpResponse;

        }

        private static Integer sendOneSignalNotif(String title,
                                                 String subtitle,
                                                 String message,
                                                 List<String> includedSegments) throws IOException {
            String jsonResponse;

            URL url = new URL("https://onesignal.com/api/v1/notifications");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);

            con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            con.setRequestProperty("Authorization", "Basic MWYwYmMyNTktMjgzOS00ZjlkLWJjM2MtMmM3YTIzMDdkZmMw");
            con.setRequestMethod("POST");

            OneSignalObject oneSignalObject = new OneSignalObject();

            oneSignalObject.setHeadings(title);
            oneSignalObject.setSubtitle(subtitle);
            oneSignalObject.setInclude_ios_tokens(includedSegments);
            oneSignalObject.setContents(message);


            String strJsonBody = Helper.getJsonStr(oneSignalObject);

            byte[] sendBytes = strJsonBody.getBytes("UTF-8");
            con.setFixedLengthStreamingMode(sendBytes.length);

            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);

            int httpResponse = con.getResponseCode();
            if (  httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            logger.info("NOTIFICATION__REQUEST:"+ strJsonBody);
            logger.info("NOTIFICATION__RESPONSE:"+ jsonResponse+",RESPONSE_CODE:"+httpResponse);
            return httpResponse;

        }
        //____________________________________One Signal_________________________________________

        //____________________________________GCM________________________________________
        private static Integer sendGcmContentUpdate(Integer type, Integer event, Integer priority, String id, List<String> includedSegments) throws IOException {
            URL url = new URL("https://gcm-http.googleapis.com/gcm/send");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "key=AIzaSyDFldkruISVlid8C5iK774WFsrLgpMrSzY");
            con.setRequestMethod("POST");

            GCMObject gcmObject = new GCMObject();
            gcmObject.setData(type, event, id);
            gcmObject.setContent_available(true);
            gcmObject.setPriority((priority == UpdateNotifPriority.HIGHT.getCode())?"high":"normal");
            if (includedSegments.size() == 1)
                gcmObject.setTo(includedSegments.get(0));
            else {
//                gcmObject.setTo("/topics/my_little_topic");
                gcmObject.setRegistration_ids(includedSegments);
            }
            String strJsonBody = Helper.getJsonStr(gcmObject);
            byte[] sendBytes = strJsonBody.getBytes();
            con.setFixedLengthStreamingMode(sendBytes.length);
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);
            int httpResponse = con.getResponseCode();
            String jsonResponse = "";
            if (  httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
                logger.info("NOTIFICATION__REQUEST:"+ strJsonBody);
                logger.info("NOTIFICATION__RESPONSE:"+ jsonResponse+",RESPONSE_CODE:"+httpResponse);
            System.out.println(jsonResponse);
            return httpResponse;
        }


        private static Integer sendGcmNotif(String title, String subtitle, String message, List<String> includedSegments) throws IOException {
            URL url = new URL("https://gcm-http.googleapis.com/gcm/send");
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setUseCaches(false);
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", "key=AIzaSyDFldkruISVlid8C5iK774WFsrLgpMrSzY");
            con.setRequestMethod("POST");

            GCMObject gcmObject = new GCMObject();
            gcmObject.setNotification(title, message);
            if (includedSegments.size() == 1)
                gcmObject.setTo(includedSegments.get(0));
            else {
//                gcmObject.setTo("/topics/my_little_topic");
                gcmObject.setRegistration_ids(includedSegments);
            }
//                String data = "{" +
//                        "   \"to\" : \"/topics/my_little_topic\"," +
//                        "   \"notification\" : {" +
//                        "       \"body\" : \"messageBody\"," +
//                        "       \"title\" : \"messageTitle\"," +
//                        "       \"icon\" : \"ic_cloud_white_48dp\"" +
//                        "   }" +
//                        "}";
            String strJsonBody= Helper.getJsonStr(gcmObject);
            byte[] sendBytes = strJsonBody.getBytes();
            con.setFixedLengthStreamingMode(sendBytes.length);
            OutputStream outputStream = con.getOutputStream();
            outputStream.write(sendBytes);
            int httpResponse = con.getResponseCode();
            String jsonResponse = "";
            if (  httpResponse >= HttpURLConnection.HTTP_OK
                    && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
                Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            } else {
                Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
                jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
                scanner.close();
            }
            logger.info("NOTIFICATION__REQUEST:"+ strJsonBody);
            logger.info("NOTIFICATION__RESPONSE:"+ jsonResponse+",RESPONSE_CODE:"+httpResponse);
            System.out.println(jsonResponse);
            return httpResponse;

        }

        //____________________________________GCM_________________________________________

        public static SmsResponse
        sendPureSMS(String destinationPhoneNumber, String body) {
            String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webService.compositeSmsGateway.services.sdp.peykasa.com/\">" +
                    "<soapenv:Header/>" +
                    "<soapenv:Body>" +
                    "<web:sendSms>" +
                    "    <userCredential>" +
                    "        <password>Re$Pina@28Ws</password>" +
                    "        <username>respina</username>" +
                    "    </userCredential>" +
                    "    <srcAddresses>982184230000</srcAddresses>" +
                    "    <destAddresses>"+destinationPhoneNumber+"</destAddresses>" +
                    "    <msgBody>"+body+"</msgBody>" +
                    "    <msgEncoding>1</msgEncoding>" +
                    "</web:sendSms>" +
                    "</soapenv:Body>" +
                    "</soapenv:Envelope>";

            Map<String, String> responseStr = null;
            logger.info("SMS__REQUEST: " +  requestBody);
            try {
                responseStr = requestGenerator(
                        "POST",
                        "http://ws.asanak.ir:8082/services/CompositeSmsGateway?wsdl",
                        new HashMap<String, String>(),
                        requestBody.getBytes("UTF-8")
                );
                
                logger.info("SMS__RESPONSE: "+ responseStr);
            } catch (PostRequestSendingException e) {
                logger.error("SMS", e);
                return null;
            } catch (UnsupportedEncodingException e) {
                logger.error("SMS", e);
                return null;
            }
            String responseBody  = responseStr.get("bodyVal");

            String status = responseBody.substring(
                    responseBody.indexOf("<status>") + "<status>".length(), responseBody.indexOf("</status>")
            );
            String msgIdArray = responseBody.substring(
                    responseBody.indexOf("<msgIdArray>") + "<msgIdArray>".length(), responseBody.indexOf("</msgIdArray>")
            );
            SmsResponse smsResponse = new SmsResponse();
            smsResponse.setMsgIdArray(Long.parseLong(msgIdArray));
            smsResponse.setStatus(Integer.valueOf(status));

            return smsResponse;
        }

    }

    public static  Map<String, String>
    requestGenerator(String method, String inputUrl, Map<String, String> headerParameterMap, byte[] bodyContent) throws PostRequestSendingException {
        try{
            Map<String, String> resMap = new HashMap<String, String>();
            URL url = new URL(inputUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setDoOutput(true);
            for (Map.Entry<String, String> entry : headerParameterMap.entrySet()){
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }

            OutputStream os = conn.getOutputStream();
            os.write(bodyContent);
            os.flush();
//        if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + conn.getResponseCode());
//        }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output, bodyString = "";
            int responseCode = conn.getResponseCode();
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                bodyString = bodyString + output;
            }

            conn.disconnect();
            resMap.put("responseCode", String.valueOf(responseCode));
            resMap.put("bodyVal", bodyString);

            return resMap;
        }catch (IOException e) {
            throw new PostRequestSendingException(e);
        }
    }

    public static String getNotifTypeStr(byte type){

            if (type == 1)
                return "NOTIFICATION";
            else if (type == 2)
                return "CONTENT_UPDATE";
            else if (type == 3)
                return "SMS";
            return null;

    }
}
