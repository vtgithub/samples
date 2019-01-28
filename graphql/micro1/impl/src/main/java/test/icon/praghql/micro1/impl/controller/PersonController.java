package test.icon.praghql.micro1.impl.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import test.icon.graphql.micro1.api.controller.PersonControllerInterface;
import test.icon.graphql.micro1.api.model.PersonDto;

@RestController
public class PersonController implements PersonControllerInterface {
    @RequestMapping(method = RequestMethod.GET, path = "/{pId}")
    public PersonDto getPersonInfo(@PathVariable("pId") int pId){
        if (pId == 1)
            return new PersonDto(pId, "mohammad", "morouzi");
        if (pId == 2)
            return new PersonDto(pId, "saeed", "safikhani");
        throw new RuntimeException();
    }
}
