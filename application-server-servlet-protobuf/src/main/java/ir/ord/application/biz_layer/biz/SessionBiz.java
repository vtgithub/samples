package ir.ord.application.biz_layer.biz;

import ir.ord.application.*;
import ir.ord.application.Convertor.SessionInfoConvertor;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.biz_layer.validation.SessionValidation;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.SessionInfoDao;
import ir.ord.application.dal.entities.DevicePushToken;
import ir.ord.application.dal.entities.SessionInfoEntity;
import ir.ord.application.dto.SessionDeactivateDto;
import ir.ord.application.dto.SessionInfoDto;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 9/10/17.
 */
@Stateless
public class SessionBiz {
    @Inject
    SessionValidation sessionValidation;
    @Inject
    SessionInfoDao sessionInfoDao;
    @Inject
    SessionInfoConvertor sessionInfoConvertor;

    public List<SessionInfoDto> getActiveSessions(String sessionId) throws DaoException {
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        List<SessionInfoEntity> allActiveAccountSessionEntityList = sessionInfoDao.getAllActiveAccountSessions(sessionInfoEntity.getAccountId());
        removeThisSessionFromList(allActiveAccountSessionEntityList, sessionId);
        List<SessionInfoDto> allActiveAccountSessionDtoList = sessionInfoConvertor.getDtoList(allActiveAccountSessionEntityList);
        setLastActivityTime(allActiveAccountSessionEntityList, allActiveAccountSessionDtoList);
        return allActiveAccountSessionDtoList;
    }


    public void signOutAccount(String sessionId) throws CustomValidationException, DaoException {
        if (sessionId == null || sessionId.equals(""))
            throw new CustomValidationException(ValidationMessages.SessionIdEmpty);
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        if (sessionInfoEntity == null || sessionInfoEntity.equals(""))
            throw new CustomValidationException(ValidationMessages.secondarySessionIdNotValid);
        sessionInfoEntity.setActive(false);
        sessionInfoDao.update(sessionInfoEntity.get_id(), sessionInfoEntity);
    }


    public void signOutAccountList(List<SessionDeactivateDto> secondarySessionList) throws CustomValidationException, DaoException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> runnableList = new ArrayList<Runnable>();
        if (secondarySessionList.size() == 0)
            throw new CustomValidationException(ValidationMessages.SessionIdEmpty);

        for (SessionDeactivateDto sessionDeactivateDto : secondarySessionList) {
            final SessionInfoEntity sessionInfoEntity = sessionInfoDao.getBySecondaryId(sessionDeactivateDto.getSecondaryId());
            if (sessionInfoEntity == null)
                throw new CustomValidationException(ValidationMessages.secondarySessionIdNotValid);
            final SessionInfoEntity oldSessionInfoEntity =
                    (SessionInfoEntity) DaoHelper.getNewInstanceFromExisting(sessionInfoEntity, sessionInfoEntity.getClass());
            sessionInfoEntity.setActive(false);
            DaoHelper.getRedisDb().hdel(DaoHelper.getActiveSessionsHashName(),sessionInfoEntity.get_id());
            callableRowList.add(new CallableRow<SessionInfoEntity>(
                            OperationEnum.UPDATE.getCode(),
                            oldSessionInfoEntity.get_id(),
                            oldSessionInfoEntity,
                            new Callable<Void>() {
                                public Void call() throws Exception {
                                    sessionInfoDao.update(sessionInfoEntity.get_id(), sessionInfoEntity);
                                    return null;
                                }
                            }
                    )
            );
//            final Set<String> pushTokenList = sessionInfoDao.getPushTokenList(sessionInfoEntity.getAccountId());
            final Set<String> pushTokenList = new HashSet<String>();
            if(sessionInfoEntity.getPushToken() != null && !sessionInfoEntity.getPushToken().equals(""))
                pushTokenList.add(sessionInfoEntity.getPushToken());
            final DevicePushToken devicePushToken = new DevicePushToken();
            devicePushToken.setPlatform(sessionInfoEntity.getPlatform());
            devicePushToken.setPushTokenSet(pushTokenList);
            runnableList.add(new Runnable() {
                public void run() {
                    Helper.Notification.sendContentUpdate(
                            UpdateNotifType.ACCOUNT.getCode(),
                            UpdateNotifAccountEvent.SIGN_OUT.getCode(),
                            UpdateNotifPriority.HIGHT.getCode(),
                            sessionInfoEntity.get_id(),
                            devicePushToken
                    );
                }
            });
        }

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(runnableList);

    }




    //-------- Helper
    private void removeThisSessionFromList(List<SessionInfoEntity> allActiveAccountSessionEntityList, String sessionId) {
        Iterator<SessionInfoEntity> iterator = allActiveAccountSessionEntityList.iterator();
        while (iterator.hasNext()){
            if (iterator.next().get_id().equals(sessionId))
                iterator.remove();
        }
    }

    public void updateSessionTime(String sessionId) {
        DaoHelper.getRedisDb().hset(DaoHelper.getActiveSessionsHashName(), sessionId, Helper.getCurrentTime().toString());
    }


    private void setLastActivityTime(List<SessionInfoEntity> sessionInfoEntityList, List<SessionInfoDto> sessionInfoDtoList) {
        for(int i=0; i <sessionInfoEntityList.size(); i++){
            String lastActivityTime = DaoHelper.getRedisDb().hget(DaoHelper.getActiveSessionsHashName(), sessionInfoEntityList.get(i).get_id());
            if (lastActivityTime != null && !lastActivityTime.equals(""))
                sessionInfoDtoList.get(i).setLastActivity(Long.parseLong(lastActivityTime));
        }
    }


    public Boolean isSessionActive(String sessionId) throws DaoException {
        SessionInfoEntity sessionInfoEntity = sessionInfoDao.getActiveSessionById(sessionId);
        if (sessionInfoEntity == null ||
                sessionInfoEntity.getAccountId() == null ||
                sessionInfoEntity.getAccountId().trim().equals(""))
            return false;
        return true;
    }

}
