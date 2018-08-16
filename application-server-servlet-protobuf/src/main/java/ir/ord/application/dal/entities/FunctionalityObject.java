package ir.ord.application.dal.entities;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

/**
 * Created by vahid on 5/6/17.
 */
public class FunctionalityObject {

    private String catId;
    private Integer funcNumber;
    private List<Functionality> functionalityList;


    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Integer getFuncNumber() {
        return funcNumber;
    }

    public void setFuncNumber(Integer funcNumber) {
        this.funcNumber = funcNumber;
    }

    public List<Functionality> getFunctionalityList() {
        return functionalityList;
    }

    public void setFunctionalityList(List<Functionality> functionalityList) {
        this.functionalityList = functionalityList;
    }
}
