package com.backend.restaurant_service.responses;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class SuccessResponse<T> {

    private boolean success;
    private int status;
    private T data;
}
