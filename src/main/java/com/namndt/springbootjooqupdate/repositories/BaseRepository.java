package com.namndt.springbootjooqupdate.repositories;

import com.namndt.springbootjooqupdate.data.models.Pageable;

import java.util.List;
import java.util.Optional;

public interface BaseRepository <Po, Id, R>{

    public List<Po> select(Pageable pageable);

    public Po insertReturning(Po data);

    public int insert(Po data);

    public int update(Po newData, Id id);

    public int deleteById(Id id);

    public Optional<Po> findById(Id id);
}
