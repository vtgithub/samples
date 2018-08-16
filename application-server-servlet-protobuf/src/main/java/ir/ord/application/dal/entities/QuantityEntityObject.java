package ir.ord.application.dal.entities;

import ir.ord.application.UnitEnum;

/**
 * Created by vahid on 12/10/17.
 */
public class QuantityEntityObject {
    private Integer unit;
    private Integer value;

    public QuantityEntityObject(UnitEnum unitEnum) {
        this.unit = unitEnum.getCode();
    }

    public QuantityEntityObject() {
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
