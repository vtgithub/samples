package dto;

import java.io.Serializable;

/**
 * Created by vahid on 1/1/18.
 */
public class TestDto implements Serializable{

    private String value;
    private Long id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "value='" + value + '\'' +
                ", id=" + id +
                '}';
    }
}
