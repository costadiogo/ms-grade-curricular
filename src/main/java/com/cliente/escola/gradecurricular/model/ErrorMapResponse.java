package com.cliente.escola.gradecurricular.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Builder
@Getter
public class ErrorMapResponse {
    private int httpStatus;
    private Map<String, String> error;
    private Long timeStamp;
}
