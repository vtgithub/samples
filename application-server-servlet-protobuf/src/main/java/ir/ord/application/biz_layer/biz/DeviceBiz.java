package ir.ord.application.biz_layer.biz;

import ir.ord.application.OperationEnum;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.NotificationMessages;
import ir.ord.application.biz_layer.validation.DeviceValidation;
import ir.ord.application.dal.dao.AccountInfoDao;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.SessionInfoDao;
import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.DevicePushToken;
import ir.ord.application.dal.entities.SessionInfoEntity;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/**
 * Created by vahid on 5/11/17.
 */
@Stateless
public class DeviceBiz {

    @Inject
    private DeviceValidation deviceValidation;
    @Inject
    private SessionInfoDao sessionInfoDao;
    @Inject
    private AccountInfoDao accountInfoDao;

    public void sendDeviceNotif(final String sessionId, String pushToken) throws CustomValidationException, DaoException {
        List<CallableRow> callableRowList = new ArrayList<CallableRow>();
        List<Runnable> notifRunnableList = new ArrayList<Runnable>();

        List<String> validationResultList = deviceValidation.sendDeviceNotifValidation(sessionId, pushToken);
        if(validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));
        final SessionInfoEntity sessionInfoEntity = sessionInfoDao.getById(sessionId);
        SessionInfoEntity oldSessionInfoEntity =
                (SessionInfoEntity) DaoHelper.getNewInstanceFromExisting(sessionInfoEntity, sessionInfoEntity.getClass());

        sessionInfoEntity.setPushToken(pushToken);

        callableRowList.add(new CallableRow<SessionInfoEntity>(
                OperationEnum.UPDATE.getCode(),
                oldSessionInfoEntity.get_id(),
                oldSessionInfoEntity,
                new Callable<Void>() {
                    public Void call() throws Exception {
                        sessionInfoDao.update(sessionId, sessionInfoEntity);
                        return null;
                    }
                }
        ));

        final AccountInfoEntity accountInfoEntity = accountInfoDao.getById(sessionInfoEntity.getAccountId());
        final Set<String> notificationReceivers = new HashSet<String>();
        notificationReceivers.add(pushToken);
        final DevicePushToken devicePushToken = new DevicePushToken();
        devicePushToken.setPlatform(sessionInfoEntity.getPlatform());
        devicePushToken.setPushTokenSet(notificationReceivers);
        notifRunnableList.add(new Runnable() {
            public void run() {
                Helper.Notification.sendNotif(
                        "",
                        "",
                        NotificationMessages.getWelcomMessage(
                                accountInfoEntity.getFirstName(),
                                accountInfoEntity.getLastName()
                        ),
                        devicePushToken
                );
            }
        });

        Helper.callCallableList(callableRowList);
        Helper.runRunnableList(notifRunnableList);
    }
}
