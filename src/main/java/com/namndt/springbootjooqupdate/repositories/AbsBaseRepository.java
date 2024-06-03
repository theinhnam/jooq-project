package com.namndt.springbootjooqupdate.repositories;

import com.namndt.springbootjooqupdate.data.models.Pageable;
import com.namndt.springbootjooqupdate.repositories.utils.PostgreUtils;
import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import org.jooq.DSLContext;
import org.jooq.TableField;
import org.jooq.impl.TableImpl;
import org.jooq.impl.TableRecordImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class AbsBaseRepository<Po, Id, R extends TableRecordImpl<R>> implements BaseRepository<Po, Id, R> {

    @Autowired
    protected DSLContext dslContext;

    protected abstract TableImpl<R> getTable();

    private Class<Po> pojoClass;

    private R record;

    private TableField<R, Id> fieldId;

    @PostConstruct
    @SneakyThrows
    public void init() {
        pojoClass = ( (Class<Po>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);

        record = ((Class<R>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2])
                .getDeclaredConstructor()
                .newInstance();

        fieldId = (TableField<R, Id>) Arrays.stream(getTable().fields()).filter(field -> field.getName()
                .equalsIgnoreCase("id"))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Po> select(Pageable pageable) {
        return dslContext.select()
                .from(getTable())
//                .where()
                .orderBy(PostgreUtils.buildSortQueries(pageable.getOrders(), getTable().fields()))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(pojoClass);
    }

    @Override
    public Po insertReturning(Po data) {
        return dslContext.insertInto(getTable())
                .set(PostgreUtils.buildInsertQueries(record, data))
                .returning()
                .fetchOne()
                .into(pojoClass);
    }

    @Override
    public int insert(Po data) {
        return 0;
    }

    @Override
    public int update(Po newData, Id id) {
        return dslContext.update(getTable())
                .set(PostgreUtils.buildInsertQueries(record, newData))
                .where(fieldId.eq(id))
                .execute();
    }

    @Override
    public int deleteById(Id id) {
        return dslContext.deleteFrom(getTable())
                .where(fieldId.eq(id))
                .execute();
    }

    @Override
    public Optional<Po> findById(Id id) {
        return dslContext.selectFrom(getTable())
                .where(fieldId.eq(id))
                .limit(1)
                .fetchOptionalInto(pojoClass);

    }
}
