package ir.ord.application.dal.entities.source_entities;

import ir.ord.application.dal.entities.LocationObject;
import ir.ord.application.dto.LocationDto;

/**
 * Created by vahid on 7/26/17.
 */
public class WebSourceEntity {
    String url;
    String ip ;
    Integer time ;
    LocationObject location ;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public LocationObject getLocation() {
        return location;
    }

    public void setLocation(LocationObject location) {
        this.location = location;
    }
}
