package com.namndt.springbootjooqupdate.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private int id;

    private String username;

    private List<String> roles;

    public UserDTO(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }
}
