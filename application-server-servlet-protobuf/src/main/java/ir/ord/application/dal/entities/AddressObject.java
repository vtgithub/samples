package ir.ord.application.dal.entities;



import ir.ord.application.accessories.DaoHelper;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vahid on 4/26/17.
 */
//@Embeddable
public class AddressObject implements Serializable {
    private String _id = DaoHelper.getUUID();
    private String title;
    private String phone;
    private String street;
    private String alley;
    private String unit;
    private String number;
    private LocationObject location;
    private LocationObject gpsInfo;
    // an string contain list of TimePeriodEntity in jsonMode
    private List<TimePeriodEntity> timePeriodEntityList;


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }

    public List<TimePeriodEntity> getTimePeriodEntityList() {
        return timePeriodEntityList;
    }

    public void setTimePeriodEntityList(List<TimePeriodEntity> timePeriodEntityList) {
        this.timePeriodEntityList = timePeriodEntityList;
    }

    public LocationObject getGpsInfo() {
        return gpsInfo;
    }

    public void setGpsInfo(LocationObject gpsInfo) {
        this.gpsInfo = gpsInfo;
    }
}
