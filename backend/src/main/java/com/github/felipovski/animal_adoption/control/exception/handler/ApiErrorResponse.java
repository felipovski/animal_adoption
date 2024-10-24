package com.github.felipovski.animal_adoption.control.exception.handler;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ApiErrorResponse{

    @JsonProperty("guid")
    String guid;
    @JsonProperty("errorCode")
    String errorCode;
    @JsonProperty("message")
    String message;
    @JsonProperty("statusCode")
    Integer statusCode;
    @JsonProperty("statusName")
    String statusName;
    @JsonProperty("path")
    String path;
    @JsonProperty("method")
    String method;
    @JsonProperty("timestamp")
    LocalDateTime timestamp;

    public ApiErrorResponse(String guid, String errorCode, String message, Integer statusCode,
                            String statusName, String path, String method) {
        this.guid = guid;
        this.errorCode = errorCode;
        this.message = message;
        this.statusCode = statusCode;
        this.statusName = statusName;
        this.path = path;
        this.method = method;
        this.timestamp = LocalDateTime.now();
    }
}
