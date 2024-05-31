package com.namndt.springbootjooqupdate.data.requests;

import lombok.Data;

@Data
public class ClassesRequest {
    private String classCode;
    private Integer schoolId;
    private Integer status;
}
