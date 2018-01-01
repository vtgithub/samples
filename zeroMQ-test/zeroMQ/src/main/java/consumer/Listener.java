package consumer;


import dto.TestDto;
import org.apache.commons.lang.SerializationUtils;
import org.zeromq.ZMQ;

/**
 * Created by vahid on 6/19/17.
 */
public class Listener {
//    public static Logger logger = Logger.getLogger("notifLogger");

    public static void main(String[] args)  {


        ZMQ.Context zmqContext = ZMQ.context(20);
        ZMQ.Socket socket = zmqContext.socket(ZMQ.PULL);
        socket.bind ("tcp://127.0.0.1:5555");
//        NotificationProto.Request.Builder requestBuilder =null;

        try {
            while (!Thread.currentThread ().isInterrupted ()) {
                byte[] reply = socket.recv(1);
                if (reply != null){
                    TestDto testDto = (TestDto) SerializationUtils.deserialize(reply);
                    System.out.println(testDto);
                }
//                requestBuilder = NotificationProto.Request.parseFrom(reply).toBuilder();
//                if (requestBuilder.getType() == NotifType.NOTIFICATION.getCode()){
//
//                    NotificationProto.Notif.Builder notifBuilder =
//                            NotificationProto.Notif.parseFrom(requestBuilder.getBody()).toBuilder();
//
//                    Helper.Notification.sendNotif(
//                            notifBuilder.getPlatform(),
//                            notifBuilder.getTitle(),
//                            notifBuilder.getSubtitle(),
//                            notifBuilder.getMessage(),
//                            Helper.getStringList(notifBuilder.getSegmentsList())
//                    );
//
//
//                }else if (requestBuilder.getType() == NotifType.CONTENT_UPDATE.getCode()){
//
//                    NotificationProto.ContentUpdateNotif.Builder contentUpdateBuilder =
//                            NotificationProto.ContentUpdateNotif.parseFrom(requestBuilder.getBody()).toBuilder();
//
//                    Helper.Notification.sendContentUpdate(
//                            contentUpdateBuilder.getPlatform(),
//                            contentUpdateBuilder.getType(),
//                            contentUpdateBuilder.getEvent(),
//                            contentUpdateBuilder.getPriority(),
//                            contentUpdateBuilder.getId(),
//                            Helper.getStringList(contentUpdateBuilder.getSegmentsList())
//                    );
//
//                }else if(requestBuilder.getType() == NotifType.SMS.getCode()){
//
//                    NotificationProto.SMS.Builder smsBuilder =
//                            NotificationProto.SMS.parseFrom(requestBuilder.getBody()).toBuilder();
//                    SmsResponse smsResponse = Helper.Notification.sendPureSMS(
//                            smsBuilder.getDestinationPhoneNumber(),
//                            smsBuilder.getBody()
//                    );
//                    String smsErrorMessage = Helper.getSmsErrorMessage(smsResponse);
//                    if (smsErrorMessage != null)
//                        logger.error(smsErrorMessage);
//
//                }

            }
        } catch(Exception e) {
            e.printStackTrace();
//            if (requestBuilder ==null)
//                logger.error(e);
//            else
//                logger.error(Helper.getNotifTypeStr((byte) requestBuilder.getType())+" ------> ",e);
        } finally {
            socket.close();
        }
    }
}
