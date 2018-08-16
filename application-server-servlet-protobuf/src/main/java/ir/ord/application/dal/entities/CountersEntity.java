package ir.ord.application.dal.entities;

/**
 * Created by vahid on 5/24/17.
 */
public class CountersEntity {
    private String _id ;
    private Long seq;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
