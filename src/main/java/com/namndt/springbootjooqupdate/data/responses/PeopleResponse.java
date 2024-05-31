package com.namndt.springbootjooqupdate.data.responses;

import lombok.Data;

@Data
public class PeopleResponse {
    private String name;
    private String position;
    private String email;
    private String username;
    private String password;
}
