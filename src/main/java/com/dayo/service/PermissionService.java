package com.dayo.service;



import com.dayo.pojo.Permission;
import com.dayo.pojo.Role;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    Set<String> listPermissions(String userName);

    List<Permission> list();
    void add(Permission permission);
    void delete(Integer id);
    Permission get(Integer id);
    void update(Permission permission);

    List<Permission> list(Role role);

    boolean needInterceptor(String requestURI);

    Set<String> listPermissionURLs(String userName);
}