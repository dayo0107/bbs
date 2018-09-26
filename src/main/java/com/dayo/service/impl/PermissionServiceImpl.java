package com.dayo.service.impl;

import com.dayo.mapper.PermissionMapper;
import com.dayo.mapper.RolePermissionMapper;
import com.dayo.pojo.*;
import com.dayo.service.PermissionService;
import com.dayo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author DayoWong on 2018/9/26
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Autowired
    RoleService roleService;
    @Override
    public Set<String> listPermissions(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = roleService.listRoles(userName);
        for (Role role :
                roles) {
            List<Permission> permissions = list(role);
            for (Permission p :
                    permissions) {
                result.add(p.getName());
            }
        }

        return result;
    }

    @Override
    public List<Permission> list() {
        PermissionExample example = new PermissionExample();
        example.setOrderByClause("id desc");
        return permissionMapper.selectByExample(example);
    }

    @Override
    public void add(Permission permission) {
        permissionMapper.insert(permission);
    }

    @Override
    public void delete(Integer id) {
        permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Permission get(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Permission permission) {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Override
    public List<Permission> list(Role role) {
        List<Permission> permissions = new ArrayList<>();
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        for (RolePermission rp :
                rolePermissions) {
            permissions.add(permissionMapper.selectByPrimaryKey(rp.getPid()));
        }
        return permissions;
    }

    @Override
    public boolean needInterceptor(String requestURI) {
        List<Permission> permissions = list();
        for (Permission p :
                permissions) {
            if (p.getUrl().equals(requestURI))
                return  true;
        }
        return false;
    }

    @Override
    public Set<String> listPermissionURLs(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = roleService.listRoles(userName);
        for (Role r : roles) {
            List<Permission> permissions = list(r);
            for (Permission p: permissions) {
                result.add(p.getUrl());
            }
        }
        return result;
    }
}
