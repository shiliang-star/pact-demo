package kl.v2x.provider.bean;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 车辆设备响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceResponseDTO {

    /**
     * 响应码.
     */
    private String resultCode;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private boolean data;

}
