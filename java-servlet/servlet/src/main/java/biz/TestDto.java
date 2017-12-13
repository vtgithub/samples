package biz;

/**
 * Created by vahid on 9/8/17.
 */
public class TestDto {
    private String code;
    private String value;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTestStr(){
        return "Ddd";
    }
    @Override
    public String toString() {
        return "TestDto{" +
                "code='" + code + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
