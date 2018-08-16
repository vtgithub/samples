package ir.ord.application.dto;

import java.util.Map;

/**
 * Created by vahid on 5/29/17.
 */
public class GetActivationCodeDTO {
    private Map<Integer, String> functionalityMap;
    private String deviceToken;
    private String name;

    public Map<Integer, String> getFunctionalityMap() {
        return functionalityMap;
    }

    public void setFunctionalityMap(Map<Integer, String> functionalityMap) {
        this.functionalityMap = functionalityMap;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
