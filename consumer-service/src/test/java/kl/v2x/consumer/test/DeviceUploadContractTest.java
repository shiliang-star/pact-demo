package kl.v2x.consumer.test;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import kl.v2x.consumer.JsonUtil;
import kl.v2x.consumer.bean.DeviceRequestDTO;
import kl.v2x.consumer.bean.DeviceResponseDTO;
import kl.v2x.consumer.client.DeviceUploadClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author shil
 * @date 2022/6/1
 */
@ExtendWith(PactConsumerTestExt.class)
public class DeviceUploadContractTest {


    @Pact(consumer = "V2X-Appliance", provider = "V2X-RA-Service")
    public RequestResponsePact uploadDeviceInfo(PactDslWithProvider builder) throws JsonProcessingException {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        return builder.given("Upload success")
                .uponReceiving("Upload device info success")
                .path("/device/upload")
                .method("POST")
                .body(JsonUtil.toJsonString(new DeviceRequestDTO("mock_guid", "mock_vin", "mock_duns")))
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(LambdaDsl.newJsonBody(object -> {
                    object.stringType("resultCode", "200");
                    object.stringType("message");
                    object.booleanType("data", true);
                }).build())
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "uploadDeviceInfo")
    public void testUploadDeviceInfo(MockServer mockServer) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri(mockServer.getUrl())
                .build();

        DeviceUploadClient deviceUploadClient = new DeviceUploadClient(restTemplate);

        DeviceRequestDTO deviceRequestDTO = new DeviceRequestDTO("mock_guid", "mock_vin", "mock_duns");
        DeviceResponseDTO deviceResponseDTO = deviceUploadClient.uploadDeviceInfo(deviceRequestDTO, mockServer.getUrl());
        System.out.println(deviceResponseDTO);
        assertEquals("200", deviceResponseDTO.getResultCode());
        assertTrue(deviceResponseDTO.isData());
    }
}
