package test.icon.graphql.micro2.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.icon.graphql.micro2.api.model.BankInfoDto;

@RequestMapping(path = "/bank")
public interface BankControllerInterface {
    @RequestMapping(method = RequestMethod.GET, path = "/{pId}")
    BankInfoDto getPersonBankInfo(@PathVariable("pId") int pId);

}
