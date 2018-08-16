package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.AccountInfoEntity;
import ir.ord.application.dto.AccountInfoDto;
import ir.ord.application.dto.protoes.AccountInfoProto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

/**
 * Created by vahid on 4/24/17.
 */
@ApplicationScoped
@Transactional
public class AccountInfoConvertor {

    public AccountInfoEntity dtoToEntity(AccountInfoDto accountInfoDto, AccountInfoEntity accountInfoEntity){
        if (accountInfoDto == null)
            return null;
//        AccountInfoEntity accountInfoEntity = new AccountInfoEntity();
        if (accountInfoDto.getPhoneNumber()!=null && !accountInfoDto.getPhoneNumber().equals("") )
            accountInfoEntity.setPhoneNumber(accountInfoDto.getPhoneNumber());
        accountInfoEntity.setFirstName(accountInfoDto.getFirstName());
        accountInfoEntity.setLastName(accountInfoDto.getLastName());

        return accountInfoEntity;
    }

    public AccountInfoDto entityToDto(AccountInfoEntity accountInfoEntity){
        if (accountInfoEntity == null)
            return null;
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setFirstName(accountInfoEntity.getFirstName());
        accountInfoDto.setLastName(accountInfoEntity.getLastName());
        accountInfoDto.setPhoneNumber(accountInfoEntity.getPhoneNumber());
        accountInfoDto.setAccountState(accountInfoEntity.getAccountState());
        accountInfoDto.setBalance(accountInfoEntity.getBalance());

        return accountInfoDto;
    }

    public AccountInfoDto getDtoFromBuilder(AccountInfoProto.AccountInfoRequest.Builder accountBuilder){
        AccountInfoDto accountInfoDto = new AccountInfoDto();
//        accountInfoDto.setBalance(accountBuilder.getBalance());
//        accountInfoDto.setAccountState(accountBuilder.getAccountState());
//        accountInfoDto.setPhoneNumber(accountBuilder.getPhoneNumber());
        accountInfoDto.setLastName(accountBuilder.getLastName());
        accountInfoDto.setFirstName(accountBuilder.getFirstName());
        return accountInfoDto;
    }

}
