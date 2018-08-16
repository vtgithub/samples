package ir.ord.application.dal.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import ir.ord.application.accessories.DaoHelper;
import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dal.entities.AddressObject;
import org.bson.Document;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/24/17.
 */
@ApplicationScoped
@Transactional
public class AccountInfoDaoMongoImpl extends DaoMongoImpl<AccountInfoEntity> implements AccountInfoDao{

    public AccountInfoEntity getByPhoneNumber(String phoneNumber) throws DaoException {
        try{
            MongoCollection<Document> collection = db.getCollection("AccountInfoEntity");
            BasicDBObject basicDBObject = new BasicDBObject();
            basicDBObject.put("phoneNumber", phoneNumber);
            Document docResult = collection.find(basicDBObject).first();
            if (docResult == null)
                return null;
            Object accountInfoEntityObject = DaoHelper.getObjectFromJson(
                    docResult.toJson(), DaoHelper.getEntityClass(this.getClass().getSuperclass())
            );
            return (AccountInfoEntity) accountInfoEntityObject;
        }catch (Exception e){
            
            throw new DaoException(e);
        }

    }

    public AddressObject getAddress(String accountId, String addressId) throws DaoException {
        AccountInfoEntity accountInfoEntity = this.getById(accountId);
        if (accountInfoEntity.getAddressObjectList() == null)
            return null;
        for (AddressObject addressObject : accountInfoEntity.getAddressObjectList()) {
            if (addressObject.get_id().equals(addressId)){
                return addressObject;
            }
        }

        return null;
    }

    public AddressObject getAddressByEntity(AccountInfoEntity accountInfoEntity, String addressId) {
        if (accountInfoEntity.getAddressObjectList() == null)
            return null;
        for (AddressObject addressObject : accountInfoEntity.getAddressObjectList()) {
            if (addressObject.get_id().equals(addressId)){
                return addressObject;
            }
        }

        return null;
    }
}
