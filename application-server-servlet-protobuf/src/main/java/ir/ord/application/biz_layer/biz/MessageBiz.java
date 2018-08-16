package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.MessageConvertor;
import ir.ord.application.accessories.Helper;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.MessageDao;
import ir.ord.application.dal.entities.MessageEntity;
import ir.ord.application.dto.MessageDto;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by vahid on 11/26/17.
 */
@Stateless
@Transactional
public class MessageBiz {
    @Inject
    private MessageDao messageDao;
    @Inject
    private MessageConvertor messageConvertor;

    public List<MessageDto> getHomePageMessageList() throws DaoException {
        List<MessageEntity> messageEntityList = messageDao.getCurrentMessage(Helper.getCurrentTime());
        List<MessageDto> messageDtoList = messageConvertor.getDtoList(messageEntityList);
        return messageDtoList;
    }
}
