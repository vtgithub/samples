package ir.ord.application.dal.entities;

import java.util.Set;

/**
 * Created by vahid on 11/11/17.
 */
public class DevicePushToken {
    private Integer platform;
    private Set<String> pushTokenSet;

    public Integer getPlatform() {
        return platform;
    }

    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    public Set<String> getPushTokenSet() {
        return pushTokenSet;
    }

    public void setPushTokenSet(Set<String> pushTokenSet) {
        this.pushTokenSet = pushTokenSet;
    }
}
