package test.icon.graphql.proxy.proxytest.service;

import com.google.gson.Gson;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServletCustomTool {

    private Gson gson = new Gson();
    @Autowired
    private RestTemplate restTemplate;


    public String getRequestBody(HttpServletRequest request) throws IOException {
        return  request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
    }

    public Object sendQueryOrMutation(String url, String query) {
        HttpHeaders  requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        Map<String, String> body = new HashMap<>();
        body.put("query", query);
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(body), requestHeaders);
        ResponseEntity<Object> responseEntity = restTemplate.postForEntity(url, entity, Object.class);
        return  responseEntity.getBody();
    }

    public void prepareResponse(RequestContext currentContext, int responseCode, Object responseList) {
        currentContext.setResponseStatusCode(responseCode);
        currentContext.setSendZuulResponse(false);
        currentContext.setResponseBody(gson.toJson(responseList));
    }
}
