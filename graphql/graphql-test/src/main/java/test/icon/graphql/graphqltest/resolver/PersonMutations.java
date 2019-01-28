package test.icon.graphql.graphqltest.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;

@Component
public class PersonMutations implements GraphQLMutationResolver {

    int personSync(String fname, String lname){

        return 20;
    }
}
