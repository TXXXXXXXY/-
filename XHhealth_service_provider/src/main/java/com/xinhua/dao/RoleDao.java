package com.xinhua.dao;

import com.xinhua.pojo.Role;

import java.util.Set;

public interface RoleDao {
    public Set<Role> findByUserId(int userId);
}
