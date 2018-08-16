package ir.ord.application.dto;

import ir.ord.application.dal.entities.OptionObject;

import java.util.List;

/**
 * Created by vahid on 10/3/17.
 */
public class FeedbackObjectDto {
    private Integer satisfactionLevel;
    private String opinion;
    private List<ComboElementDto> comboElementList;

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

    public List<ComboElementDto> getComboElementList() {
        return comboElementList;
    }

    public void setComboElementList(List<ComboElementDto> comboElementList) {
        this.comboElementList = comboElementList;
    }
}
