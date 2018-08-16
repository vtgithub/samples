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
//        uniqueConstraints = {
//                @UniqueConstraint(columnNames = {"deviceToken","activationCode", "isUsed"})
//        })
public class ButtonActivationEntity implements Serializable{
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")

    private String _id = DaoHelper.getUUID();
    private String deviceToken;
    private String activationCode;
    private Long creationTime;
    private String activatorIP;
    @NotNull
    private Boolean used;
    private Map<Integer, Functionality> functionalityMap;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
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

    public String getActivatorIP() {
        return activatorIP;
    }

    public void setActivatorIP(String activatorIP) {
        this.activatorIP = activatorIP;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Map<Integer, Functionality> getFunctionalityMap() {
        return functionalityMap;
    }

    public void setFunctionalityMap(Map<Integer, Functionality> functionalityMap) {
        this.functionalityMap = functionalityMap;
    }
}
