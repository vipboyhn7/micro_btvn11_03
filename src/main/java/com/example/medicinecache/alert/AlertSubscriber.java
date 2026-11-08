package com.example.medicinecache.alert;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AlertSubscriber {

    private static final Logger log = LoggerFactory.getLogger(AlertSubscriber.class);

    private final ObjectMapper objectMapper;

    public AlertSubscriber(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void receive(String payload) {
        try {
            AlertMessage alert = objectMapper.readValue(payload, AlertMessage.class);
            log.info("""
                    === THÔNG BÁO DASHBOARD QUẢN LÝ ===
                    Type: {}
                    Nội dung: {}
                    ================================
                    """, alert.type(), alert.message());
        } catch (JsonProcessingException exception) {
            log.error("Nhận được thông báo Redis không hợp lệ: {}", payload, exception);
        }
    }
}
