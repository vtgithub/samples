package test.icon.graghql.micro1.impl.controller;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotation.GraphQLApi;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.icon.graphql.micro1.api.controller.PersonControllerInterface;
import test.icon.graphql.micro1.api.model.PersonDto;

import java.util.ArrayList;
import java.util.List;

@RestController
@GraphQLApi
public class PersonController implements PersonControllerInterface {
    @GraphQLQuery
    public @ResponseBody PersonDto getPersonInfo(@PathVariable("pId") int pId){
        PersonDto personDto = new PersonDto();
        if (pId == 1){
//            personDto.setFirstName("mohammad");
//            personDto.setLastName("norouzi");
//            personDto.setId(pId);
            return new PersonDto(pId, "mohammad", "norouzi");
        }
        if (pId == 2){
            personDto.setFirstName("saeed");
            personDto.setLastName("safikhani");
            personDto.setId(pId);
            return personDto;
        }
        throw new RuntimeException();
    }
    @GraphQLQuery
    public @ResponseBody List<PersonDto> getAllPerson() {
        return new ArrayList<PersonDto>();
    }
}
