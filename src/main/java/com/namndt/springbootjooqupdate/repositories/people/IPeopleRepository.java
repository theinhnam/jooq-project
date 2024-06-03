package com.namndt.springbootjooqupdate.repositories.people;

import com.namndt.springbootjooq.model.tables.pojos.People;

import java.util.Optional;

public interface IPeopleRepository {

    public Optional<People> getPeopleByUser(String username);
}
