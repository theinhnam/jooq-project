package com.namndt.springbootjooqupdate.services;

import com.namndt.springbootjooq.model.tables.pojos.Classes;
import com.namndt.springbootjooqupdate.data.models.Pageable;
import com.namndt.springbootjooqupdate.data.responses.ClassesResponse;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface BaseService<Rq, Rp, Po, Id> {

    public List<Rp> select(Pageable pageable, Authentication authentication);

    public Rp insert(Rq data, Authentication authentication);

    public int update(Rq data, Id id, Authentication authentication);

    public int delete(Id id, Authentication authentication);
}
