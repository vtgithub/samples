package ir.ord.warehouse.dto;

/**
 * Created by vahid on 9/20/17.
 */
public class CustomOrderDto {
    private String id;
    private String packageId;
    private String packageName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
