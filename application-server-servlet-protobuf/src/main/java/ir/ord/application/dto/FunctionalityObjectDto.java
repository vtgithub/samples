package ir.ord.application.dto;

/**
 * Created by vahid on 5/30/17.
 */
public class FunctionalityObjectDto {
    private String catId;
    private String catName;
    private Integer funcNumber;
    private PackageDto packageDto;


    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public Integer getFuncNumber() {
        return funcNumber;
    }

    public void setFuncNumber(Integer funcNumber) {
        this.funcNumber = funcNumber;
    }

    public PackageDto getPackageDto() {
        return packageDto;
    }

    public void setPackageDto(PackageDto packageDto) {
        this.packageDto = packageDto;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

}
