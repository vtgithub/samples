package ir.ord.application.dal.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by vahid on 4/30/17.
 */
public class TestEntity implements Serializable {
//    private String _id = DaoHelper.getUUID();


    public TestEntity(String value, Object object) {
        this.value = value;
        this.object = object;
    }

    private String value;
    @JsonIgnore
    private Object object;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "value='" + value + '\'' +
                ", object=" + object +
                '}';
    }


    //    public String get_id() {
//        return _id;
//    }
//
//    public void set_id(String _id) {
//        this._id = _id;
//    }
}
