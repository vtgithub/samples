package Accessories;

public class SmsResponse {

    private Integer status;
    private long msgIdArray;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public long getMsgIdArray() {
        return msgIdArray;
    }

    public void setMsgIdArray(long msgIdArray) {
        this.msgIdArray = msgIdArray;
    }

    @Override
    public String toString() {
        return "{" +
                "status=" + status +
                ", msgIdArray=" + msgIdArray +
                '}';
    }
}