package test.icon.graphql.graphqltest.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import test.icon.graphql.micro2.api.controller.BankControllerInterface;

@Service
@FeignClient(name = "bank-api", url = "http://localhost:4042")
public interface BankApiClient extends BankControllerInterface {
}
