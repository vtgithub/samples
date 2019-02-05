package test.icon.graphql.micro1.api.controller;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.icon.graphql.micro1.api.model.PersonDto;

import java.util.List;

@Api(value = "personApis", description = "everything about a person info")
@RequestMapping(path = "/person")
public interface PersonControllerInterface {
    @ApiOperation(value = "gettting a persons bank info", response = PersonDto.class)
    @RequestMapping(method = RequestMethod.GET, path = "/{pId}")
    PersonDto getPersonInfo(@PathVariable("pId") int pId);
    @ApiOperation(value = "gettting a persons list")
    @RequestMapping(method = RequestMethod.GET)
    List<PersonDto> getAllPerson();
}
