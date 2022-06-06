package kl.v2x.consumer.client;

import kl.v2x.consumer.bean.DeviceRequestDTO;
import kl.v2x.consumer.bean.DeviceResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


/**
 * @author shil
 * @date 2022/5/31
 */
@Component
public class DeviceUploadClient {

    private final RestTemplate restTemplate;

    @Autowired
    public DeviceUploadClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public DeviceResponseDTO uploadDeviceInfo(DeviceRequestDTO deviceRequestDTO,String baseUrl) {
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
//        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
//        ResponseEntity<DeviceResponseDTO> dtoResponseEntity = restTemplate.exchange("/device/upload",
//                HttpMethod.POST,
//                new HttpEntity<>(headers),
//                DeviceResponseDTO.class,
//                deviceRequestDTO);
//        return dtoResponseEntity.getBody();

        ParameterizedTypeReference<DeviceResponseDTO> ptr = new ParameterizedTypeReference<DeviceResponseDTO>() {
        };
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        RequestEntity<DeviceRequestDTO> deviceRequestDTOHttpEntity = null;
        try {
            deviceRequestDTOHttpEntity = new RequestEntity<>(deviceRequestDTO, headers, HttpMethod.POST, new URI(baseUrl + "/device/upload"));
        } catch (URISyntaxException e) {
        }
        ResponseEntity<DeviceResponseDTO> dtoResponseEntity = restTemplate.exchange(deviceRequestDTOHttpEntity, ptr);
        return dtoResponseEntity.getBody();
    }

}
