package kl.v2x.provider.service.impl;

import kl.v2x.provider.service.CertApplyService;
import org.springframework.stereotype.Service;

/**
 * @author shil
 * @date 2022/5/31
 */
@Service
public class CertApplyServiceImpl implements CertApplyService {
    @Override
    public byte[] applyCert(byte[] certRequest) {
        // cert apply operate
        return new byte[]{1,23,34,21,23,34,34,54,34};
    }
}
