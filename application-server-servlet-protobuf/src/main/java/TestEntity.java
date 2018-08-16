import java.io.Serializable;

/**
 * Created by vahid on 6/19/17.
 */
public class TestEntity implements Serializable{
    private int id;
    private String value;


    public TestEntity(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
