package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.LocationObject;
import ir.ord.application.dto.LocationDto;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by vahid on 5/1/17.
 */
@ApplicationScoped
public class LocationConvertor {

    public LocationDto getDto(LocationObject locationObject){
        if (locationObject == null)
            return null;
        LocationDto locationDto = new LocationDto();
        locationDto.setLatitude(locationObject.getLatitude());
        locationDto.setLongitude(locationObject.getLongitude());
        return locationDto;
    }

    public LocationObject getObject(LocationDto locationDto){
        if (locationDto == null)
            return null;
        LocationObject locationObject = new LocationObject();
        locationObject.setLongitude(locationDto.getLongitude());
        locationObject.setLatitude(locationDto.getLatitude());
        return locationObject;
    }
}
