package com.namndt.springbootjooqupdate.repositories.role;

import static com.namndt.springbootjooq.model.Tables.PERMISSION;
import static com.namndt.springbootjooq.model.Tables.ROLE;
import com.namndt.springbootjooq.model.tables.pojos.Role;
import com.namndt.springbootjooq.model.tables.records.RoleRecord;
import com.namndt.springbootjooqupdate.repositories.AbsBaseRepository;
import org.jooq.impl.TableImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleRepository extends AbsBaseRepository<Role, Integer, RoleRecord> implements IRoleRepository{
    @Override
    protected TableImpl<RoleRecord> getTable() {
        return ROLE;
    }

    @Override
    public List<String> getRoleNameByUserId(int userId) {
        return dslContext.select(ROLE.ROLE_NAME)
                .from(ROLE)
                .join(PERMISSION)
                .on(ROLE.ID.eq(PERMISSION.ROLE_ID))
                .where(PERMISSION.USER_ID.eq(userId))
                .fetchInto(String.class);
    }
}
