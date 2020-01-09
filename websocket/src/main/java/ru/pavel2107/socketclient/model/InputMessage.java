package ru.pavel2107.socketclient.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InputMessage {
    private String from;
    private String fromUniqueName;
    private String to;
    private String toUniqueName;
    private String text;
    private String sessionId;
}


