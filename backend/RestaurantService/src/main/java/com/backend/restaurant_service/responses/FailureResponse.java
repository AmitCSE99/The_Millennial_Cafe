package com.backend.restaurant_service.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FailureResponse {

    private boolean success;
    private int status;
    private String exceptionClass;
    private String message;
}
