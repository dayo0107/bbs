package com.dayo.service.impl;

import com.dayo.mapper.RolePermissionMapper;
import com.dayo.pojo.Role;
import com.dayo.pojo.RolePermission;
import com.dayo.pojo.RolePermissionExample;
import com.dayo.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DayoWong on 2018/9/27
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    RolePermissionMapper rolePermissionMapper;
    @Override
    public void setPermissions(Role role, int[] permissionIds) {
        //删除当前角色所有的权限
        RolePermissionExample example= new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rps= rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps)
            rolePermissionMapper.deleteByPrimaryKey(rolePermission.getId());

        //设置新的权限关系
        if(null!=permissionIds)
            for (int pid : permissionIds) {
                RolePermission rolePermission = new RolePermission();
                rolePermission.setPid(pid);
                rolePermission.setRid(role.getId());
                rolePermissionMapper.insert(rolePermission);
            }
    }

    @Override
    public void deleteByRole(int roleId) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        for (RolePermission rp :
                rolePermissions) {
            rolePermissionMapper.deleteByPrimaryKey(rp.getId());
        }
    }

    @Override
    public void deleteByPermission(int permissionId) {
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andPidEqualTo(permissionId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectByExample(example);
        for (RolePermission rp :
                rolePermissions) {
            rolePermissionMapper.deleteByPrimaryKey(rp.getId());
        }
    }
}
