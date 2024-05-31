package com.namndt.springbootjooqupdate.repositories.classes;

import static com.namndt.springbootjooq.model.Tables.CLASSES;
import com.namndt.springbootjooq.model.tables.pojos.Classes;
import com.namndt.springbootjooq.model.tables.records.ClassesRecord;
import com.namndt.springbootjooqupdate.repositories.AbsBaseRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ClassesRepository extends AbsBaseRepository<Classes, Integer, ClassesRecord>
        implements IClassesRepository{

    @Override
    protected TableImpl<ClassesRecord> getTable() {
        return CLASSES;
    }
}
