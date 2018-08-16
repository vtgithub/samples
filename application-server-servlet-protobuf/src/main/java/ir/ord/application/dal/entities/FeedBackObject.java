package ir.ord.application.dal.entities;

import java.util.List;
import java.util.Map;

/**
 * Created by vahid on 10/3/17.
 */
public class FeedBackObject {
    private Integer satisfactionLevel;
    private String opinion;
    private List<OptionObject> optionObjectList;

    public Integer getSatisfactionLevel() {
        return satisfactionLevel;
    }

    public void setSatisfactionLevel(Integer satisfactionLevel) {
        this.satisfactionLevel = satisfactionLevel;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public List<OptionObject> getOptionObjectList() {
        return optionObjectList;
    }

    public void setOptionObjectList(List<OptionObject> optionObjectList) {
        this.optionObjectList = optionObjectList;
    }
}
