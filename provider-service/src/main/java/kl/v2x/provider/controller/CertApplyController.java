package kl.v2x.provider.controller;

import kl.v2x.provider.service.CertApplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class CertApplyController {

    private final CertApplyService certApplyService;

    @PostMapping(path = "/cert/apply",consumes = MediaType.APPLICATION_OCTET_STREAM_VALUE, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] requestPseudonymCert(@RequestBody byte[] certRequestPayload) {
        return certApplyService.applyCert(certRequestPayload);
    }

}
