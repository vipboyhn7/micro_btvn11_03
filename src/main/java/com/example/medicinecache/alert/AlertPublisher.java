package com.example.medicinecache.alert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertPublisher {

    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    public AlertPublisher(StringRedisTemplate redisTemplate, ObjectMapper objectMapper) {
        this.redisTemplate = redisTemplate;
        this.objectMapper = objectMapper;
    }

    public void publish(AlertMessage alert) {
        try {
            redisTemplate.convertAndSend(alert.topicListen(), objectMapper.writeValueAsString(alert));
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException("Không thể tạo JSON cho thông báo Redis", exception);
        }
    }
}
