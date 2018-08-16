package ir.ord.application.dal.entities;

import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dto.NutritionFactObject;
import ir.ord.application.dto.QuantityObject;

import java.util.Map;

/**
 * Created by vahid on 12/23/17.
 */
public class ProductEntity {
    private String _id = DaoHelper.getUUID();
    private Long pId;
    private Map<String, String> barcodeMap;
    private Map<String, String> extraInfoMap;//مثل پروانه ساخت
    private NutritionFactEntityObject nutritionFact;
    private QuantityEntityObject quantity = new QuantityEntityObject();
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

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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

    public NutritionFactEntityObject getNutritionFact() {
        return nutritionFact;
    }

    public void setNutritionFact(NutritionFactEntityObject nutritionFact) {
        this.nutritionFact = nutritionFact;
    }

    public QuantityEntityObject getQuantity() {
        return quantity;
    }

    public void setQuantity(QuantityEntityObject quantity) {
        this.quantity = quantity;
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
