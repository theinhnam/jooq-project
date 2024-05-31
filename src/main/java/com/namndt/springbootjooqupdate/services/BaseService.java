package com.namndt.springbootjooqupdate.services;

import com.namndt.springbootjooqupdate.data.models.Pageable;
import com.namndt.springbootjooqupdate.data.responses.DFResponse;
import org.springframework.security.core.Authentication;

public interface BaseService<Rq, Rp, Po, Id> {

    public DFResponse<Object> select(Pageable pageable, Authentication authentication);

    public DFResponse<Object> insert(Rq data, Authentication authentication);

    public DFResponse<Object> update(Rq data, Id id, Authentication authentication);

    public DFResponse<Object> delete(Id id, Authentication authentication);
}
