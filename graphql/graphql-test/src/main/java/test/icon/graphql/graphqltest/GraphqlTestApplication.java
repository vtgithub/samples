package test.icon.graphql.graphqltest;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
public class GraphqlTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlTestApplication.class, args);
	}


	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {

				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(ServerGraphQLError::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

}

