package com.dayo.service;



import com.dayo.pojo.Role;
import com.dayo.pojo.User;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Set<String> listRoleNames(String userName);
    List<Role> listRoles(String userName);
    List<Role> listRoles(User user);

    List<Role> list();
    void add(Role role);
    void delete(Integer id);
    Role get(Integer id);
    void update(Role role);

}