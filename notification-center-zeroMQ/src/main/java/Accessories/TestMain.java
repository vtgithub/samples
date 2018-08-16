package Accessories;

import dto.GCMObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 11/7/17.
 */
public class TestMain {
    public static void main(String[] args) {
//        Helper.Notification.sendAndroidNotif("", "");
//        GCMObject gcmObject = new GCMObject();
//        gcmObject.setData(Integer.valueOf(NotifType.NOTIFICATION.getCode()), 2, null);
//        gcmObject.setContent_available(false);
//        gcmObject.setNotification("title_____txt", "body_____txt");
//        System.out.println(Helper.getJsonStr(gcmObject));

        List<String> segments = new ArrayList<String>();
        segments.add("fERc-ZZq220:APA91bHX1BWSPUBuyb1IF_a3IsnIxU3pfGUDW6mNXGnE0sYGXRXIjXzZTMjtm1GY5aGdQixcCJbItGwBSUSmumbCNY6x57t-S3SFZuIqdy9KJ711Js-DiY_IupOhXVjle6mm0dABaVxK");
        segments.add("fX19Pc87838:APA91bELKw-nCqW1qhvCHLBtj5ctNrWV6mLTaoq11-ExU0PdOgREajwPDLrcKu1HJZxcghLGN5xewvDoG7g8aMexTz8fIpcaNlcbkc4fl2zTDk9QqarOvK0UbYaaX7vMBhngXPzo75sp");
        Integer responseCode = Helper.Notification.sendNotif(PlatformEnum.ANDROID.getCode(), "test title", "test subtitle", "test message", segments);
        System.out.println(responseCode);
    }
}
