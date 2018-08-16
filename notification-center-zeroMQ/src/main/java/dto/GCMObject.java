package dto;

import java.util.List;

/**
 * Created by vahid on 11/11/17.
 */
public class GCMObject {
    private String to;
    private List<String>  registration_ids; // for more than 1 less than 1000
    private String priority;// high(10) & normal(5)
    private Boolean content_available;
    //private Integer time_to_live;  //default 4 weeks
//    private String restricted_package_name; //package name of the application where the registration tokens must match in order to receive the message.
    private DataObject data = new DataObject();
    private Notification notification = new Notification();

    public String getTo() {
        return to;
    }

    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    public String getPriority() {
        return priority;
    }

    public Boolean getContent_available() {
        return content_available;
    }

    public DataObject getData() {
        return data;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setContent_available(Boolean content_available) {
        this.content_available = content_available;
    }

    public void setData(Integer type, Integer event, String id) {
        this.data.setType(type);
        this.data.setEvent(event);
        if (id != null && !id.equals(""))
            this.data.setId(id);
    }

    public void setNotification(String title, String bodyMessage){
        this.notification.setTitle(title);
        this.notification.setBody(bodyMessage);
    }

    // helper classes
    private class Notification {
        private String title;
        private String body;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
