package com.dayo.service.impl;

import com.dayo.mapper.UserMapper;
import com.dayo.pojo.User;
import com.dayo.pojo.UserExample;
import com.dayo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DayoWong on 2018/9/19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int add(User user) {
        return userMapper.insert(user);
    }

    @Override
    public void delete(int uid) {
        userMapper.deleteByPrimaryKey(uid);
    }

    @Override
    public int update(User user) {
        return  userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User get(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.isEmpty())
            return null;
        return users.get(0);
    }

    @Override
    public User getByCode(String code) {
        UserExample example = new UserExample();
        example.createCriteria().andCodeEqualTo(code);
        List<User> users = userMapper.selectByExample(example);
        if(!users.isEmpty())
            return users.get(0);
        return null;
    }

    @Override
    public User getById(Integer uid) {
        return userMapper.selectByPrimaryKey(uid);
    }
}
