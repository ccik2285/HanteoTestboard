package com.hanteo.board.common;
import com.hanteo.board.domain.category.dto.CategoryResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "API 응답 객체")
public class ResponseApi<T> {
    @Schema(description = "응답 메시지", example = "Success")
    private String message;

    @Schema(description = "응답 데이터", oneOf = {CategoryResponse.class}) // CategoryResponse 예시 추가
    private T data;

    public static <T> ResponseApi<T> success(T data) {
        return new ResponseApi<>("Success", data);
    }

    public static <T> ResponseApi<T> failure(String message) {
        return new ResponseApi<>(message, null);
    }
}