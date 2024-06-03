package com.namndt.springbootjooqupdate.data.dto;

import com.namndt.springbootjooq.model.tables.pojos.People;
import com.namndt.springbootjooqupdate.data.exception.AccountNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private int id;

    private String username;

    private String password;

    private List<String> roles;

    public UserDTO(String username) {
        this.username = username;
    }

    public UserDTO(String username, List<String> roles) {
        this.username = username;
        this.roles = roles;
    }

    public UserDTO notNull(UserDTO people){
        if (people == null){
            throw new AccountNotFoundException("Account not exists");
        }else{
            return people;
        }
    }

    public void setData(People people){
        this.username = people.getUsername();
        this.password = people.getPassword();
    }
}
