package ir.ord.application.dto.source_dtos;

import ir.ord.application.dto.LocationDto;

/**
 * Created by vahid on 7/26/17.
 */
public class WebSourceDto {
    String url;
    String ip ;
    Integer time ;
    LocationDto location ;

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

    public LocationDto getLocation() {
        return location;
    }

    public void setLocation(LocationDto location) {
        this.location = location;
    }
}
