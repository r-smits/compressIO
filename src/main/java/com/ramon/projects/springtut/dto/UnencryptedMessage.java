package com.ramon.projects.springtut.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UnencryptedMessage {

    private final String payload;

    @JsonCreator
    public UnencryptedMessage(@JsonProperty("payload") String payload) {
        this.payload = payload;
    }
}
