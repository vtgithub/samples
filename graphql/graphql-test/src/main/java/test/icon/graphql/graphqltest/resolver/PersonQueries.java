package test.icon.graphql.graphqltest.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import test.icon.graphql.graphqltest.dto.Person;
import test.icon.graphql.graphqltest.remote.BankApiClient;
import test.icon.graphql.graphqltest.remote.PersonApiClient;
import test.icon.graphql.micro1.api.model.PersonDto;
import test.icon.graphql.micro2.api.model.BankInfoDto;

@Component
public class PersonQueries implements GraphQLQueryResolver {
    @Autowired
    private BankApiClient bankApiClient;
    @Autowired
    private PersonApiClient personApiClient;

    Person person(int id){
        PersonDto personDto = personApiClient.getPersonInfo(id);
        BankInfoDto personBankInfo = bankApiClient.getPersonBankInfo(id);
        Person person = new Person();
        person.setPersonInfo(personDto);
        person.setBankInfo(personBankInfo);
        return person;
    }
    Person personAsync(String fname, String lname){
        Person person = new Person();
        return person;
    }
//    @ExceptionHandler(RouteException.class)
//    public GraphQLError exception(RouteException routeException) {
//        return new RouteGraphQLError(routeException.getMessage());
//    }
}
