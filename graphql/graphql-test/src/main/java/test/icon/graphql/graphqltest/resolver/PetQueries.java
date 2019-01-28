package test.icon.graphql.graphqltest.resolver;


import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;
import test.icon.graphql.graphqltest.dto.Animal;
import test.icon.graphql.graphqltest.dto.Pet;

import java.util.ArrayList;
import java.util.List;

@Component
public class PetQueries implements GraphQLQueryResolver {

    public List<Pet> pets() {
        List<Pet> pets = new ArrayList<>();
        Pet aPet = new Pet();
        aPet.setId(1l);
        aPet.setName("Bill");
        aPet.setAge(9);
        aPet.setType(Animal.MAMMOTH);
        pets.add(aPet);
        return pets;
    }

}
