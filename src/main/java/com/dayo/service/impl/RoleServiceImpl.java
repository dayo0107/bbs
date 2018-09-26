package com.dayo.service.impl;

import com.dayo.mapper.RoleMapper;
import com.dayo.mapper.UserRoleMapper;
import com.dayo.pojo.*;
import com.dayo.service.RoleService;
import com.dayo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author DayoWong on 2018/9/27
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    UserService userService;

    @Override
    public Set<String> listRoleNames(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role :
                roles) {
            result.add(role.getName());
        }
        return result;
    }

    @Override
    public List<Role> listRoles(String userName) {
        User user = userService.get(userName);
        if(null == user)
            return  null;
        return listRoles(user);
    }

    @Override
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();

        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole userRole:
                userRoles) {
            Role role = roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }

        return roles;
    }

    @Override
    public List<Role> list() {
        RoleExample example =  new RoleExample();
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);
    }

    @Override
    public void add(Role role) {
        roleMapper.insert(role);
    }

    @Override
    public void delete(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Role get(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(Role role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }
}
