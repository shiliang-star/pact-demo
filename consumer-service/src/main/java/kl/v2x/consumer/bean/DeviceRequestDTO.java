package kl.v2x.consumer.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author shil
 * @date 2022/5/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequestDTO {

    /**
     * GUID
     */
    private String guid;

    private String vin;

    private String duns;

}
