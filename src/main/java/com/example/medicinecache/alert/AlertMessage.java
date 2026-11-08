package com.example.medicinecache.alert;

import jakarta.validation.constraints.NotBlank;

public record AlertMessage(
        @NotBlank String topicListen,
        @NotBlank String type,
        @NotBlank String message) {
}
