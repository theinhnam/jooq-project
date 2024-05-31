package com.namndt.springbootjooqupdate.repositories.people;

import com.namndt.springbootjooq.model.tables.pojos.People;

public interface IPeopleRepository {

    public People getPeopleByUser(String username);
}
