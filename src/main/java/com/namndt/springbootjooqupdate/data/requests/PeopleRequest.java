package com.namndt.springbootjooqupdate.data.requests;

import lombok.Data;

@Data
public class PeopleRequest {

    private String name;
    private String position;
    private String email;
    private String username;
    private String password;
}
