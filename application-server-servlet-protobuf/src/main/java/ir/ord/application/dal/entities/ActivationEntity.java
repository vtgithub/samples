package ir.ord.application.dal.entities;



import ir.ord.application.accessories.DaoHelper;

import javax.validation.constraints.NotNull;

/**
 * Created by vahid on 4/22/17.
 */
//@Entity
//@Table(
//        uniqueConstraints = {
//        @UniqueConstraint(columnNames = {"deviceId","phoneNumber","activationCode", "isUsed"})
//})
public class ActivationEntity {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();

    private String deviceId;
    private String phoneNumber;
    private String activationCode;
    private Long creationTime;
    @NotNull
    private Boolean used = false;

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
