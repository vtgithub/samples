package ir.ord.application.test;


import ir.ord.application.accessories.CustomDate;
import ir.ord.application.accessories.Helper;
import ir.ord.application.dal.entities.TestEntity;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.io.IOUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;

import java.io.File;
import java.util.*;

/**
 * Created by vahid on 4/17/17.
 */
public class MainTest {
    public static void main(final String[] args) throws Exception {


        String s = "ddsdjh\n" +
                "sdsdshd\n" +
                "fdaa";
        System.out.println(s.replace("\n", ""));
//        Configuration configuration =  new Configuration();
//        configuration.addResource(new Path("/usr/local/hadoop-2.8.1/etc/hadoop/core-site.xml"));
//        configuration.addResource(new Path("/usr/local/hadoop-2.8.1/etc/hadoop/hdfs-site.xml"));
//        FileSystem fileSystem = FileSystem.get(configuration);
//
////        fileSystem.copyFromLocalFile(false, new Path("/home/vahid/test.sh"), new Path("/test2/sample.sh"));
////        fileSystem.delete(new Path("/test-app"), true);
//
//
////        single file
//        FileSystem fs = FileSystem.get(configuration);
//        FSDataInputStream inputStream = fs.open(new Path("/test2/java.mp4"));
////        inputStream.seek(0);
//        System.out.println("_____________________________________________");
//        IOUtils.copyBytes(inputStream, System.out, 10);
//        System.out.println("______________________________________________");
//        IOUtils.closeStream(inputStream);
//        fileSystem.close();
        
//        System.out.println(( Long.parseLong("1507119863149") - Helper.getCurrentTime())/60000);


//        list hdfs files
//        Path path = new Path("/test2");
//        if ( args.length == 1){
//            path = new Path(args[0]);
//        }
//        FileSystem fs = FileSystem.get(configuration);
//        FileStatus[] files = fs.listStatus(path);
//        for (FileStatus file : files ){
//            System.out.println(file.getPath().getName());
//        }


//        try{
//            Jedis jedis = new Jedis("localhost",3030);
////            jedis.auth("vahid12345");
//            jedis.hset("myHash2", "hashkey3", "d");
//            String hget = jedis.hget("myHash2", "hashkey3");
//            System.out.println(hget);
//        }catch (JedisConnectionException e){
//            System.out.println("connection failed");
//        }catch (JedisDataException e){
//            System.out.println("authentication failed");
//        }


//        java.util.Calendar calendar =  java.util.Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
//        calendar.set(java.util.Calendar.HOUR, 0);
//        calendar.set(java.util.Calendar.MINUTE, 0);
//        calendar.set(java.util.Calendar.SECOND, 0);
//        calendar.set(java.util.Calendar.MILLISECOND, 0);
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println(new Date(calendar.getTimeInMillis()));

        //        CustomDate currentCustomD
        // ate = Helper.Calendar.getCurrentCustomDate();
//        System.out.println(currentCustomDate.getMinute());
//        System.out.println(currentCustomDate.getHour());
//        System.out.println(currentCustomDate.getDayOfWeek());
//        System.out.println(Helper.getCurrentTime() - 15*60000);
//        System.out.println("1504827000000");
//        CustomDate customDateFromMillies = Helper.Calendar.getCustomDateFromMillies(Long.parseLong("1504827000000"));
//        System.out.println(customDateFromMillies.toString());
//
//        System.out.println(Helper.getCurrentTime());
//
//        System.out.println(Helper.Calendar.getCustomDateFromMillies(Helper.getCurrentTime()));
//        System.out.println(Helper.Calendar.getCustomDateFromMillies(Helper.getCurrentTime()+473776425));
//        System.out.println(24*60*60*1000);
//        System.out.println(Long.parseLong("1505300776425") - Long.parseLong("1504827000000"));
//        System.out.println(Helper.getPersianNumbers((long) 120870));
//        PaymentResponseObject paymentResponseObject = new PaymentResponseObject();
//        paymentResponseObject.setToken("dssds");
//        paymentResponseObject.setDescription("ede");
//        paymentResponseObject.setResCode("200");
//        System.out.println(Helper.getJsonStr(paymentResponseObject));

//        PersianDate persianDate = Helper.Calendar.getCurrentPersianDate();
//        System.out.println(persianDate.toString());

//        List<String> strings = new ArrayList<String>();
//        strings.add("a");
//        strings.add("b");
//        strings.add("c");
//        strings.add("d");
//        Collections.swap(strings, 0, 3);
//
//        for (String string : strings) {
//            System.out.println(string);
//        }

//        System.out.println(
// Helper.getCurrentTime());
//        System.out.println(Helper.getCurrentTime());
//        Calendar calendar = Calendar.getInstance();
//        System.out.println(calendar.getTimeInMillis());
//        System.out.println(Helper.Calendar.getCurrentDayBaseTimeMillies());

//        Jedis jedis = new Jedis("localhost", 3000);
//        jedis.auth("ord12345");java.util.Calendar
//        TestEntity testEntity = new TestEntity();
//        testEntity.setValue("val3");
//        testEntity.setObject("object3");
//        jedis.set("testKey".getBytes() ,Helper.BytesUtil.toByteArray(testEntity));
////        jedis.set("testKey", testEnt);
//        TestEntity retrieved = (TestEntity) Helper.BytesUtil.toObject(jedis.get("testKey".getBytes()));
//        System.out.println(retrieved.getValue());
//        System.out.println(retrieved.getObject());
//
//        jedis.hset("CategoryHash".getBytes(),"testKey".getBytes(), Helper.BytesUtil.toByteArray(testEntity));
//        TestEntity testEntity1 = (TestEntity) Helper.BytesUtil.toObject(jedis.hget("CategoryHash".getBytes(), "testKey".getBytes()));
//        System.out.println("________________"+testEntity1.getValue());
//


//        String test = "{\n" +
//                "    \"title\":\"عنوان\",\n" +
//                "    \"phone\":\"88229911\",\n" +
//                "    \"street\":\"بنفشه\"\n" +
//                "    \"alley\":\"مطهری\"\n" +
//                "    \"unit\":\"2\"\n" +
//                "    \"number\":\"43\"\n" +
//                "    location:{\n" +
//                "        \"longitude\":\"2314\",\n" +
//                "        \"latitude\":\"42340\"\n" +
//                "    }\n" +
//                "}";
//        Object objectFromJson = ComHelper.getObjectFromJson(test, AddressDto.class);
//
//        System.out.println(objectFromJson.toString());

//        System.out.println(Helper.getUUID());
//
//        AccountInfoProto.AccountInfo accountInfo = AccountInfoProto.AccountInfo.newBuilder().setFirstName("محمد").setLastName("پروشانی").build();
//        GiftProto.Gift gift = GiftProto.Gift.newBuilder().setCode("123").setValue(12000).setExpirationTime(Helper.getCurrentTime()+3600000).setIncludeOrExclude(false).build();
//        GiftProto.GiftRequest giftRequest = GiftProto.GiftRequest.newBuilder().setPhoneNumber("09354212425").setGiftDto(gift).build();
//        Map<String, String> headers = new HashMap<String, String>();
//        headers.put("Content-Type", "application/octet-stream");
//        headers.put("Accept", "application/json");
////        headers.put("sessionId", "2b519a1f-6c82-4977-89d9-c15fb610181b");
//        headers.put("sessionId", "9acb8f1d-3f03-4768-81d3-9000aea82108");

//        AccountInfoProto.AccountSignInRequest builder = AccountInfoProto.AccountSignInRequest.newBuilder().setDeviceId("123").setPhoneNumber("09354212425").build();
//        AccountInfoProto.AccountInfoRequest accountInfoRequest = AccountInfoProto.AccountInfoRequest.newBuilder().setFirstName("علی").setLastName("مطهری").build();
//        String accountInfoStr = "{\"firstName\":\"علی\", \"lastName\":\"مطهری\"}";
//        PackageProto.Package aPackage = PackageProto.Package.newBuilder().setCategoryId("f0a0cce3-fd92-47ef-9e7f-c6c400fdf6c2c693").setPrice(43820).setName("ppp").build();
//        TimePeriodProto.TimePeriodFillRequest timePeriodFillRequest = TimePeriodProto.TimePeriodFillRequest.newBuilder().setPeriod(1).setFrom(10).setTo(22).build();
//        System.out.println(
//                Helper.requestGenerator("POST",
//                        "http://localhost:8180/management-war/timePeriodFill",
//                        headers,
//                        timePeriodFillRequest.toByteArray()
//                )
//        );









//
//        GiftProto.Gift.Builder giftBuilder = GiftProto.Gift.newBuilder();
//        giftBuilder.setCode("1111");
//        giftBuilder.setAccountId("aaaa");
//        giftBuilder.setExpirationTime(2);
//        giftBuilder.setIncludeOrExclude(true);
//
//        System.out.println(
//                Helper.getJsonStr(
//                    ComHelper.getDtoObject(GiftConvertor.class.getName(), giftBuilder)
//                )
//        );

//
//        String s2 = "{\"list\":[{\"_id\":\"1\", \"name\":\"vahid\"}, {\"_id\":\"2\", \"name\":\"saeed\"}]}";
//        ObjectMapper mapper = new ObjectMapper();
//
//            CategoryProto.CategoryList.Builder builder = CategoryProto.CategoryList.newBuilder();
//            JsonFormat.parser().merge(s2, builder);
//            System.out.println(builder.toString());

//            ListObject listObject = mapper.readValue(s2, ListObject.class);
//            for (CategoryProto.Category.Builder builder1: builder.getListBuilderList())
//                System.out.println(builder1.toString());


//        CategoryProto.CategoryList.Builder listBuilder = CategoryProto.CategoryList.newBuilder();
//        CategoryProto.Category.Builder catBuilder = CategoryProto.Category.newBuilder();
//        catBuilder.setName("vahid");
//        catBuilder.setId("1");
//        listBuilder.addList(0, catBuilder);
//        listBuilder.addList(1, catBuilder);
//
//        System.out.println(JsonFormat.printer().print(listBuilder));


//        List<String> strings = new ArrayList<String>();
//        setList(strings);
//        System.out.println(Helper.getJsonStr(strings));
//
//        String var = "";
//        setVar(var);






//        String a = "cccccccccccc sssssssssssd";
//
//        a = a.substring(a.indexOf(" ") + 1, a.length());
//        System.out.println(a);

//        String key = "abcdabcdabcdabcd"; // 128 bit key
//        String key = "2b519a1f-6c82-49"; // 128 bit key


//         String encrypted = Helper.AES.encrypt(
//                        key,
//                        Helper.AES.initVector,
//                        "balbla 5unc54PApdQvLyzay3iS3S+Tj8rNBkR4wbdRIAAeda0udWJlZn8TaNOp7KNDeYNeEWxgwCQF5oTYacyocGGtP+Z5VLGNBeu16KMRecq6Hn8=");

//        String decrypted = Helper.AES.decrypt(
//                key,
//                Helper.AES.initVector,
//                encrypted
//        );
//        System.out.println(encrypted);
//        System.out.println(decrypted);

//        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        final ScheduledFuture<?> future = executor.schedule(new Runnable() {
//            public void run() {
//                System.out.println("this will shutdown echeduleExecutorService");
//
//                executor.shutdown();
//
//            }
//        }, 10, TimeUnit.SECONDS);
//        System.out.println("_____");


//        NotificationObject notificationObject = new NotificationObject();
//        notificationObject.setContents("content ....");
//        notificationObject.setHeadings("heading ...");
//        List<String> tokentList = new ArrayList<String>();
//        tokentList.add("t1");
//        tokentList.add("t2");
//        notificationObject.setInclude_ios_tokens(tokentList);
//        notificationObject.setSubtitle("subtitle ...");
//        List<String> segList = new ArrayList<String>();
//        segList.add("b0148b9ba9855c33e9abe77bfa7a916741f9dd51f7266b4a41a2b8c62a362e38");
//        Helper.sendNotificationToUser("title","subtitle", "message", segList);

//        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
//
//        exec.schedule(new Runnable() {
//            public void run() {
//                System.out.println("++++++++++++++++++");
//            }
//        }, 4, TimeUnit.SECONDS);

//        System.out.println(FollowUpState.FOLLOW_SMS_SENDING_FAILED.toString());
//        final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        final ScheduledFuture<?> future = executor.scheduleAtFixedRate(new Runnable() {
//            public void run() {
//                System.out.println("this will printed every 3 second ...");
//
////                executor.shutdown();
//
//            }
//        },0, 3, TimeUnit.SECONDS);
//
//
//        System.out.println("continue ... ");




//        followOrder("6fca6187-57c4-40b4-91a2-86797262b06b", 10);
//        String test = "http://localhost:8180/application-war/account/signUp";
//        System.out.println(test.contains("signUp"));
//        String inputString = "{ \"_id\" : \"fa1cc0e1-c693-4453-b110-a9af1c99b83e\", \"timePeriodObjectList\" : [{ }], \"demandTime\" : { \"$numberLong\" : \"1493658734492\" }, \"statusObjectList\" : [{ \"timeStamp\" : { \"$numberLong\" : \"1493658737669\" }, \"state\" : 1, \"description\" : \"has been bought\", \"actor\" : \"you\" }], \"packageEntity\" : { \"_id\" : \"c1007307-d956-45d7-9174-1e9b9c411e94\", \"name\" : \"pack4\", \"imageUrl\" : \"url1\", \"price\" : 10.0, \"categoryId\" : \"213123fd\", \"description\" : \"des4\" }, \"sourceType\" : 2, \"accountId\" : \"711c3934-8c18-454e-b57d-5d1290fed4fb\", \"addressObject\" : { \"buttonId\" : \"123123\", \"locationObject\" : { \"x\" : 34.0, \"y\" : 21.0 }, \"timePeriodEntityList\" : [{ \"weekDay\" : 1, \"fromTime\" : 1, \"toTime\" : 40000 }] } }";
//        System.out.println(inputString);
//        System.out.println(DaoHelper.getSafeJsonString(inputString));

//        secondChuck = secondChuck.substring(secondChuck.indexOf("}")+2);
//        System.out.println(firstChuck);
//        System.out.println(secondChuck);
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(firstCh  uck);
//        stringBuilder.append(secondChuck);
//        System.out.println(inputString);
//        System.out.println(stringBuilder.toString());

//        Map<String, String > entityList = new HashMap<String, String>();
//        entityList.put("sss", "DSDSD");
//        entityList.put("rwer", "swer");
//
//        System.out.println(Helper.getJsonStr(entityList));

//        Set<String> strings = new HashSet<String>();
//        strings.add("DDDD");
//        strings.add("VVVV");
//
//        System.out.println(StringUtils.join(strings,","));
        //-------------------------------------------------------
//        String jsonString = "{\"ti\":{\"z\":\"zVal\"},\"creationTime\":{\"$numberLong\":\"123123123123123\"} ,\"x\":{\"y\":\"yVal\"}}";
//        jsonString = jsonString.replace("{\"$numberLong\":", "");
//        int openCounter = 0;
//        StringBuilder stringBuilder = new StringBuilder(jsonString);
//        for (int i = 0 ; i <stringBuilder.length() ; i++){
//            if(stringBuilder.charAt(i) == '{')
//                openCounter ++;
//            if(stringBuilder.charAt(i) == '}')
//                openCounter--;
//            if (openCounter == 0 && i != (stringBuilder.length()-1)){
//                stringBuilder.deleteCharAt(i);
//                openCounter++;
//            }
//
//        }
//        System.out.println(stringBuilder);
        //--------------------------------------------------------
//            ButtonActivationEntity buttonActivationEntity = new ButtonActivationEntity();
//            buttonActivationEntity.setActivationCode("23123");
////            buttonActivationEntity.setActivatorIP("2311231");
//            buttonActivationEntity.setCreationTime((long) 1222222);
//            buttonActivationEntity.setUsed(false);



//        System.out.println(Helper.executeCommand("ping -c 3 8.8.8.8"));
//        Helper.executeCommand("java -jar /opt/jars/sms.jar 09354212425 fdfdf");
//        String phoneNumber = "09123339922";
//        System.out.println(phoneNumber.matches("^09\\d{9}$"));

//        Random random = new Random(System.currentTimeMillis());
//        System.out.println(random.nextInt(99999));
//        Map<String, String> headerParamMap = new HashMap<String, String>();
//        headerParamMap.put("Content-Type", "*/*");
//        headerParamMap.put("deviceId", ("deviceId"));
//        headerParamMap.put("deviceVersion", ("deviceVersion"));
//        headerParamMap.put("bundleId", ("bundleId"));
//        headerParamMap.put("responseUrl", "http://127.0.0.1:8081/proxy-war/app-service/button");
//        headerParamMap.put("key", "UUID");
//
//        try {
//            Helper.postRequestGenerator("http://localhost:8180/application-war/button-service/button", headerParamMap, "sdsadas".getBytes());
//        } catch (RequestSendingToProxyServerException e) {
//            e.printStackTrace();
//        } catch (PostRequestSendingException e) {
//            e.printStackTrace();
//        }

//        Helper.sendSMS("09354212425", "wwww...");

//        CacheRemote cacheInitializerBizRemote = lookupRemoteCacheInitializerBiz();
//        cacheInitializerBizRemote.initCache();
    }

//    private static CacheRemote lookupRemoteCacheInitializerBiz() throws NamingException {
//        final Hashtable jndiProperties = new Hashtable();
//        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//         Context context = new InitialContext(jndiProperties);
//         String appName = "";//application-war
//         String moduleName = "application-war";
//         String distinctName = "";
//         String beanName = CacheInitializerBiz.class.getSimpleName();
//         String viewClassName = CacheRemote.class.getName();
//        String contextLookupSrt = "ejb:" + appName + "/" + moduleName  + "/" + beanName + "!" + viewClassName;
//        System.out.println(contextLookupSrt);
//        return (CacheRemote) context.lookup(contextLookupSrt);
//
//    }
}
