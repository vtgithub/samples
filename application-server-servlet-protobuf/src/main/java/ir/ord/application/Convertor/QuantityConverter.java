package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.QuantityEntityObject;
import ir.ord.application.dto.QuantityObject;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 12/23/17.
 */
@ApplicationScoped
public class QuantityConverter {

    public QuantityObject getDto(QuantityEntityObject quantityEntityObject){
        if (quantityEntityObject == null)
            return null;
        QuantityObject quantityObject= new QuantityObject();
        quantityObject.setUnit(quantityEntityObject.getUnit());
        quantityObject.setValue(quantityEntityObject.getValue());
        return quantityObject;
    }

    public QuantityEntityObject getEntity(QuantityObject quantityObject){
        if (quantityObject == null)
            return null;
        QuantityEntityObject quantityEntityObject = new QuantityEntityObject();
        quantityEntityObject.setUnit(quantityObject.getUnit());
        quantityEntityObject.setValue(quantityObject.getValue());
        return quantityEntityObject;
    }

    public List<QuantityObject> getDtoList(List<QuantityEntityObject> extraFields) {
        if (extraFields == null)
            return null;
        List<QuantityObject> quantityObjectList = new ArrayList<QuantityObject>();
        for (QuantityEntityObject extraField : extraFields) {
            quantityObjectList.add(getDto(extraField));
        }
        return quantityObjectList;
    }
    public List<QuantityEntityObject> getEntityList(List<QuantityObject> quantityObjectList){
        if (quantityObjectList == null)
            return null;
        List<QuantityEntityObject> quantityEntityObjectList = new ArrayList<QuantityEntityObject>();
        for (QuantityObject quantityObject : quantityObjectList) {
            quantityEntityObjectList.add(getEntity(quantityObject));
        }
        return quantityEntityObjectList;
    }
}
