package ir.ord.application.dal.entities;

import ir.ord.application.accessories.DaoHelper;

/**
 * Created by vahid on 11/26/17.
 */
public class MessageEntity {
    private String _id = DaoHelper.getUUID();
    private Long startTime;
    private Long endTime;
    private String note;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
