package ir.ord.application.dal.entities.source_entities;

import ir.ord.application.dal.entities.LocationObject;

/**
 * Created by vahid on 7/26/17.
 */
public class AppSourceEntity {
    private String appVersion;
    private String appVersionCode;
    private Integer devicePlatform;
    private String deviceSDK;
    private String deviceName;
    private String page;
    LocationObject location ;

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

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }
}
