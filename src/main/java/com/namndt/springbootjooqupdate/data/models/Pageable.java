package com.namndt.springbootjooqupdate.data.models;

import lombok.Data;

import java.util.List;

@Data
public class Pageable {

    private final Integer DEFAULT_PAGE = 0;

    private Integer page;

    private Integer pageSize = 5;

    private List<Order> orders;

    private Integer offset;

//    public Pageable() {
//        page = DEFAULT_PAGE;
//        offset = Math.max((page - 1) * pageSize , 0);
//    }

    public Pageable(Integer page, List<Order> orders) {
        this.page = page;
        this.orders = orders;
        this.offset = Math.max((page - 1) * pageSize, 0);
    }
}
