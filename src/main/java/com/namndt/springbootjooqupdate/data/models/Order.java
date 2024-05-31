package com.namndt.springbootjooqupdate.data.models;

import lombok.Data;

@Data
public class Order {

    private String field;

    private String direction;

    public enum direction{
        asc, desc
    }
}
