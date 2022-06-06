package kl.v2x.consumer.test;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import kl.v2x.consumer.bean.Customer;
import kl.v2x.consumer.client.CustomerClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author shil
 * @date 2022/6/1
 */
@ExtendWith(PactConsumerTestExt.class)
public class CustomerContractTest {

    @Pact(consumer="consumer-get-customers",provider = "provider-springboot-customer")
    public RequestResponsePact getCustomers(PactDslWithProvider builder) {
        return builder.given("customers exists")
                .uponReceiving("Get All kl.v2x.Customer Info")
                .path("/customestrs")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(PactDslJsonArray.arrayEachLike()
                        .stringType("name", "customer name")
                        .numberType("id", 1999L)
                        .closeObject())
                .toPact();
    }

    @PactTestFor(pactMethod = "getCustomers")
    @Test
    public void testGetCustomers(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        CustomerClient customerClient = new CustomerClient(restTemplate);
        List<Customer> allCustomers = customerClient.getAllCustomers();
        assertThat(allCustomers.get(0).getId(), is(1999L));
    }
}
