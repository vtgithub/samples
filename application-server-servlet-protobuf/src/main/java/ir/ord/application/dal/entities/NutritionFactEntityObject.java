package ir.ord.application.dal.entities;

import ir.ord.application.UnitEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 12/10/17.
 */
public class NutritionFactEntityObject {
    private QuantityEntityObject energy;
    private QuantityEntityObject totalFat;
    private QuantityEntityObject carbohydrate;
    private QuantityEntityObject sugar;
    private QuantityEntityObject protein;
    private QuantityEntityObject saturatedFattyAcids;
    private QuantityEntityObject transeFattyAcids;
    private QuantityEntityObject salt;
    private List<QuantityEntityObject> extraFields;

    public void newEnergy(){
        energy = new QuantityEntityObject(UnitEnum.cal);
    }

    public void newTotalFat(){
        totalFat = new QuantityEntityObject(UnitEnum.gr);
    }

    public void newCarbohydrate(){
        carbohydrate =  new QuantityEntityObject(UnitEnum.gr);
    }

    public void newSugar(){
        sugar =  new QuantityEntityObject(UnitEnum.gr);
    }

    public void newProtein(){
        protein =  new QuantityEntityObject(UnitEnum.gr);
    }

    public void newSaturatedFattyAcids(){
        saturatedFattyAcids =  new QuantityEntityObject(UnitEnum.gr);
    }

    public void newTranseFattyAcids(){
        transeFattyAcids =  new QuantityEntityObject(UnitEnum.gr);
    }

    public void newSalt(){
        salt =  new QuantityEntityObject(UnitEnum.mgr);
    }

    public void newExtraFields(){
        extraFields = new ArrayList<QuantityEntityObject>();
    }

    public QuantityEntityObject getEnergy() {
        return energy;
    }

    public void setEnergy(QuantityEntityObject energy) {
        this.energy = energy;
    }

    public QuantityEntityObject getTotalFat() {
        return totalFat;
    }

    public void setTotalFat(QuantityEntityObject totalFat) {
        this.totalFat = totalFat;
    }

    public QuantityEntityObject getCarbohydrate() {
        return carbohydrate;
    }

    public void setCarbohydrate(QuantityEntityObject carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    public QuantityEntityObject getSugar() {
        return sugar;
    }

    public void setSugar(QuantityEntityObject sugar) {
        this.sugar = sugar;
    }

    public QuantityEntityObject getProtein() {
        return protein;
    }

    public void setProtein(QuantityEntityObject protein) {
        this.protein = protein;
    }

    public QuantityEntityObject getSaturatedFattyAcids() {
        return saturatedFattyAcids;
    }

    public void setSaturatedFattyAcids(QuantityEntityObject saturatedFattyAcids) {
        this.saturatedFattyAcids = saturatedFattyAcids;
    }

    public QuantityEntityObject getTranseFattyAcids() {
        return transeFattyAcids;
    }

    public void setTranseFattyAcids(QuantityEntityObject transeFattyAcids) {
        this.transeFattyAcids = transeFattyAcids;
    }

    public QuantityEntityObject getSalt() {
        return salt;
    }

    public void setSalt(QuantityEntityObject salt) {
        this.salt = salt;
    }

    public List<QuantityEntityObject> getExtraFields() {
        return extraFields;
    }

    public void setExtraFields(List<QuantityEntityObject> extraFields) {
        this.extraFields = extraFields;
    }
}
