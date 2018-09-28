package com.dayo.service.impl;

import com.dayo.mapper.UserRoleMapper;
import com.dayo.pojo.User;
import com.dayo.pojo.UserRole;
import com.dayo.pojo.UserRoleExample;
import com.dayo.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DayoWong on 2018/9/27
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    @Override
    public void setRoles(User user, int[] roleIds) {
        //删除当前用户所有的角色
        UserRoleExample example= new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> urs= userRoleMapper.selectByExample(example);
        for (UserRole userRole : urs)
            userRoleMapper.deleteByPrimaryKey(userRole.getId());

        //设置新的角色关系
        if(null!=roleIds)
            for (int rid : roleIds) {
                UserRole userRole = new UserRole();
                userRole.setRid(rid);
                userRole.setUid(user.getId());
                userRoleMapper.insert(userRole);
            }
    }

    @Override
    public void updateRole(User user, int State) {
        UserRoleExample example= new UserRoleExample();
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> urs= userRoleMapper.selectByExample(example);
        if(urs!=null){
            if(urs.get(0) != null){
                urs.get(0).setRid(State);
                userRoleMapper.updateByPrimaryKeySelective(urs.get(0));
            }
        }
    }

    @Override
    public void addRole(User user, int State) {
        UserRole userRole = new UserRole();
        userRole.setRid(State);
        userRole.setUid(user.getId());
        userRoleMapper.insert(userRole);
    }


    @Override
    public void deleteByUser(int userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andUidEqualTo(userId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole ur :
                userRoles) {
            userRoleMapper.deleteByPrimaryKey(ur.getId());
        }
    }

    @Override
    public void deleteByRole(int roleId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria().andRidEqualTo(roleId);
        List<UserRole> userRoles = userRoleMapper.selectByExample(example);
        for (UserRole ur :
                userRoles) {
            userRoleMapper.deleteByPrimaryKey(ur.getId());
        }
    }
}
