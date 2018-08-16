package ir.ord.application.dal.entities;


import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dto.OrdObject;

import java.util.List;

/**
 * Created by vahid on 4/26/17.
 */
//@Entity
public class ButtonEntity extends OrdObject{
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
//    private String deviceIp;
//    @ElementCollection
    private String name;
    private List<StatusObject> stateList;
    private String accountId;
    private String deviceToken;
    private Byte numOfFuncs;
    private String addressId;
    private List<FunctionalityObject> functionalityObjectList;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<StatusObject> getStateList() {
        return stateList;
    }

    public void setStateList(List<StatusObject> stateList) {
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

    public List<FunctionalityObject> getFunctionalityObjectList() {
        return functionalityObjectList;
    }

    public void setFunctionalityObjectList(List<FunctionalityObject> functionalityObjectList) {
        this.functionalityObjectList = functionalityObjectList;
    }


    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

