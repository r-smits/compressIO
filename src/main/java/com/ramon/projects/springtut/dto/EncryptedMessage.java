package com.ramon.projects.springtut.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EncryptedMessage {

    private List<String> payload;

    public EncryptedMessage(List<String> payload) {
        this.payload = payload;
    }
}
