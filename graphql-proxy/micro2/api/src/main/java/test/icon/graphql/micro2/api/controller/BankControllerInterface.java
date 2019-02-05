package test.icon.graphql.micro2.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.icon.graphql.micro2.api.model.BankInfoDto;

@Api(value = "bankApis", description = "everything about a persons bank")
@RequestMapping(path = "/bank")
public interface BankControllerInterface {
    @ApiOperation(value = "gettting a persons bank info", response = BankInfoDto.class)
    @RequestMapping(method = RequestMethod.GET, path = "/{pId}")
    BankInfoDto getPersonBankInfo(@PathVariable("pId") int pId);

}
