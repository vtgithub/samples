package test.icon.graphql.proxy.proxytest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.web.client.RestTemplate;
import test.icon.graphql.proxy.proxytest.service.GraphqlService;
import test.icon.graphql.proxy.proxytest.service.FileReadingException;

@SpringBootApplication
@EnableZuulProxy
public class ProxyTestApplication {

	@Autowired
	private GraphqlService graphqlService;

	public static void main(String[] args) {
		SpringApplication.run(ProxyTestApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws FileReadingException {
//		graphqlService.initMethodsByBasePackage();
		graphqlService.initMethodsBySchemaFiles();
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

