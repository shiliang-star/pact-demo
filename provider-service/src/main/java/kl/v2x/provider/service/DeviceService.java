package kl.v2x.provider.service;

import kl.v2x.provider.bean.DeviceRequestDTO;
import kl.v2x.provider.bean.DeviceResponseDTO;

/**
 * @author shil
 * @date 2022/5/30
 */
public interface DeviceService {

    DeviceResponseDTO uploadDeviceInfo(DeviceRequestDTO deviceRequest);
}
