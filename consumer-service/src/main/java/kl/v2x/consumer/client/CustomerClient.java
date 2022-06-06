package kl.v2x.consumer.client;



import kl.v2x.consumer.bean.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author shil
 * @date 2022/5/25
 */
@Component
public class CustomerClient {


    private RestTemplate restTemplate;

    @Autowired
    public CustomerClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Customer> getAllCustomers() {
        ParameterizedTypeReference<List<Customer>> ptr = new ParameterizedTypeReference<List<Customer>>() {
        };
        ResponseEntity<List<Customer>> listResponseEntity = restTemplate.exchange("/customers", HttpMethod.GET, null, ptr);
        return listResponseEntity.getBody();
    }
}
