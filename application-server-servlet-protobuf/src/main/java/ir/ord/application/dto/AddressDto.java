package ir.ord.application.dto;

import java.util.List;

/**
 * Created by vahid on 5/1/17.
 */
public class AddressDto {
    private String id;
    private String title;
    private String phone;
    private String street;
    private String alley;
    private String unit;
    private String number;
    private LocationDto location;
    private List<TimePeriodDto> timePeriodDtoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }

    public List<TimePeriodDto> getTimePeriodDtoList() {
        return timePeriodDtoList;
    }

    public void setTimePeriodDtoList(List<TimePeriodDto> timePeriodDtoList) {
        this.timePeriodDtoList = timePeriodDtoList;
    }
}
