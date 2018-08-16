package ir.ord.application.dto;

import java.util.List;

/**
 * Created by vahid on 5/13/17.
 */
public class ButtonDto {
    private String _id ;
//    private String deviceIp;
    private String name;
    private String addressId;
    private List<StatusObjectDto> stateList;
    private String accountId;
    private String deviceToken;
    private Byte numOfFuncs;
    private List<FunctionalityObjectDto> functionalityObjectDtoList;


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

    public List<StatusObjectDto> getStateList() {
        return stateList;
    }

    public void setStateList(List<StatusObjectDto> stateList) {
        this.stateList = stateList;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Byte getNumOfFuncs() {
        return numOfFuncs;
    }

    public void setNumOfFuncs(Byte numOfFuncs) {
        this.numOfFuncs = numOfFuncs;
    }

    public List<FunctionalityObjectDto> getFunctionalityObjectDtoList() {
        return functionalityObjectDtoList;
    }

    public void setFunctionalityObjectDtoList(List<FunctionalityObjectDto> functionalityObjectDtoList) {
        this.functionalityObjectDtoList = functionalityObjectDtoList;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}
