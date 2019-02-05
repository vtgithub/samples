package test.icon.graphql.micro2.impl.controller;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.icon.graphql.micro2.api.controller.BankControllerInterface;
import test.icon.graphql.micro2.api.model.BankInfoDto;

@GraphQLApi
@RestController
public class BankController implements BankControllerInterface {

    @GraphQLQuery
    public @ResponseBody
    BankInfoDto getPersonBankInfo(@PathVariable("pId") int pId){
        if (pId == 1)
            return new BankInfoDto(pId, 1, "pasargad",  1, "pasdaran");
        if (pId == 2)
            return new BankInfoDto(pId, 2, "tejarat",  1, "vanak");
        throw new RuntimeException();
    }
}
