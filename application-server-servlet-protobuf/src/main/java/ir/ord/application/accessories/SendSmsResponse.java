
package ir.ord.application.accessories;

import javax.xml.bind.annotation.*;

/**
 * Created by vahid on 5/10/17.
 */
public class SendSmsResponse {


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
}
