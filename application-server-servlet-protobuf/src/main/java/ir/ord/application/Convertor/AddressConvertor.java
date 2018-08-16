package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.AddressObject;
import ir.ord.application.dto.AddressDto;
import ir.ord.application.dto.LocationDto;
import ir.ord.application.dto.protoes.AddressProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/1/17.
 */
@ApplicationScoped
public class AddressConvertor {

    @Inject
    private LocationConvertor locationConvertor;
    @Inject
    private TimePeriodConvertor timePeriodConvertor;

    public AddressObject getEntity(AddressDto addressDto){
        if (addressDto == null)
            return null;
        AddressObject addressObject = new AddressObject();
        if (addressDto.getId()!=null)
            addressObject.set_id(addressDto.getId());
        addressObject.setTitle(addressDto.getTitle());
        addressObject.setPhone(addressDto.getPhone());
        addressObject.setStreet(addressDto.getStreet());
        addressObject.setAlley(addressDto.getAlley());
        addressObject.setUnit(addressDto.getUnit());
        addressObject.setNumber(addressDto.getNumber());
        addressObject.setLocation(locationConvertor.getObject(addressDto.getLocation()));
//        addressObject.setTimePeriodEntityList(
//                timePeriodConvertor.getTimePeriodEntityList(addressDto.getTimePeriodDtoList())
//        );

        return addressObject;
    }

    public AddressDto getDto(AddressObject addressObject){
        if (addressObject == null)
            return null;
        AddressDto addressDto = new AddressDto();
        addressDto.setId(addressObject.get_id());
        addressDto.setTitle(addressObject.getTitle());
        addressDto.setPhone(addressObject.getPhone());
        addressDto.setStreet(addressObject.getStreet());
        addressDto.setAlley(addressObject.getAlley());
        addressDto.setUnit(addressObject.getUnit());
        addressDto.setNumber(addressObject.getNumber());
        addressDto.setLocation(locationConvertor.getDto(addressObject.getLocation()));
        addressDto.setTimePeriodDtoList(
                timePeriodConvertor.getTimePeriodDtoList(addressObject.getTimePeriodEntityList())
        );

        return addressDto;
    }

    public AddressDto getDtoFromBuilder(AddressProto.AccountAddressResquest.Builder builder){
        AddressDto addressDto = new AddressDto();
//        addressDto.setId(builder.getId());
        addressDto.setTitle(builder.getTitle());
        addressDto.setPhone(builder.getPhone());
        addressDto.setStreet(builder.getStreet());
        addressDto.setAlley(builder.getAlley());
        addressDto.setUnit(builder.getUnit());
        addressDto.setNumber(builder.getNumber());
        addressDto.setLocation(getLocationDtoFromBuilder(builder.getLocationBuilder()));
//        addressDto.setTimePeriodDtoList(
//                timePeriodConvertor.getTimePeriodDtoListFromBuilderList(builder.())
//        );
        return addressDto;
    }

    private LocationDto getLocationDtoFromBuilder(AddressProto.OrdLocation.Builder locationBuilder) {
        if (locationBuilder == null)
            return null;
        LocationDto locationDto = new LocationDto();
        locationDto.setLongitude(locationBuilder.getLongitude());
        locationDto.setLatitude(locationBuilder.getLatitude());
        return locationDto;
    }


    // hepler functions

    public List<AddressDto> getDtoList(List<AddressObject> addressObjectList) {
        if (addressObjectList == null)
            return null;
        List<AddressDto> addressDtoList = new ArrayList<AddressDto>();
        for (AddressObject addressObject:addressObjectList){
            addressDtoList.add(getDto(addressObject));
        }
        return addressDtoList;
    }


}
