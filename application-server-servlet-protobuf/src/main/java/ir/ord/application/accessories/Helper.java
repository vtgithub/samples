package ir.ord.application.accessories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import com.maxmind.geoip.Location;
import com.maxmind.geoip.LookupService;
import com.maxmind.geoip.regionName;
import ir.ord.application.BankEnum;
import ir.ord.application.NotifType;
import ir.ord.application.OperationEnum;
import ir.ord.application.ResponseStatus;
import ir.ord.application.biz_layer.biz.CallableRow;
import ir.ord.application.biz_layer.rpc.CacheService;
import ir.ord.application.biz_layer.rpc.CacheServiceHandler;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import ir.ord.application.dto.ComboElementDto;
import ir.ord.application.dto.protoes.NotificationProto;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;
import javax.servlet.ServletInputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by vahid on 4/17/17.
 */
public class Helper {

    @Inject
    PublicMongoDao publicMongoDao;

    static ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
    static String bankInfo;

    public static Logger appLogger = Logger.getLogger("appLogger");
    public static Logger buttonLogger= Logger.getLogger("buttonLogger");
    static Map<Object, Object> entityDao = new HashMap<Object, Object>();
    static {
        entityDao.put(ActivationDaoImpl.class, ActivationEntity.class);
        entityDao.put(SessionInfoDaoImpl.class, SessionInfoEntity.class);
        entityDao.put(AccountInfoDaoImpl.class, AccountInfoEntity.class);
        entityDao.put(ButtonActivationDaoMongoImpl.class, ButtonActivationEntity.class);
        entityDao.put(ButtonDaoImpl.class, ButtonEntity.class);
        entityDao.put(CategoryDaoImpl.class, CategoryEntity.class);
        entityDao.put(CreditDaoImpl.class, CreditEntity.class);
        entityDao.put(GiftDaoImpl.class, GiftEntity.class);
        entityDao.put(OrderDaoImpl.class, OrderEntity.class);
        entityDao.put(PackageDaoImpl.class, PackageEntity.class);
        entityDao.put(TimePeriodDaoImpl.class, TimePeriodEntity.class);
    }

    static Map<String, String> uriToPageAddressMap = new HashMap<String, String>();
    static {
        uriToPageAddressMap.put("/pages/bank", "/bank.html");
        uriToPageAddressMap.put("/pages/user", "/user.html");
    }
    static {
        appLogger.setLevel(Level.INFO);
        buttonLogger.setLevel(Level.INFO);
    }


    public static Class getEntityClass(Class clazz){
        return (Class) entityDao.get(clazz);
    }

    public static String getJsonStrFromProtoBuilder(Message.Builder builder) {
        try {
            return JsonFormat.printer().print(builder);
        } catch (InvalidProtocolBufferException e) {
            Helper.appLogger.error("getJsonStrFromProtoBuilder", e);

        }
        return null;
    }

    public static void runRunnableList(List<Runnable> notifRunnableList) {
        for (Runnable runnable : notifRunnableList) {
            runnable.run();
        }
    }

    public static byte[] getByteArrayFromServletInputStream(ServletInputStream servletInputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int nread;
        while ((nread = servletInputStream.read())!=-1 ){
            byteArrayOutputStream.write(nread);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Long getDeliveryTime(Long currentTimeStamp) {
        //TODO calculate delivery time using timePeriods
        return currentTimeStamp + 3*60*60*1000;
    }

    public static String getTrackNum(String orderId) {
        return orderId.substring(0,6);
    }

    public static String getPersianNumbers(Long number) {
        StringBuilder sb= new StringBuilder();
        char[] chars = number.toString().toCharArray();
        for (int i=0 ; i<chars.length;i++){
            switch (chars[i]){
                case '1':
                    sb.append('۱');
                    break;
                case '2':
                    sb.append('۲');
                    break;
                case '3':
                    sb.append('۳');
                    break;
                case '4':
                    sb.append('۴');
                    break;
                case '5':
                    sb.append('۵');
                    break;
                case '6':
                    sb.append('۶');
                    break;
                case '7':
                    sb.append('۷');
                    break;
                case '8':
                    sb.append('۸');
                    break;
                case '9':
                    sb.append('۹');
                    break;
                case '0':
                    sb.append('۰');
                    break;

            }
        }
        return sb.toString();
    }

    public static Byte getSystemProccessHour() {
        return 3;
    }

    public static String addZeroToOrderId(String orderId) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < 6 - orderId.length(); i++)
            stringBuilder.append('0');
        stringBuilder.append(orderId);
        return stringBuilder.toString();
    }

