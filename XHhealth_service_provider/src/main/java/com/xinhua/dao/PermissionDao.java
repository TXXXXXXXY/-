package com.xinhua.dao;

import com.xinhua.pojo.Permission;

import java.util.Set;

public interface PermissionDao {
    public Set<Permission> findByRoleId(int roleId);
}
