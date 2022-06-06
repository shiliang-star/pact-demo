package kl.v2x.consumer.client;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author shil
 * @date 2022/5/31
 */
@Component
public class CertApplyClient {

    private RestTemplate restTemplate;

    @Autowired
    public CertApplyClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<byte[]> certApply(byte[] requestPayload) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_OCTET_STREAM_VALUE);
            headers.add("Accept", MediaType.APPLICATION_OCTET_STREAM_VALUE);
        HttpEntity<byte[]> request = new HttpEntity<>(requestPayload, headers);
        return restTemplate.exchange("/cert/apply",
                HttpMethod.POST,
                request,
                byte[].class);
    }
}
