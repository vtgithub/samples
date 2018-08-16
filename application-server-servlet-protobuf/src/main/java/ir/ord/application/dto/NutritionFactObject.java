package ir.ord.application.dto;

import ir.ord.application.UnitEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 12/10/17.
 */
public class NutritionFactObject {
    private QuantityObject energy;
    private QuantityObject totalFat;
    private QuantityObject carbohydrate;
    private QuantityObject sugar;
    private QuantityObject protein;
    private QuantityObject saturatedFattyAcids;
    private QuantityObject transeFattyAcids;
    private QuantityObject salt;
    private List<QuantityObject> extraFields;

    public void newExtraFields(){
        extraFields = new ArrayList<QuantityObject>();
    }
    public void newEnergy(){
        energy = new QuantityObject(UnitEnum.cal);
    }
    public void newTotalFat(){
        totalFat = new QuantityObject(UnitEnum.gr);
    }
    public void newCarbohydrate(){
        carbohydrate =  new QuantityObject(UnitEnum.gr);
    }
    public void newSugar(){
        sugar =  new QuantityObject(UnitEnum.gr);
    }
    public void newProtein(){
        protein =  new QuantityObject(UnitEnum.gr);
    }
    public void newSaturatedFattyAcids(){
        saturatedFattyAcids =  new QuantityObject(UnitEnum.gr);
    }
    public void newTranseFattyAcids(){
        transeFattyAcids =  new QuantityObject(UnitEnum.gr);
    }
    public void newSalt(){
        salt=  new QuantityObject(UnitEnum.mgr);
    }

    public QuantityObject getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy.setValue(energy);
    }

    public QuantityObject getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(Integer totalFat) {
        this.totalFat.setValue(totalFat);
    }

    public QuantityObject getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(Integer carbohydrate) {
        this.carbohydrate.setValue(carbohydrate);
    }

    public QuantityObject getSugar() {
        return sugar;
    }

    public void setSugar(Integer sugar) {
        this.sugar.setValue(sugar);
    }

    public QuantityObject getProtein() {
        return protein;
    }

    public void setProtein(Integer protein) {
        this.protein.setValue(protein);
    }

    public QuantityObject getSaturatedFattyAcids() {
        return saturatedFattyAcids;
    }

    public void setSaturatedFattyAcids(Integer saturatedFattyAcids) {
        this.saturatedFattyAcids.setValue(saturatedFattyAcids);
    }

    public QuantityObject getTranseFattyAcids() {
        return transeFattyAcids;
    }

    public void setTranseFattyAcids(Integer transeFattyAcids) {
        this.transeFattyAcids.setValue(transeFattyAcids);
    }

    public QuantityObject getSalt() {
        return salt;
    }

    public void setSalt(Integer salt) {
        this.salt.setValue(salt);
    }

    public List<QuantityObject> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(List<QuantityObject> extraFields) {
        this.extraFields = extraFields;
    }
}
