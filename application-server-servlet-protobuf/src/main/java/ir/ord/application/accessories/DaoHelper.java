package ir.ord.application.accessories;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import ir.ord.application.dal.dao.*;
import ir.ord.application.dal.entities.*;
import redis.clients.jedis.Jedis;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 4/30/17.
 */
public class DaoHelper {

//    static Jedis jedis = new Jedis("localhost", 3000);
    // ActiveSessions hash table in redis
    static List<ActivationEntity> activationEntityList;
    static Jedis jedis = new Jedis("localhost",3030);
    static private MongoDatabase db = (new MongoClient("localhost",27017)).getDatabase("ord");

    static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    static Map<Object, String> mongoEntityMap = new HashMap<Object, String>();
    static Map<String, Map<String, Object>> cacheMap;
    static Map<Object, Object> entityDao = new HashMap<Object, Object>();
//    static String applicationServerAddress = "http://localhost:8180/application-war";
    static String applicationServerAddress = "http://172.22.32.16:8180/application-war/rest";

//    static {jedis.auth("ord12345");}
    static {
        activationEntityList = new ArrayList<ActivationEntity>();
        ActivationEntity activationEntity = new ActivationEntity();
        activationEntity.setPhoneNumber("09000000000");
        activationEntity.setActivationCode("123321");
        activationEntity.setCreationTime(null);
        activationEntity.setUsed(false);
        activationEntity.setDeviceId("1234554321");
        activationEntityList.add(activationEntity);
    }

    static {
        entityDao.put(ActivationDaoMongoImpl.class, ActivationEntity.class);
        entityDao.put(SessionInfoDaoMongoImpl.class, SessionInfoEntity.class);
        entityDao.put(AccountInfoDaoMongoImpl.class, AccountInfoEntity.class);
        entityDao.put(ButtonActivationDaoMongoImpl.class, ButtonActivationEntity.class);
        entityDao.put(ButtonDaoMongoImpl.class, ButtonEntity.class);
        entityDao.put(CategoryDaoMongoImpl.class, CategoryEntity.class);
        entityDao.put(CreditDaoMongoImpl.class, CreditEntity.class);
        entityDao.put(GiftDaoMongoImpl.class, GiftEntity.class);
        entityDao.put(OrderDaoMongoImpl.class, OrderEntity.class);
        entityDao.put(PackageDaoMongoImpl.class, PackageEntity.class);
        entityDao.put(TimePeriodDaoMongoImpl.class, TimePeriodEntity.class);
        entityDao.put(BankPaymentDaoMongoImpl.class, BankPaymentEntity.class);
        entityDao.put(UnpaiedDaoMongoImpl.class, UnpaiedEntity.class);
        entityDao.put(PhoneLogDaoMongoImpl.class, PhoneLogEntity.class);
        entityDao.put(QRDaoMongoImpl.class, QREntity.class);
        entityDao.put(CountersDaoMongoImpl.class, CountersEntity.class);
        entityDao.put(TestDaoMongoImpl.class, TestEntity.class);
        entityDao.put(ProductDaoMongoImpl.class, ProductEntity.class);
    }


    static {
        mongoEntityMap.put(ActivationDaoMongoImpl.class, "ActivationEntity");
        mongoEntityMap.put(SessionInfoDaoMongoImpl.class, "SessionInfoEntity");
        mongoEntityMap.put(AccountInfoDaoMongoImpl.class, "AccountInfoEntity");
        mongoEntityMap.put(ButtonActivationDaoMongoImpl.class, "ButtonActivationEntity");
        mongoEntityMap.put(ButtonDaoMongoImpl.class, "ButtonEntity");
        mongoEntityMap.put(CategoryDaoMongoImpl.class, "CategoryEntity");
        mongoEntityMap.put(CreditDaoMongoImpl.class, "CreditEntity");
        mongoEntityMap.put(GiftDaoMongoImpl.class, "GiftEntity");
        mongoEntityMap.put(OrderDaoMongoImpl.class, "OrderEntity");
        mongoEntityMap.put(PackageDaoMongoImpl.class, "PackageEntity");
        mongoEntityMap.put(TimePeriodDaoMongoImpl.class, "TimePeriodEntity");
        mongoEntityMap.put(BankPaymentDaoMongoImpl.class, "BankPaymentEntity");
        mongoEntityMap.put(UnpaiedDaoMongoImpl.class, "UnpaiedEntity");
        mongoEntityMap.put(PhoneLogDaoMongoImpl.class, "PhoneLogEntity");
        mongoEntityMap.put(QRDaoMongoImpl.class, "QREntity");
        mongoEntityMap.put(CountersDaoMongoImpl.class, "CountersEntity");
        mongoEntityMap.put(TestDaoMongoImpl.class, "TestEntity");
        mongoEntityMap.put(ProductDaoMongoImpl.class, "ProductEntity");
    }


