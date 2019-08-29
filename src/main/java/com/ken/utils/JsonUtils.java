package com.ken.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * @author yhq
 * @date 2019/5/14
 */
public class JsonUtils {

    private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);

    private static final ObjectMapper objectMapper;


    static {
        objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * 将对象序列化为json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectMapper localObjectMapper = getObjectMapper();
        try {
            localObjectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("Json序列化失败", e);
        }
        return null;
    }

    /**
     * 将json数据反序列化成对象
     *
     * @param json
     * @param objClass
     * @return T
     * @author yhq
     * @date 2019/5/14
     */
    public static <T> T toObject(String json, Class<T> objClass) {
        if (EmptyUtils.isEmpty(json)) {
            try {
                return objClass.newInstance();
            } catch (Exception e) {
                logger.error("json反序列化失败: objClass.newInstance()", e);
            }
        }
        ObjectMapper localObjectMapper = getObjectMapper();
        try {
            return localObjectMapper.readValue(json, objClass);
        } catch (Exception e) {
            logger.error("json反序列化失败:", e);
        }
        return null;
    }

    /**
     * 输出格式化后的json字符串
     *
     * @param object
     * @return
     * @throws IOException
     */
    public static String toPrettyJsonString(Object object) {
        ObjectMapper objectMapper = getObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonString = null;
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            logger.error("json序列化失败", e);
        }
        return jsonString;
    }


}
