package kl.v2x.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

/**
 *
 * Json工具类
 *
 * @author mouyy
 * @date 2020/9/3
 */
@Getter
public class JsonUtil {


    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * 把JavaBean转换为json字符串
     *
     * @param object object
     * @return jsonString
     */
    public static String toJsonString(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    /**
     * 美化输出
     *
     * @param object
     * @return prettyJsonString
     * @throws JsonProcessingException
     */
    public static String toPrettyJsonString(Object object) throws JsonProcessingException {
        return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }


    /**
     * 反序列化
     *
     * @param json json字符串
     * @param valueType 反序列化对象class
     * @param <T> 泛型
     * @return T
     * @throws JsonProcessingException
     */
    public static <T> T jsonStringToObject(String json,Class<T> valueType) throws JsonProcessingException {
        return OBJECT_MAPPER.readValue(json, valueType);
    }
}
