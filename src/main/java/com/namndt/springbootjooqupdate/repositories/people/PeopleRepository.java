package com.namndt.springbootjooqupdate.repositories.people;

import static com.namndt.springbootjooq.model.Tables.PEOPLE;

import com.namndt.springbootjooq.model.tables.pojos.People;
import com.namndt.springbootjooq.model.tables.records.PeopleRecord;
import com.namndt.springbootjooqupdate.repositories.AbsBaseRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class PeopleRepository extends AbsBaseRepository<People, Integer, PeopleRecord> implements IPeopleRepository{
    @Override
    protected TableImpl<PeopleRecord> getTable() {
        return PEOPLE;
    }

    @Override
    public People getPeopleByUser(String username) {
        return dslContext.selectFrom(getTable())
                .where(PEOPLE.USERNAME.eq(username))
                .fetchOne()
                .into(People.class);
    }
}
