package ir.ord.application.dto.source_dtos;

import ir.ord.application.dto.LocationDto;

/**
 * Created by vahid on 7/26/17.
 */
public class AppSourceDto {
    private String appVersion;
    private String appVersionCode;
    private Integer devicePlatform;
    private String deviceSDK;
    private String deviceName;
    private String page;
    LocationDto location ;

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public Integer getDevicePlatform() {
        return devicePlatform;
    }

    public void setDevicePlatform(Integer devicePlatform) {
        this.devicePlatform = devicePlatform;
    }

    public String getDeviceSDK() {
        return deviceSDK;
    }

    public void setDeviceSDK(String deviceSDK) {
        this.deviceSDK = deviceSDK;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }
}
