package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Map;

/**
 * Created by vahid on 4/26/17.
 */
//@Entity
//@Table(
//        uniqueConstraints = @UniqueConstraint(columnNames = {"name"})
//)
public class PackageEntity implements Serializable{
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
    @NotNull
    private String name;
    private Map<String, Integer> products   ; //productId, number of that
//    private String imageUrl;
    private Double price;
//    @NotNull
    private String categoryId;
    private String description;
    private Object data; //used for saving number of functionality


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<String, Integer> products) {
        this.products = products;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
