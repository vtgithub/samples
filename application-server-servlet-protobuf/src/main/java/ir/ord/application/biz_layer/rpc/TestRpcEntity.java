package ir.ord.application.biz_layer.rpc;

import java.io.Serializable;

/**
 * Created by vahid on 7/24/17.
 */
public class TestRpcEntity implements Serializable{
    private String value;
    private Integer id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