    public static boolean isSameRequest(String s) {
        return s.equals(bankInfo);
    }

    public static void putRequest(String s) {
        bankInfo = s;
    }

    public static class Notification{

        public static void sendContentUpdate(Integer type,
                                             Integer event,
                                             Integer priority,
                                             String id,
                                             DevicePushToken devicePushToken){
            NotificationProto.ContentUpdateNotif.Builder contentUpdateNotifBuilder= NotificationProto.ContentUpdateNotif.newBuilder().
                    setType(type).
                    setEvent(event).
                    setPriority(priority).
                    setPlatform(devicePushToken.getPlatform());
            if (id != null)
                contentUpdateNotifBuilder.setId(id);

            if (devicePushToken.getPushTokenSet() != null && devicePushToken.getPushTokenSet().size()>0){
                contentUpdateNotifBuilder.addAllSegments(devicePushToken.getPushTokenSet());
            }

            NotificationProto.ContentUpdateNotif contentUpdateNotif = contentUpdateNotifBuilder.build();

            NotificationProto.Request request = NotificationProto.Request.newBuilder().
                    setType(NotifType.CONTENT_UPDATE.getCode()).
                    setBody(contentUpdateNotif.toByteString()).
                    build();
            try {
                ComHelper.getZMQSocket().send(request.toByteArray(), 0);
            }catch (Exception e){
                Helper.appLogger.error( e);
            }


//            try{
//                String jsonResponse;
//
//                URL url = new URL("https://onesignal.com/api/v1/notifications");
//                HttpURLConnection con = (HttpURLConnection)url.openConnection();
//                con.setUseCaches(false);
//                con.setDoOutput(true);
//                con.setDoInput(true);
//
//                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                con.setRequestProperty("Authorization", "Basic MWYwYmMyNTktMjgzOS00ZjlkLWJjM2MtMmM3YTIzMDdkZmMw");
//                con.setRequestMethod("POST");
//
//                NotificationObject notificationObject = new NotificationObject();
//
//                notificationObject.setInclude_ios_tokens(includedSegments);
//                notificationObject.setData(type, event);
//                notificationObject.setContent_available(true);
//                notificationObject.setPriority(priority);
//
//                String strJsonBody = Helper.getJsonStr(notificationObject);
//
//                comLogger.info("notif: "+ strJsonBody);
//                byte[] sendBytes = strJsonBody.getBytes("UTF-8");
//                con.setFixedLengthStreamingMode(sendBytes.length);
//
//                OutputStream outputStream = con.getOutputStream();
//                outputStream.write(sendBytes);
//
//                int httpResponse = con.getResponseCode();
//                comLogger.info("notifHttpResponse: " + httpResponse);
//                if (  httpResponse >= HttpURLConnection.HTTP_OK
//                        && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
//                    Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
//                    jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
//                    scanner.close();
//                }
//                else {
//                    Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
//                    jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
//                    scanner.close();
//                }
//                return httpResponse;
//
//            } catch(Throwable t) {
//                comLogger.error("sendNotificationToUser:"+t.getMessage());
//                t.printStackTrace();
//                return ResponseStatus.SENDING_TO_NOTIF_CENTER_FAILED.getCode();
//            }


        }


