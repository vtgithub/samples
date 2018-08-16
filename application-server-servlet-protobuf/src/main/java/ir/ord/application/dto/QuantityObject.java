package ir.ord.application.dto;

import ir.ord.application.UnitEnum;

/**
 * Created by vahid on 12/10/17.
 */
public class QuantityObject {
    private Integer unit;
    private Integer value;

    public QuantityObject(UnitEnum unitEnum) {
        this.unit = unitEnum.getCode();
    }

    public QuantityObject() {
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
