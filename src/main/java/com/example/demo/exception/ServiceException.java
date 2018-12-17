package com.example.demo.exception;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    private int code;

    private String reason;

    public ServiceException() {
        super();
    }

    public ServiceException(int code, String reason) {
        super(reason);
        this.code = code;
        this.reason = reason;
    }

}
