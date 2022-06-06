package kl.v2x.provider.controller;

import kl.v2x.provider.bean.DeviceRequestDTO;
import kl.v2x.provider.bean.DeviceResponseDTO;
import kl.v2x.provider.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shil
 * @date 2022/5/25
 */
@RequiredArgsConstructor
@RestController
public class DeviceUploadController {

    private final DeviceService deviceService;

    @PostMapping(path = "/device/upload", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json; charset=utf-8")
    public ResponseEntity<DeviceResponseDTO> uploadDeviceInfo(@RequestBody DeviceRequestDTO deviceRequest) {
        return new ResponseEntity<>(deviceService.uploadDeviceInfo(deviceRequest), HttpStatus.OK);
    }


}
