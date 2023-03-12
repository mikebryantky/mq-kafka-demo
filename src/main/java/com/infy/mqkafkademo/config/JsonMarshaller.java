package com.infy.mqkafkademo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class JsonMarshaller {
    private final ObjectMapper objectMapper;

    public JsonMarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    public String marshal(Object object) {
        String json = "";
        try {
            json = objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            log.warn(e.getMessage(), e);
        }

        return json;
    }

    public <T extends Object> T unmarshal(String json, Class<?> clazz) {
        Object object = null;

        try {
            object = objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }

        return (T) object;
    }

    public <T> List<T> unmarshalList(String json, Class<?> clazz) {
        List<T> objects = new ArrayList<>();

        CollectionType type = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            objects = objectMapper.readValue(json, type);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
        }

        return objects;
    }

}
