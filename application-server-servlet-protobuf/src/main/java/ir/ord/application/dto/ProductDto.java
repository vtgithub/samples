package ir.ord.application.dto;

import ir.ord.application.UnitEnum;

import java.util.Map;

/**
 * Created by vahid on 12/10/17.
 */
public class ProductDto {
//    private Map<String, String> ingredientMap;
    private Long pId;
    private Map<String, String> barcodeMap;
    private Map<String, String> extraInfoMap;//مثل پروانه ساخت
    private NutritionFactObject nutritionFact;
    private QuantityObject quantity = new QuantityObject();
    private String importer;
    private String name;
    private String description;
    private String imageUrl;
    private String catId;
    private Integer expirationDurationDate;
    private Boolean organic;
    private Boolean gmoFree;
    private String packingType;
    private Integer price;

//    public Map<String, String> getIngredientMap() {
//        return ingredientMap;
//    }
//
//    public void setIngredientMap(Map<String, String> ingredientMap) {
//        this.ingredientMap = ingredientMap;
//    }


    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Map<String, String> getBarcodeMap() {
        return barcodeMap;
    }

    public void setBarcodeMap(Map<String, String> barcodeMap) {
        this.barcodeMap = barcodeMap;
    }

    public Map<String, String> getExtraInfoMap() {
        return extraInfoMap;
    }

    public void setExtraInfoMap(Map<String, String> extraInfoMap) {
        this.extraInfoMap = extraInfoMap;
    }

    public NutritionFactObject getNutritionFact() {
        return nutritionFact;
    }

    public void setNutritionFact(NutritionFactObject nutritionFact) {
        this.nutritionFact = nutritionFact;
    }

    public QuantityObject getQuantity() {
        return quantity;
    }

    public void setQuantityUnit(Integer unit) {
        this.quantity.setUnit(unit);
    }

    public void setQuantityValue(Integer value) {
        this.quantity.setValue(value);
    }

    public String getImporter() {
        return importer;
    }

    public void setImporter(String importer) {
        this.importer = importer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Integer getExpirationDurationDate() {
        return expirationDurationDate;
    }

    public void setExpirationDurationDate(Integer expirationDurationDate) {
        this.expirationDurationDate = expirationDurationDate;
    }

    public Boolean getOrganic() {
        return organic;
    }

    public void setOrganic(Boolean organic) {
        this.organic = organic;
    }

    public Boolean getGmoFree() {
        return gmoFree;
    }

    public void setGmoFree(Boolean gmoFree) {
        this.gmoFree = gmoFree;
    }

    public String getPackingType() {
        return packingType;
    }

    public void setPackingType(String packingType) {
        this.packingType = packingType;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
