package ir.ord.application.biz_layer.biz;

import ir.ord.application.Convertor.GiftConvertor;
import ir.ord.application.accessories.Helper;
import ir.ord.application.accessories.ValidationMessages;
import ir.ord.application.biz_layer.validation.GiftValidation;
import ir.ord.application.dal.dao.AccountInfoDao;
import ir.ord.application.dal.dao.DaoException;
import ir.ord.application.dal.dao.GiftDao;
import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.GiftEntity;
import ir.ord.application.dto.GiftDto;
import org.parboiled.common.StringUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 * Created by vahid on 5/3/17.
 */
@Stateless
public class Giftbiz {

    @Inject
    private GiftDao giftDao;
    @Inject
    private AccountInfoDao accountInfoDao;
    @Inject
    private GiftValidation giftValidation;
    @Inject
    private GiftConvertor giftConvertor;

    public String addGift(GiftDto giftDto) throws CustomValidationException, DaoException {
        List<String> validationResultList = giftValidation.addGiftValidation(giftDto);
        if (validationResultList.size() != 0)
            throw new CustomValidationException(StringUtils.join(validationResultList, ", "));

        String giftChargeCode = null;
        boolean isUnique = false;
        while (!isUnique){
            giftChargeCode = Helper.getGiftChargeCode();
            GiftEntity giftEntity = giftDao.getByCode(giftChargeCode);
            if (giftEntity == null)
                isUnique = true;
            else
                isUnique = false;
        }
        for (String accountId : giftDto.getAccountIdList()) {
            AccountInfoEntity accountInfoEntity = accountInfoDao.getById(accountId);
            if (accountInfoEntity == null)
                throw new CustomValidationException(ValidationMessages.noAccountFound);
        }
        giftDto.setCode(giftChargeCode);
        GiftEntity giftEntity = giftConvertor.getEntity(giftDto);
        giftDao.save(giftEntity);

        return giftChargeCode;
    }
}
