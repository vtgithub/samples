package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.SessionInfoEntity;
import ir.ord.application.dto.SessionDeactivateDto;
import ir.ord.application.dto.SessionInfoDto;
import ir.ord.application.dto.protoes.SessionInfoProto;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 9/10/17.
 */
@ApplicationScoped
public class SessionInfoConvertor {

        public SessionInfoDto getDto(SessionInfoEntity sessionInfoEntity){
            if (sessionInfoEntity  == null)
                return null;
            ModelMapper modelMapper = new ModelMapper();
            SessionInfoDto sessionInfoDto = modelMapper.map(sessionInfoEntity, SessionInfoDto.class);
            return sessionInfoDto;
        }

    public List<SessionInfoDto> getDtoList(List<SessionInfoEntity> allActiveAccountSessionEntityList) {
        if (allActiveAccountSessionEntityList == null)
            return null;
        List<SessionInfoDto> sessionInfoDtos = new ArrayList<SessionInfoDto>();
        for (SessionInfoEntity sessionInfoEntity : allActiveAccountSessionEntityList) {
            SessionInfoDto dto = getDto(sessionInfoEntity);
            dto.setActive(null);
            sessionInfoDtos.add(dto);
        }
        return sessionInfoDtos;
    }

    public SessionDeactivateDto getDtoFromBuilder(SessionInfoProto.SessionDeactivateRequest.Builder builder){
            if (builder == null)
                return null;
            SessionDeactivateDto sessionDeactivateDto = new SessionDeactivateDto();
            sessionDeactivateDto.setSecondaryId(builder.getSecondaryId());
            return sessionDeactivateDto;
    }
}
