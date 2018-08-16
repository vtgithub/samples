package ir.ord.application.dto;

import ir.ord.application.dal.entities.GiftEntity;

import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
public class GiftDto {

    private String code;
    private Double value;
    private Long usedTime;
    private Long expirationTime;
    private Boolean includeOrExclude;
    private List<String> accountIdList;
    private String userId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Long usedTime) {
        this.usedTime = usedTime;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Boolean getIncludeOrExclude() {
        return includeOrExclude;
    }

    public void setIncludeOrExclude(Boolean includeOrExclude) {
        this.includeOrExclude = includeOrExclude;
    }

    public List<String> getAccountIdList() {
        return accountIdList;
    }

    public void setAccountIdList(List<String> accountIdList) {
        this.accountIdList = accountIdList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
