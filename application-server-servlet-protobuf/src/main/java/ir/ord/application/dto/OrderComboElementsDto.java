package ir.ord.application.dto;

import java.util.List;

/**
 * Created by vahid on 10/3/17.
 */

public class OrderComboElementsDto {
    List<ComboElementDto> productComboElementList;
    List<ComboElementDto> deliveryComboElementList;
    List<ComboElementDto> packingComboElementList;

    public List<ComboElementDto> getProductComboElementList() {
        return productComboElementList;
    }

    public void setProductComboElementList(List<ComboElementDto> productComboElementList) {
        this.productComboElementList = productComboElementList;
    }

    public List<ComboElementDto> getDeliveryComboElementList() {
        return deliveryComboElementList;
    }

    public void setDeliveryComboElementList(List<ComboElementDto> deliveryComboElementList) {
        this.deliveryComboElementList = deliveryComboElementList;
    }

    public List<ComboElementDto> getPackingComboElementList() {
        return packingComboElementList;
    }

    public void setPackingComboElementList(List<ComboElementDto> packingComboElementList) {
        this.packingComboElementList = packingComboElementList;
    }
}
