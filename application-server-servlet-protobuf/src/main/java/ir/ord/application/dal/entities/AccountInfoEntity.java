package ir.ord.application.dal.entities;



import ir.ord.application.accessories.DaoHelper;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Created by vahid on 4/24/17.
 */
//@Entity
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"phoneNumber"}))
public class AccountInfoEntity implements Serializable {
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String _id = DaoHelper.getUUID();
    private String phoneNumber;
    private String firstName;
    private String lastName;
    @NotNull
    private Integer accountState;
    private Double balance = 0.0;
    private Boolean infoRequired;
//    @ElementCollection
    private List<AddressObject> addressObjectList;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAccountState() {
        return accountState;
    }

    public void setAccountState(Integer accountState) {
        this.accountState = accountState;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<AddressObject> getAddressObjectList() {
        return addressObjectList;
    }

    public void setAddressObjectList(List<AddressObject> addressObjectList) {
        this.addressObjectList = addressObjectList;
    }

    public Boolean getInfoRequired() {
        return infoRequired;
    }

    public void setInfoRequired(Boolean infoRequired) {
        this.infoRequired = infoRequired;
    }
}
