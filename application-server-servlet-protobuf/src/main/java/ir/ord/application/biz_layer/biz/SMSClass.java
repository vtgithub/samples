package ir.ord.application.biz_layer.biz;

/**
 * Created by vahid on 6/17/17.
 */
public class SMSClass {
    private String phoneNumber;
    private String body;

    public SMSClass(String phoneNumber, String body) {
        this.phoneNumber = phoneNumber;
        this.body = body;
    }

    public SMSClass() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