        public static void sendNotif(String title,
                                       String subtitle,
                                       String message,
                                       DevicePushToken devicePushToken){

            NotificationProto.Notif notifBuild = NotificationProto.Notif.newBuilder().
                    setTitle(title).
                    setSubtitle(subtitle).
                    setMessage(message).
                    setPlatform(devicePushToken.getPlatform()).
                    addAllSegments(devicePushToken.getPushTokenSet()).
                    build();
            NotificationProto.Request request = NotificationProto.Request.newBuilder().
                    setType(NotifType.NOTIFICATION.getCode()).
                    setBody(notifBuild.toByteString()).
                    build();
            try {
                ComHelper.getZMQSocket().send(request.toByteArray(), 0);
            }catch (Exception e){
                Helper.appLogger.error( e);
            }
            //            try {
//                String jsonResponse;
//
//                URL url = new URL("https://onesignal.com/api/v1/notifications");
//                HttpURLConnection con = (HttpURLConnection)url.openConnection();
//                con.setUseCaches(false);
//                con.setDoOutput(true);
//                con.setDoInput(true);
//
//                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//                con.setRequestProperty("Authorization", "Basic MWYwYmMyNTktMjgzOS00ZjlkLWJjM2MtMmM3YTIzMDdkZmMw");
//                con.setRequestMethod("POST");
//
//                NotificationObject notificationObject = new NotificationObject();
//
//                notificationObject.setHeadings(title);
//                notificationObject.setSubtitle(subtitle);
//                notificationObject.setInclude_ios_tokens(includedSegments);
//                notificationObject.setContents(message);
//
//
//                String strJsonBody = Helper.getJsonStr(notificationObject);
//
//                comLogger.info("notif: "+ strJsonBody);
//                byte[] sendBytes = strJsonBody.getBytes("UTF-8");
//                con.setFixedLengthStreamingMode(sendBytes.length);
//
//                OutputStream outputStream = con.getOutputStream();
//                outputStream.write(sendBytes);
//
//                int httpResponse = con.getResponseCode();
//                comLogger.info("notifHttpResponse: " + httpResponse);
//                if (  httpResponse >= HttpURLConnection.HTTP_OK
//                        && httpResponse < HttpURLConnection.HTTP_BAD_REQUEST) {
//                    Scanner scanner = new Scanner(con.getInputStream(), "UTF-8");
//                    jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
//                    scanner.close();
//                }
//                else {
//                    Scanner scanner = new Scanner(con.getErrorStream(), "UTF-8");
//                    jsonResponse = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
//                    scanner.close();
//                }
//                return httpResponse;
//
//            } catch(Throwable t) {
//                comLogger.error("sendNotificationToUser:"+t.getMessage());
//                t.printStackTrace();
//                return ResponseStatus.SENDING_TO_NOTIF_CENTER_FAILED.getCode();
//            }
        }

        public static void
        sendPureSMS(String destinationPhoneNumber, String body) {
            NotificationProto.SMS smsBuild = NotificationProto.SMS.newBuilder().
                    setDestinationPhoneNumber(destinationPhoneNumber).
                    setBody(body).
                    build();
            NotificationProto.Request request = NotificationProto.Request.newBuilder().
                    setType(3).
                    setBody(smsBuild.toByteString()).
                    build();
            try {
                ComHelper.getZMQSocket().send(request.toByteArray(), 0);
            }catch (Exception e){
                Helper.appLogger.error("Notification -> sendPureSMS", e);
            }

//        String requestBody = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://webService.compositeSmsGateway.services.sdp.peykasa.com/\">\n" +
//                "<soapenv:Header/>\n" +
//                "<soapenv:Body>\n" +
//                "<web:sendSms>\n" +
//                "    <userCredential>\n" +
//                "        <password>respina@1599</password>\n" +
//                "        <username>respina</username>\n" +
//                "    </userCredential>\n" +
//                "    <srcAddresses>982184230000</srcAddresses>\n" +
//                "    <destAddresses>"+destinationPhoneNumber+"</destAddresses>\n" +
//                "    <msgBody>"+body+"</msgBody>\n" +
//                "    <msgEncoding>1</msgEncoding>\n" +
//                "</web:sendSms>\n" +
//                "</soapenv:Body>\n" +
//                "</soapenv:Envelope>";
//
//        Map<String, String> responseStr = null;
//        try {
//            responseStr = requestGenerator(
//                    "POST",
//                    "http://ws.asanak.ir:8082/services/CompositeSmsGateway?wsdl",
//                    new HashMap<String, String>(),
//                    requestBody.getBytes("UTF-8")
//            );
//        } catch (PostRequestSendingException e) {
//            comLogger.error("sms: "+e.getMessage());
//            e.printStackTrace();
//            return null;
//        } catch (UnsupportedEncodingException e) {
//            comLogger.error("sms: "+e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//        String responseBody  = responseStr.get("bodyVal");
//
//        String status = responseBody.substring(
//                responseBody.indexOf("<status>") + "<status>".length(), responseBody.indexOf("</status>")
//        );
//        String msgIdArray = responseBody.substring(
//                responseBody.indexOf("<msgIdArray>") + "<msgIdArray>".length(), responseBody.indexOf("</msgIdArray>")
//        );
//        SendSmsResponse sendSmsResponse = new SendSmsResponse();
//        sendSmsResponse.setMsgIdArray(Long.parseLong(msgIdArray));
//        sendSmsResponse.setStatus(Integer.valueOf(status));
//        comLogger.info("sms: "+destinationPhoneNumber+", response: "+getJsonStr(sendSmsResponse));
//
//
//        return sendSmsResponse;
        }

