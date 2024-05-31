package com.namndt.springbootjooqupdate.data.responses;

import lombok.Data;

@Data
public class ClassesResponse {
    private Integer id;
    private String classCode;
    private Integer schoolId;
    private Integer status;
}