    public static Jedis getRedisDb(){
        return jedis;
    }

    public static void reconnectRedis(){
        jedis = new Jedis("localhost",3030);
    }
    public static MongoDatabase getMongoDb(){
        return db;
    }
    public static Map<String, Map<String, Object>> getCacheMap(){
        return cacheMap;
    }
    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static Class getEntityClass(Class clazz){
        Class aClass = (Class) entityDao.get(clazz);
        return aClass;
    }

    public static String getMongoEntityClassName(Class clazz) {
        return mongoEntityMap.get(clazz);
    }

    public static String getEntityJson(Object o){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            Helper.appLogger.error("getEntityJson",e);

        }
        return null;
    }

    public static Object getObjectFromJson(String jsonString, Class classType){
        ObjectMapper objectMapper = new ObjectMapper();
        jsonString = getSafeJsonString(jsonString);
//        String oooo="";
        try {
            return objectMapper.readValue(jsonString, classType);
        } catch (IOException e) {
//            oooo = e.getMessage();
            Helper.appLogger.error("getObjectFromJson",e);
        }
        return null;
    }


    public static Boolean isEntityValid(Object entityObject) throws DaoException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate( entityObject);
        System.out.println(constraintViolations.size());
        if(constraintViolations.size()>0)
            throw new DaoException();
        return !(constraintViolations.size()>0);

    }

    public static String getSafeJsonString(String inputString){

        if(!inputString.contains("{ \"$numberLong\" :"))
            return inputString;

        String firstChuck = inputString.substring(0 ,inputString.indexOf("{ \"$numberLong\" :") );
        String secondChuck = inputString.substring(inputString.indexOf(
                "{ \"$numberLong\" :")+ "{ \"$numberLong\" :".length(), inputString.length()
        );
        String secondChunckFirstChunck = secondChuck.substring(0, secondChuck.indexOf("}"));
        String secondChunckSecondChunck = secondChuck.substring(secondChuck.indexOf("}")+1);
        secondChuck = secondChunckFirstChunck + secondChunckSecondChunck;

        return firstChuck + getSafeJsonString(secondChuck);
    }

    public static Long getCurrentTime() {
        return System.currentTimeMillis();
    }

// ------- for bank
    public static String getMercahntId() {
        return "000000140189456";
    }
    public static String getTerminalId() {
        return "24000408";
    }

    public static String getJsonDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getBankReturnUrl() {
        return applicationServerAddress + "/payment/fromUser";
    }

    public static String getSignedRequest(String terminalId, Long orderId, String amount){
        StringBuilder unsignedData = new StringBuilder();
        unsignedData.append(terminalId);
        unsignedData.append(";");
        unsignedData.append(orderId);
        unsignedData.append(";");
        unsignedData.append(amount);
        try {
            return Helper.TripleDESBase64.encrypt(unsignedData.toString());
        } catch (Exception e) {
            Helper.appLogger.error("getSignedRequest", e);
            return null;
        }
    }

    public static String getVerificationSignData(String plainText){
        try {
            return Helper.TripleDESBase64.encrypt(plainText);
        } catch (Exception e) {
            Helper.appLogger.error("getVerificationSignData", e);
            return null;
        }
    }

    public static void calCallableList(List<Callable<Void>> callableList) {
        for (Callable<Void> callable : callableList) {
            try {
                callable.call();
            } catch (Exception e) {
                Helper.appLogger.error("getVerificationSignData", e);
            }
        }
    }

    public static Object getNewInstanceFromExisting(Object entity, Class aClass) {
        if(entity == null)
            return null;
        String entityJson = getEntityJson(entity);
        Object objectFromJson = getObjectFromJson(entityJson, aClass);
        return objectFromJson;
//        Object newInstance = aClass.newInstance();
//        for(PropertyDescriptor propertyDescriptor : Introspector.getBeanInfo(aClass).getPropertyDescriptors()){
//            System.out.println(propertyDescriptor.getReadMethod());
//        }
//        return null;
    }

    public static Long getActivationCodeExpirationMillies() {
        return Long.valueOf(30*60*1000);
    }

    public static Long getOneDayMillies(){
        return Long.valueOf(24*60*60*1000);
    }

    public static List<Object> getObjectListFromByteMap(Map<byte[], byte[]> byteMap) throws IOException, ClassNotFoundException {
        if (byteMap == null)
            return null;
        List<Object> objectList = new ArrayList<Object>();
        for(Map.Entry<byte[], byte[]> byteEntry: byteMap.entrySet()){
            objectList.add(BytesUtil.toObject(byteEntry.getValue()));
        }
        return objectList;
    }

    public static String getActiveSessionsHashName() {
        return "ActiveSessions";
    }

    public static List<ActivationEntity> getActivationWhiteList() {
        return activationEntityList;

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
}