        public static void sendGlobalNotificationToUserAfterSeconds(
                int notifWaiteTime,
                final String creditLowTitle,
                final String phoneNumber,
                final String creditLowMessage,
                final DevicePushToken devicePushToken) {

            final ScheduledFuture<?> future = executor.schedule(new Runnable() {
                public void run() {
                    System.out.println("sendNotificationToUserAfterSeconds has been called");
                    Notification.sendNotif(creditLowTitle, phoneNumber, creditLowMessage, devicePushToken);
//                executor.shutdown();

                }
            }, notifWaiteTime, TimeUnit.SECONDS);


        }

        public static void sendPureSMSAfterSeconds(final int seconds, final String phoneNumber, final String creditLowMessage) {
            final ScheduledFuture<?> future = executor.schedule(new Runnable() {
                public void run() {
                    System.out.println("sendPureSMSAfterSeconds has been called");
                    Notification.sendPureSMS(phoneNumber, creditLowMessage);
                }
            }, seconds, TimeUnit.SECONDS);

        }
    }

    public static  RequestResponse requestGenerator(String method, String inputUrl, Map<String, String> headerParameterMap, byte[] bodyContent) throws PostRequestSendingException {
        try{
            RequestResponse response =  new RequestResponse();
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
            response.setResponseCode(responseCode);
            response.setResponseBody(bodyString);

            return response;
        }catch (IOException e) {
            throw new PostRequestSendingException(e);
        }
    }



    public static int getResponseTimeOut() {
        //TODO read from a config
        return 1200;// second
    }


    public static String getActivationCode() {
        char[] chars = "1234567890".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return  sb.toString();
    }

