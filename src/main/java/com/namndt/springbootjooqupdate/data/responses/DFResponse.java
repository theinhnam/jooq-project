package com.namndt.springbootjooqupdate.data.responses;

import lombok.Data;

@Data
public class DFResponse<T> {

    private String status;

    private String message;

    private T data;

    public DFResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
