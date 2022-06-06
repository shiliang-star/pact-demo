package kl.v2x.consumer.test;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import kl.v2x.consumer.client.CertApplyClient;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * @author shil
 * @date 2022/6/1
 */
@ExtendWith(PactConsumerTestExt.class)
public class CertApplyContractTest {

    @Pact(consumer = "V2X-RA-Service", provider = "V2X-PCA-Service")
    public RequestResponsePact certApply(PactDslWithProvider builder) throws IOException {
        Map<String, Object> data = new HashMap<>();
        data.put("cert", new byte[]{1, 2, 3, 5});
        data.put("test", "shiliang");
        return builder.given("Cert apply success", data)
                .uponReceiving("Cert Apply")
                .path("/cert/apply")
                .method("POST")
                .body(new String(new byte[]{1, 2, 3, 4}), "application/octet-stream")
                .willRespondWith()
                .status(200)
                .headers(headers())
                .withBinaryData(new byte[]{12, 32, 45, 23}, "application/octet-stream")
                .toPact();
    }

    private Map<String, String> headers() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", ContentType.APPLICATION_OCTET_STREAM.toString());
        return headers;
    }

    @PactTestFor(pactMethod = "certApply")
    @Test
    public void testCertApply(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        CertApplyClient certApplyClient = new CertApplyClient(restTemplate);

        ResponseEntity<byte[]> responseEntity = certApplyClient.certApply(new byte[]{1, 2, 3, 4});
        assertThat(responseEntity.getStatusCodeValue(), is(200));
        assertThat(responseEntity.getHeaders().get("Content-Type").get(0), is("application/octet-stream"));
        System.out.println(Arrays.toString(responseEntity.getBody()));
    }
}
