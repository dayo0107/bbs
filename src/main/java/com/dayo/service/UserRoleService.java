package com.dayo.service;


import com.dayo.pojo.User;

/**
 * @author DayoWong on 2018/9/14
 */
public interface UserRoleService {
    void setRoles(User user, int[] roleIds);
    void updateRole(User user ,int State);
    void addRole(User user ,int State);
    void deleteByUser(int userId);
    void deleteByRole(int roleId);
}
