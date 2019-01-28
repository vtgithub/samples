package test.icon.graphql.micro1.api.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import test.icon.graphql.micro1.api.model.PersonDto;

@RequestMapping(path = "/person")
public interface PersonControllerInterface {
    @RequestMapping(method = RequestMethod.GET, path = "/{pId}")
    public PersonDto getPersonInfo(@PathVariable("pId") int pId);
}