    public static String getRandomStr(int strLen){
        char[] chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxwz!@#$%^&*()=-".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < strLen; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return  sb.toString();
    }
//
//    public static void sendSMS(String destinationPhoneNumber, String smsBody) {
//
//        String output = executeCommand("java -jar /opt/jars/sms.jar " + destinationPhoneNumber + " " + smsBody);
//
//    }





    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            appLogger.error("executeCommand", e);
        }

        return output.toString();

    }

    public static Long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getJsonStr(Object object) {
        if (object == null)
            return null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            Helper.appLogger.error("getJsonStr", e);
        }
        return null;
    }

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static String getGiftChargeCode() {
        char[] chars = "1234567890abcdefghijklmnpqrstuvwxyz".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }





    // ----------- for bank
    public static String getBankPageUrI() {
//        return applicationServerAddress + "/pages/bank";
//        return " https://sadad.shaparak.ir/VPG/Purchase";
//        return "http://localhost:8180/application-war/pages/bank";
//        "https://sadad.shaparak.ir/VPG/api/v0/Request/PaymentRequest"
        return "https://sadad.shaparak.ir/VPG/Purchase";
    }

    public static PaymentResponseObject getBankResponse(PaymentRequestObject paymentRequestObject) {
        try{
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Content-Type", "application/json");
            RequestResponse response = requestGenerator(
                    "POST",
                    "https://sadad.shaparak.ir/VPG/api/v0/Request/PaymentRequest",
                    headerMap,
                    getJsonStr(paymentRequestObject).getBytes()
            );

            if (response.getResponseCode() == ResponseStatus.OK.getCode()) {
                return (PaymentResponseObject) getObjectFromJson(String.valueOf(response.getResponseBody()), PaymentResponseObject.class);
            }
        }catch (Exception e){
            appLogger.error("getBankResponse", e);
        }

        return null;
    }

    public static VerifyResponseObject getBankVerificationResponse(VerifyRequestObject verifyRequestObject) {
        Map<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        try {
            RequestResponse response = requestGenerator(
                    "POST",
                    "https://sadad.shaparak.ir/VPG/api/v0/Advice/Verify",
                    headerMap,
                    getJsonStr(verifyRequestObject).getBytes()
            );
            if (response.getResponseCode() == ResponseStatus.OK.getCode()){
                return (VerifyResponseObject) getObjectFromJson((String) response.getResponseBody(), VerifyResponseObject.class);
            }
        } catch (PostRequestSendingException e) {
            appLogger.error("getBankVerificationResponse", e);
        }
        return null;

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


    public static Integer getActivationCodeTimeOut() {
        return 120;/*second*/
    }

    public static long getOneHourMiliSeconds() {
            return 60*60*1000;
    }

    public static long getOrderExpirationMiliSeconds() {
        return 24*60*60*1000;
    }

    public static long getOneDayMiliSeconds() {
        return getOneHourMiliSeconds()*24;
    }

    public static long getOneMinuteMilliseconds() {
        return 60*1000;
    }

    public static ClientLocation getClientLocation(String ipAddress){
        File file = new File(
                "/opt/location-lib/GeoLiteCity.dat");
        ClientLocation clientLocation = null;

        try {

            clientLocation = new ClientLocation();

            LookupService lookup = new LookupService(file,LookupService.GEOIP_MEMORY_CACHE);
            Location locationServices = lookup.getLocation(ipAddress);
            if (locationServices == null)
                return null;
            clientLocation.setCountryCode(locationServices.countryCode);
            clientLocation.setCountryName(locationServices.countryName);
        } catch (IOException e) {
            appLogger.error("getClientLocation", e);
        }

        return clientLocation;
    }


    public static int getNotifWaiteTime() {
        return 30;
    }

    public static Map<String, Object> getDictionaryFromList(Object object) {
        Map<String, Object> dictionaryList = new HashMap<String,Object>();
        dictionaryList.put("list", object);
        return dictionaryList;
    }

    public static String getQRCode() {
        char[] chars = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 64; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }

    public static Integer getQRCodeTTL() {
        return 15*60*1000;
    }

    public static void callCallableList(List<CallableRow> callableList) throws DaoException {
        for (int i = 0; i < callableList.size() ; i++){
            try {
                callableList.get(i).getCallable().call();
            } catch (Exception e) {
                appLogger.error("callCallableList", e);
                List<CallableRow> commitedList = callableList.subList(0, i);
                rollBack(commitedList);
                throw new DaoException(e);
            }
        }
    }

    private static void rollBack(List<CallableRow> commitedList) {
        for (CallableRow callableRow : commitedList) {
            if (callableRow.getOperation() == OperationEnum.INSERT.getCode()){
                PublicMongoDao.deleteById(callableRow.getEntityClassName(), callableRow.getId());
            }else if(callableRow.getOperation() == OperationEnum.UPDATE.getCode()){
                PublicMongoDao.update(callableRow.getEntityClassName(),callableRow.getId(), getJsonStr(callableRow.getEntityOldValue()));
            }else if (callableRow.getOperation() == OperationEnum.DELETE.getCode()){
                PublicMongoDao.insert(callableRow.getEntityClassName(), getJsonStr(callableRow.getEntityOldValue()));
            }
        }
    }

    public static class CacheServer{

        public static void start() {
            CacheService.Processor<CacheServiceHandler> processor =
                    new CacheService.Processor<CacheServiceHandler>(new CacheServiceHandler());
            try {
                TServerTransport serverTransport = new TServerSocket(6000);
                final TSimpleServer server = new TSimpleServer(
                        new TServer.Args(serverTransport).processor(processor));
                new Thread(){
                    public void run(){
                        server.serve();
                    }
                }.start();
            } catch (Exception e) {
                Helper.appLogger.error("CacheServer -> start", e);
            }
        }
    }

    //-------------- Byte Utiles
    public static class BytesUtil {

        // toByteArray and toObject are taken from: http://tinyurl.com/69h8l7x
        public static byte[] toByteArray(Object obj) throws IOException {
            byte[] bytes = null;
            ByteArrayOutputStream bos = null;
            ObjectOutputStream oos = null;
            try {
                bos = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);
                oos.flush();
                bytes = bos.toByteArray();
            } finally {
                if (oos != null) {
                    oos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            }
            return bytes;
        }

        public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
            Object obj = null;
            ByteArrayInputStream bis = null;
            ObjectInputStream ois = null;
            try {
                bis = new ByteArrayInputStream(bytes);
                ois = new ObjectInputStream(bis);
                obj = ois.readObject();
            } finally {
                if (bis != null) {
                    bis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            }
            return obj;
        }

        public static String toString(byte[] bytes) {
            return new String(bytes);
        }
    }

    //-------------- RSA
    public static class RSA{

        private static Cipher cipher ;
        private static PrivateKey privateKey;
        private static PublicKey publicKey;

        public static void init(String publicKeyPath, String privateKeyPath) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
            cipher = Cipher.getInstance("RSA");
            KeyFactory kf = KeyFactory.getInstance("RSA");
            byte[] publicKeyBytes = Files.readAllBytes(new File(publicKeyPath).toPath());
            X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicKeyBytes);
            publicKey = kf.generatePublic(publicSpec);
            byte[] keyBytes = Files.readAllBytes(new File(privateKeyPath).toPath());
            PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(keyBytes);
            privateKey = kf.generatePrivate(privateSpec);

        }

        public static String encrypt(byte[] input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encrypted = cipher.doFinal(input);
            return Base64.getEncoder().encodeToString(encrypted);
        }

        public static byte[] decrypt(String base64Input) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(base64Input));
            return decrypted;
        }

    }

    //-------------- DES

    public static class TripleDESBase64{
        public static String encrypt(String plainText) throws Exception {
            final MessageDigest md = MessageDigest.getInstance("md5");
//            final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//                    .getBytes("utf-8"));

            final SecretKey key = new SecretKeySpec(
                    Base64.getDecoder().decode("6lRqh2yaYyc1NV2oCcqltC0hYp/9Lnn5"), "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            final byte[] plainTextBytes = plainText.getBytes("utf-8");
            final byte[] cipherText = cipher.doFinal(plainTextBytes);
            String base64EncodedCipherText = Base64.getEncoder().encodeToString(cipherText);
            return base64EncodedCipherText;
        }

        public static String decrypt(String base64CipherText) throws Exception {
            byte[] cipherbytes = Base64.getDecoder().decode(base64CipherText);
            final MessageDigest md = MessageDigest.getInstance("md5");
//            ede
            final SecretKey key = new SecretKeySpec(
                    Base64.getDecoder().decode("6lRqh2yaYyc1NV2oCcqltC0hYp/9Lnn5"),
                    "DESede"
            );
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, key, iv);
            final byte[] plainText = decipher.doFinal(cipherbytes);

            return new String(plainText, "UTF-8");
        }
    }

    //----------------- AES

    public static class AES {
        public static String initVector = "dec07f6e-3b47-42";
//        public static String key = "passwordpassword";
        public static String getKey(String sessionId){
            return sessionId.substring(0, 16);
        }

        public static String encrypt(String key, String initVector, String value) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

                byte[] encrypted = cipher.doFinal(value.getBytes());
                System.out.println("encrypted string: "+Base64.getEncoder().encodeToString(encrypted));

                return Base64.getEncoder().encodeToString(encrypted);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static byte[] encrypt(String key, String initVector, byte[] value) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

                byte[] encrypted = cipher.doFinal(value);

                return encrypted;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static String decrypt(String key, String initVector, String encrypted) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

                return new String(original);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static byte[] decrypt(String key, String initVector, byte[] encrypted) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                byte[] original = cipher.doFinal(encrypted);

                return original;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static String realContent(String content){
            return content.substring(content.indexOf(" ") + 1, content.length());
        }

        public static String getRandomKey() {
            return getRandomStr(16);
        }
    }


    public static class Dto {

        public static Object getOrderSourceObject(Object sourceObject, Message.Builder builder, String outputProtocol) {
            try {
                if (outputProtocol.trim().equals("application/json")){
                    return getJsonStr(sourceObject);
                }else if (outputProtocol.trim().equals("application/octet-stream")){
                    JsonFormat.parser().merge(getJsonStr(sourceObject), builder);
                    return builder.build().toByteString();
                }
                return null;
            }catch (InvalidProtocolBufferException e){
                Helper.appLogger.error("Dto -> getOrderSourceObject", e);
                return null;
            }
//            return null;
        }
    }

    public static class Calendar{


//        public static PersianDate getCurrentPersianDate() {
//            PersianCalendar persiancalendar = new PersianCalendar(new Date());
//            PersianDate persianDate = new PersianDate(
//                    (short)persiancalendar.get(PersianCalendar.YEAR),
//                    (byte)(persiancalendar.get(PersianCalendar.MONTH) +1),
//                    (byte)persiancalendar.get(PersianCalendar.DATE),
//                    (byte)persiancalendar.get(PersianCalendar.DAY_OF_WEEK),
//                    (byte)persiancalendar.get(PersianCalendar.HOUR),
//                    (byte)persiancalendar.get(PersianCalendar.MINUTE)
//            );
//            return persianDate;
//        }

        public static CustomDate getCurrentCustomDate(){

            java.util.Calendar calendar =  java.util.Calendar.getInstance();
            calendar.setTime(new Date());
            CustomDate customDate = new CustomDate(
                    (byte)((calendar.get(java.util.Calendar.DAY_OF_WEEK)==7)?0:calendar.get(java.util.Calendar.DAY_OF_WEEK)),
                    (byte)calendar.get(java.util.Calendar.HOUR_OF_DAY),
                    (byte)calendar.get(java.util.Calendar.MINUTE)
            );
            return customDate;
        }

        public static CustomDate getCustomDateFromMillies(long timeMillies){
            java.util.Calendar calendar =  java.util.Calendar.getInstance();
            calendar.setTime(new Date(timeMillies));
            CustomDate customDate = new CustomDate(
//                    calendar.get(java.util.Calendar.)
                    (byte)((calendar.get(java.util.Calendar.DAY_OF_WEEK)==7)?0:calendar.get(java.util.Calendar.DAY_OF_WEEK)),
                    (byte)calendar.get(java.util.Calendar.HOUR_OF_DAY),
                    (byte)calendar.get(java.util.Calendar.MINUTE)
            );
            return customDate;
        }

        public static Long getCurrentDayBaseTimeMillies(){
            java.util.Calendar calendar =  java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
            calendar.set(java.util.Calendar.HOUR, 0);
            calendar.set(java.util.Calendar.MINUTE, 0);
            calendar.set(java.util.Calendar.SECOND, 0);
            calendar.set(java.util.Calendar.MILLISECOND, 0);
            return calendar.getTimeInMillis();
        }

        public static Long getCustomDayBaseTimeMillies(long baseTime) {
            java.util.Calendar calendar =  java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));
            calendar.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
            calendar.setTimeInMillis(baseTime);
            calendar.set(java.util.Calendar.HOUR, 0);
            calendar.set(java.util.Calendar.MINUTE, 0);
            calendar.set(java.util.Calendar.SECOND, 0);
            calendar.set(java.util.Calendar.MILLISECOND, 0);
            return calendar.getTimeInMillis();
        }
    }

    public static String getBankName(Integer bankId){
        if (bankId == BankEnum.SADAD.getCode())
            return "بانک ملی";
        return null;
    }

    public static class ChangeTime {

        public static Long getBaseTimeAppend() {
            return Long.valueOf(2*60*60*1000);
        }

        public static Integer getFutureDays() {
            return 2;
        }
    }

    public static class ComboElementHelper {

        public static List<ComboElementDto> getRescheduleReasonList() {
            List<ComboElementDto> rescheduleDtoList =  new ArrayList<ComboElementDto>();
            rescheduleDtoList.add(new ComboElementDto(1, ComboMessages.Cancel.wrongOrder));
            rescheduleDtoList.add(new ComboElementDto(2, ComboMessages.Cancel.unsuitablePrice));
            rescheduleDtoList.add(new ComboElementDto(3, ComboMessages.Cancel.unsuitablePackage));
            rescheduleDtoList.add(new ComboElementDto(4, ComboMessages.Cancel.unsuitableDeliveryTime));
            rescheduleDtoList.add(new ComboElementDto(5, ComboMessages.Cancel.other));
            return rescheduleDtoList;
        }

        public static List<ComboElementDto> getCancelReasonList() {
            List<ComboElementDto> cancelReasonDtoList =  new ArrayList<ComboElementDto>();
            cancelReasonDtoList.add(new ComboElementDto(1, ComboMessages.Reschedule.notExistInAddress));
            cancelReasonDtoList.add(new ComboElementDto(2, ComboMessages.Reschedule.wannaSoonerTime));
            cancelReasonDtoList.add(new ComboElementDto(3, ComboMessages.Reschedule.wannaLaterTime));
            cancelReasonDtoList.add(new ComboElementDto(4, ComboMessages.Reschedule.other));
            return cancelReasonDtoList;
        }

        public static List<ComboElementDto> getProductFeedbackList() {
            List<ComboElementDto> productFeedbackList = new ArrayList<ComboElementDto>();
            productFeedbackList.add(new ComboElementDto(1, ComboMessages.ProductFeedback.placeHolder1));
            productFeedbackList.add(new ComboElementDto(2, ComboMessages.ProductFeedback.placeHolder2));
            productFeedbackList.add(new ComboElementDto(3, ComboMessages.ProductFeedback.placeHolder3));
            productFeedbackList.add(new ComboElementDto(4, ComboMessages.ProductFeedback.placeHolder4));
            return productFeedbackList;
        }

        public static List<ComboElementDto> setDeliveryFeedbackList() {
            List<ComboElementDto> deliveryFeedbackList = new ArrayList<ComboElementDto>();
            deliveryFeedbackList.add(new ComboElementDto(1, ComboMessages.ProductFeedback.placeHolder1));
            deliveryFeedbackList.add(new ComboElementDto(2, ComboMessages.ProductFeedback.placeHolder2));
            deliveryFeedbackList.add(new ComboElementDto(3, ComboMessages.ProductFeedback.placeHolder3));
            deliveryFeedbackList.add(new ComboElementDto(4, ComboMessages.ProductFeedback.placeHolder4));
            return deliveryFeedbackList;
        }

        public static List<ComboElementDto> setPackingFeedbackList() {
            List<ComboElementDto> packingFeedbackList = new ArrayList<ComboElementDto>();
            packingFeedbackList.add(new ComboElementDto(1, ComboMessages.ProductFeedback.placeHolder1));
            packingFeedbackList.add(new ComboElementDto(2, ComboMessages.ProductFeedback.placeHolder2));
            packingFeedbackList.add(new ComboElementDto(3, ComboMessages.ProductFeedback.placeHolder3));
            packingFeedbackList.add(new ComboElementDto(4, ComboMessages.ProductFeedback.placeHolder4));
            return packingFeedbackList;
        }
    }
}
