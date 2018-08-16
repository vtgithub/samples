package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vahid on 4/26/17.
 */
//@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
//@Embeddable
public class CategoryEntity implements Serializable{
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
    @NotNull
    private String name;
    private String imageUrl;
//    @Column
//    @ElementCollection
    private List<CategoryEntity> children;

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

    public List<CategoryEntity> getChildren() {
        return children;
    }

    public void setChildren(List<CategoryEntity> children) {
        this.children = children;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
