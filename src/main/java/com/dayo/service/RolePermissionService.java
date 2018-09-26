package com.dayo.service;


import com.dayo.pojo.Role;

/**
 * @author DayoWong on 2018/9/14
 */
public interface RolePermissionService {
    void setPermissions(Role role, int[] permissionIds);
    void deleteByRole(int roleId);
    void deleteByPermission(int permissionId);
}
