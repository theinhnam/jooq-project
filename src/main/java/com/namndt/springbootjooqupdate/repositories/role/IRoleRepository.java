package com.namndt.springbootjooqupdate.repositories.role;

import java.util.List;

public interface IRoleRepository {

    public List<String> getRoleNameByUserId(int userId);

}
