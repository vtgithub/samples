package ir.ord.application.Convertor.sources_convertors;

import ir.ord.application.Convertor.LocationConvertor;
import ir.ord.application.dal.entities.source_entities.AppSourceEntity;
import ir.ord.application.dto.source_dtos.AppSourceDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 7/29/17.
 */
@ApplicationScoped
public class AppSourceConvertor {

    @Inject
    private LocationConvertor locationConvertor;

    public AppSourceDto getDto(AppSourceEntity appSourceEntity){
        if (appSourceEntity == null)
            return null;
        AppSourceDto appSourceDto = new AppSourceDto();

        appSourceDto.setAppVersion(appSourceEntity.getAppVersion());
        appSourceDto.setAppVersionCode(appSourceEntity.getAppVersionCode());
        appSourceDto.setDevicePlatform(appSourceEntity.getDevicePlatform());
        appSourceDto.setDeviceSDK(appSourceEntity.getDeviceSDK());
        appSourceDto.setDeviceName(appSourceEntity.getDeviceName());
        appSourceDto.setPage(appSourceEntity.getPage());
        appSourceDto.setLocation(locationConvertor.getDto(appSourceEntity.getLocation()));
        return appSourceDto;
    }
}
