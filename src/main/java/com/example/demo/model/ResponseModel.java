package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel<T> {

    private Integer code;

    private String message;

    private T data;

    public static ResponseModel success(Object data) {
        return ResponseModel.builder().code(0).message("Success").data(data).build();
    }

    public static ResponseModel fail(Integer code, String message) {
        return ResponseModel.builder().code(code).message(message).build();
    }

}
