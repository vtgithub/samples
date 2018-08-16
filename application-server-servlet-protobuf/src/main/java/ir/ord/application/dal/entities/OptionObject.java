package ir.ord.application.dal.entities;

/**
 * Created by vahid on 10/3/17.
 */
public class OptionObject {
    private Integer id;
    private String val;

    public OptionObject(Integer id, String val) {
        this.id = id;
        this.val = val;
    }

    public OptionObject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}
