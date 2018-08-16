package ir.ord.application.Convertor;

import ir.ord.application.biz_layer.biz.BankInfoDto;
import ir.ord.application.dal.entities.BankInfoObject;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by vahid on 9/5/17.
 */
@ApplicationScoped
public class BankInfoConvertor {

    public BankInfoDto getDto(BankInfoObject bankInfoObject){
        BankInfoDto bankInfoDto = new BankInfoDto();
        bankInfoDto.setBankId(bankInfoObject.getBankId());
//        bankInfoDto.setToken(bankInfoObject.getToken());
        return bankInfoDto;
    }

    public BankInfoObject getObject(BankInfoDto bankInfoDto){
        BankInfoObject bankInfoObject = new BankInfoObject();
        bankInfoObject.setToken(bankInfoDto.getToken());
        bankInfoObject.setBankId(bankInfoDto.getBankId());
        return bankInfoObject;
    }
}
