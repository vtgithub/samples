package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.GiftEntity;
import ir.ord.application.dto.GiftDto;
import ir.ord.application.dto.protoes.GiftProto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@ApplicationScoped
public class GiftConvertor {

    public GiftEntity getEntity(GiftDto giftDto){
        if (giftDto == null)
            return null;
        GiftEntity giftEntity = new GiftEntity();
        giftEntity.setUsedTime(giftDto.getUsedTime());
        giftEntity.setCode(giftDto.getCode());
        giftEntity.setExpirationTime(giftDto.getExpirationTime());
        giftEntity.setIncludeOrExclude(giftDto.getIncludeOrExclude());
        giftEntity.setValue(giftDto.getValue());
        giftEntity.setAccountIdList(giftDto.getAccountIdList());
        return giftEntity;
    }

    public GiftDto getDto(GiftEntity giftEntity){
        if (giftEntity == null)
            return null;
        GiftDto giftDto = new GiftDto();
        giftDto.setUsedTime(giftEntity.getUsedTime());
        giftDto.setCode(giftEntity.getCode());
//        giftDto.setExpirationTime(giftEntity.getExpirationTime());
//        giftDto.setIncludeOrExclude(giftEntity.getIncludeOrExclude());
        giftDto.setValue(giftEntity.getValue());
//        giftDto.setAccountIdList(giftEntity.getAccountIdList());
//        giftDto.setUserId(giftEntity.getUserId());
        return  giftDto;
    }

    public GiftDto getDtoFromBuilder(GiftProto.GiftRequest.Builder giftBuilder){
        if (giftBuilder == null)
            return null;
        GiftDto giftDto = new GiftDto();
        giftDto.setIncludeOrExclude(giftBuilder.getIncludeOrExclude());
        giftDto.setExpirationTime(giftBuilder.getExpirationTime());
        giftDto.setValue(giftBuilder.getValue());
        giftDto.setAccountIdList(giftBuilder.getAccountIdListList());
        return giftDto;
    }

    public List<GiftDto> getDtoList(List<GiftEntity> giftEntityList) {
        if (giftEntityList == null || giftEntityList.size() == 0)
            return null;
        List<GiftDto> giftDtoList = new ArrayList<GiftDto>();
        for (GiftEntity giftEntity : giftEntityList) {
            giftDtoList.add(getDto(giftEntity));
        }
        return giftDtoList;
    }

    public GiftEntity getEntity(GiftProto.GiftRequest.Builder giftBuilder) {
        if (giftBuilder == null)
            return null;
        GiftEntity giftEntity = new GiftEntity();
        giftEntity.setValue(giftBuilder.getValue());
        if (giftBuilder.getExpirationTime() != 0)
            giftEntity.setExpirationTime(giftBuilder.getExpirationTime());
        giftEntity.setIncludeOrExclude(giftBuilder.getIncludeOrExclude());
        giftEntity.setAccountIdList(giftBuilder.getAccountIdListList());
        return giftEntity;
    }

    public GiftProto.GiftList.Builder getBuilderList(List<GiftEntity> giftEntityList) {
        if (giftEntityList == null || giftEntityList.size() == 0)
            return null;
        List<GiftProto.Gift> giftProtoList = new ArrayList<GiftProto.Gift>();
        for (GiftEntity giftEntity : giftEntityList) {
            GiftProto.Gift.Builder giftBuilder = getBuilder(giftEntity);
            if (giftBuilder != null)
                giftProtoList.add(giftBuilder.build());
        }
        GiftProto.GiftList.Builder giftListBuilder = GiftProto.GiftList.newBuilder();
        giftListBuilder.addAllList(giftProtoList);
        return giftListBuilder;
    }

    private GiftProto.Gift.Builder getBuilder(GiftEntity giftEntity) {
        if (giftEntity == null)
            return null;
        GiftProto.Gift.Builder giftBuilder = GiftProto.Gift.newBuilder();
        giftBuilder.setCode(giftEntity.getCode());
        giftBuilder.setValue(giftEntity.getValue());
        if (giftEntity.getExpirationTime() != null)
            giftBuilder.setExpirationTime(giftEntity.getExpirationTime());
        if (giftEntity.getAccountIdList() != null)
            giftBuilder.addAllAccountIdList(giftEntity.getAccountIdList());
        if (giftEntity.getUsedTime() != null)
            giftBuilder.setUsedTime(giftEntity.getUsedTime());
        if (giftEntity.getUserId() != null)
            giftBuilder.setUserId(giftEntity.getUserId());
        giftBuilder.setIncludeOrExclude(giftEntity.getIncludeOrExclude());
        return giftBuilder;
    }
}
