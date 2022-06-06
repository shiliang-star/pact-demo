package kl.v2x.provider.service.impl;

import kl.v2x.provider.bean.DeviceRequestDTO;
import kl.v2x.provider.bean.DeviceResponseDTO;
import kl.v2x.provider.service.DeviceService;
import org.springframework.stereotype.Service;

/**
 * @author shil
 * @date 2022/5/30
 */
@Service
public class DeviceServiceImpl implements DeviceService {


    @Override
    public DeviceResponseDTO uploadDeviceInfo(DeviceRequestDTO deviceRequest) {
        // 保存至数据库
        return null;
    }

    private boolean saveDeviceInfo(DeviceRequestDTO deviceRequest) {
        return true;
    }

}
