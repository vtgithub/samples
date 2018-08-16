package ir.ord.application.Convertor;

import ir.ord.application.dal.entities.CreditEntity;
import ir.ord.application.dto.CreditDto;
import ir.ord.application.dto.protoes.CreditProto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vahid on 5/2/17.
 */
@ApplicationScoped
public class WalletConvertor {

    @Inject
    BankInfoConvertor bankInfoConvertor;

    public CreditDto getDto(CreditEntity creditEntity){
        if(creditEntity == null)
            return null;

        CreditDto creditDto = new CreditDto();
        creditDto.setId(creditEntity.get_id());
        creditDto.setAmount(creditEntity.getAmount());
        creditDto.setPayDate(creditEntity.getPayDate());
        creditDto.setOrderId(creditEntity.getOrderId());
        creditDto.setBankInfoDto((creditEntity.getBankInfoObject() == null)?null:bankInfoConvertor.getDto(creditEntity.getBankInfoObject()));
        creditDto.setDescription(creditEntity.getDescription());
        creditDto.setBalance(creditEntity.getBalance());
        creditDto.setPayType(creditEntity.getPayType());
        return creditDto;
    }

//    public CreditDto getDtoFromBuilder(CreditProto.Credit.Builder builder){
//        if (builder == null)
//            return null;
//        CreditDto creditDto = new CreditDto();
//        creditDto.setId(builder.getId());
//        creditDto.setAmount(builder.getAmount());
//        creditDto.setPayDate(builder.getPayDate());
//        creditDto.setOrderId(builder.getOrderId());
//        creditDto.setOrderId(builder.getOrderId());
//        creditDto.setDescription(builder.getDescription());
//        creditDto.setBalance(builder.getBalance());
////        creditDto.setGiftObject(builder.getGiftObject());
////        creditDto.setBankObject(builder.getBankObject());
//        return creditDto;
//    }

    public List<CreditDto> getCreditDtoList(List<CreditEntity> creditEntityList) {
        List<CreditDto> creditDtoList = new ArrayList<CreditDto>();
        if (creditEntityList != null){
            for (CreditEntity creditEntity : creditEntityList){
                creditDtoList.add(getDto(creditEntity));
            }
        }
        return creditDtoList;
    }


}
