package ir.ord.application.Convertor.sources_convertors;

import ir.ord.application.Convertor.LocationConvertor;
import ir.ord.application.dal.entities.source_entities.WebSourceEntity;
import ir.ord.application.dto.source_dtos.WebSourceDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Created by vahid on 7/29/17.
 */
@ApplicationScoped
public class WebSourceConvertor {

    @Inject
    private LocationConvertor locationConvertor;
    public WebSourceDto getDto(WebSourceEntity webSourceEntity){
        if (webSourceEntity == null)
            return null;
        WebSourceDto webSourceDto = new WebSourceDto();
        webSourceDto.setLocation(locationConvertor.getDto(webSourceEntity.getLocation()));
        webSourceDto.setIp(webSourceEntity.getIp());
        webSourceDto.setTime(webSourceEntity.getTime());
        webSourceDto.setUrl(webSourceEntity.getUrl());
        return webSourceDto;
    }
}
