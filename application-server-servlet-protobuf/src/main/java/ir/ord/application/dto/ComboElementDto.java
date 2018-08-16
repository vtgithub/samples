package ir.ord.application.dto;

/**
 * Created by vahid on 10/2/17.
 */

public class ComboElementDto {
    private Integer id;
    private String val;

    public ComboElementDto() {
    }

    public ComboElementDto(Integer id, String val) {
        this.id = id;
        this.val = val;
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
