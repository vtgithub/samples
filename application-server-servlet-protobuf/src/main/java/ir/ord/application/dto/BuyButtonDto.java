package ir.ord.application.dto;

import java.util.Map;

/**
 * Created by vahid on 5/6/17.
 */
public class BuyButtonDto {
    private String addressId;
    private Map<Integer, String> buttonFunctionalityMap;
    private String packageId;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public Map<Integer, String> getButtonFunctionalityMap() {
        return buttonFunctionalityMap;
    }

    public void setButtonFunctionalityMap(Map<Integer, String> buttonFunctionalityMap) {
        this.buttonFunctionalityMap = buttonFunctionalityMap;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }
}
