package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.NutritionFactEntityObject;
import ir.ord.application.dto.NutritionFactObject;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 12/23/17.
 */
@ApplicationScoped
public class NutritionFactConverter {

    @Inject
    private QuantityConverter quantityConverter;

    public NutritionFactObject getDto(NutritionFactEntityObject nutritionFactEntityObject){
        if (nutritionFactEntityObject == null)
            return null;
        NutritionFactObject nutritionFactObject = new NutritionFactObject();
        if (nutritionFactEntityObject.getCarbohydrate() != null) {
            nutritionFactObject.newCarbohydrate();
            nutritionFactObject.setCarbohydrate(nutritionFactEntityObject.getCarbohydrate().getValue());
        }
        if (nutritionFactEntityObject.getEnergy() != null){
            nutritionFactObject.newEnergy();
            nutritionFactObject.setEnergy(nutritionFactEntityObject.getEnergy().getValue());
        }
        if (nutritionFactEntityObject.getProtein() != null){
            nutritionFactObject.newProtein();
            nutritionFactObject.setProtein(nutritionFactEntityObject.getProtein().getValue());
        }
        if (nutritionFactEntityObject.getSalt() != null){
            nutritionFactObject.newSalt();
            nutritionFactObject.setSalt(nutritionFactEntityObject.getSalt().getValue());
        }
        if (nutritionFactEntityObject.getSaturatedFattyAcids() != null){
            nutritionFactObject.newSaturatedFattyAcids();
            nutritionFactObject.setSaturatedFattyAcids(nutritionFactEntityObject.getSaturatedFattyAcids().getValue());
        }
        if (nutritionFactEntityObject.getSugar() != null){
            nutritionFactObject.newSugar();
            nutritionFactObject.setSugar(nutritionFactEntityObject.getSugar().getValue());
        }
        if (nutritionFactEntityObject.getTotalFat() != null){
            nutritionFactObject.newTotalFat();
            nutritionFactObject.setTotalFat(nutritionFactEntityObject.getTotalFat().getValue());
        }
        if (nutritionFactEntityObject.getTranseFattyAcids() != null){
            nutritionFactObject.newTranseFattyAcids();
            nutritionFactObject.setTranseFattyAcids(nutritionFactObject.getTranseFattyAcids().getValue());
        }
        if(nutritionFactEntityObject.getExtraFields() != null && nutritionFactEntityObject.getExtraFields().size()!=0){
            nutritionFactObject.newExtraFields();
            nutritionFactObject.setExtraFields(quantityConverter.getDtoList(nutritionFactEntityObject.getExtraFields()));
        }
        return nutritionFactObject;
    }

    public NutritionFactEntityObject getEntity(NutritionFactObject nutritionFactObject){
        if (nutritionFactObject == null)
            return null;
        NutritionFactEntityObject nutritionFactEntityObject = new NutritionFactEntityObject();
        if (nutritionFactObject.getCarbohydrate() != null) {
            nutritionFactEntityObject.newCarbohydrate();
            nutritionFactEntityObject.getCarbohydrate().setValue(nutritionFactObject.getCarbohydrate().getValue());
        }
        if (nutritionFactObject.getEnergy() != null){
            nutritionFactEntityObject.newEnergy();
            nutritionFactEntityObject.getEnergy().setValue(nutritionFactObject.getEnergy().getValue());
        }
        if (nutritionFactObject.getProtein() != null){
            nutritionFactEntityObject.newProtein();
            nutritionFactEntityObject.getProtein().setValue(nutritionFactObject.getProtein().getValue());
        }
        if (nutritionFactObject.getSalt() != null){
            nutritionFactEntityObject.newSalt();
            nutritionFactEntityObject.getSalt().setValue(nutritionFactObject.getSalt().getValue());
        }
        if (nutritionFactObject.getSaturatedFattyAcids() != null){
            nutritionFactEntityObject.newSaturatedFattyAcids();
            nutritionFactEntityObject.getSaturatedFattyAcids().setValue(nutritionFactObject.getSaturatedFattyAcids().getValue());
        }
        if (nutritionFactObject.getSugar() != null){
            nutritionFactEntityObject.newSugar();
            nutritionFactEntityObject.getSugar().setValue(nutritionFactObject.getSugar().getValue());
        }
        if (nutritionFactObject.getTotalFat() != null){
            nutritionFactEntityObject.newTotalFat();
            nutritionFactEntityObject.getTotalFat().setValue(nutritionFactObject.getTotalFat().getValue());
        }
        if (nutritionFactObject.getTranseFattyAcids() != null){
            nutritionFactEntityObject.newTranseFattyAcids();
            nutritionFactEntityObject.getTranseFattyAcids().setValue(nutritionFactObject.getTranseFattyAcids().getValue());
        }
        if(nutritionFactObject.getExtraFields() != null && nutritionFactObject.getExtraFields().size()!=0){
            nutritionFactEntityObject.newExtraFields();
            nutritionFactEntityObject.setExtraFields(quantityConverter.getEntityList(nutritionFactObject.getExtraFields()));
        }
        return nutritionFactEntityObject;
    }

}
