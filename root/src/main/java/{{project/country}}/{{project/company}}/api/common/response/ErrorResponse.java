package ru.peter.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ErrorResponse {

    private String error;
    private String message;
    private Integer code;
}
