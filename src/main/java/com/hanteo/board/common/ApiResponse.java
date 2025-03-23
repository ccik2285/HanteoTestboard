package com.hanteo.board.common;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final boolean success;
    private final T data;

    private ApiResponse(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data);
    }

    public static <T> ApiResponse<T> fail(T data) {
        return new ApiResponse<>(false, data);
    }
}