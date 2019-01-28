package test.icon.graphql.graphqltest.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import test.icon.graphql.micro1.api.controller.PersonControllerInterface;

@Service
@FeignClient(name = "person-api", url = "http://localhost:4041")
public interface PersonApiClient extends PersonControllerInterface {
}
