package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.MessageEntity;
import ir.ord.application.dto.MessageDto;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 11/26/17.
 */
@ApplicationScoped
public class MessageConvertor {

    public MessageDto getDto(MessageEntity messageEntity){
        if (messageEntity == null)
            return null;
        MessageDto messageDto = new MessageDto();
        messageDto.setStartTime(messageEntity.getStartTime());
        messageDto.setEndTime(messageEntity.getEndTime());
        messageDto.setNote(messageEntity.getNote());
        return messageDto;
    }

    public List<MessageDto> getDtoList(List<MessageEntity> messageEntityList){
        if (messageEntityList == null || messageEntityList.size() == 0)
            return null;
        List<MessageDto> messageDtoList = new ArrayList<MessageDto>();
        for (MessageEntity messageEntity : messageEntityList) {
            messageDtoList.add(getDto(messageEntity));
        }

        return messageDtoList;
    }
}
