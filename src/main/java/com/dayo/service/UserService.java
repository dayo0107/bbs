package com.dayo.service;

import com.dayo.pojo.User;

import java.util.List;

/**
 * @author DayoWong on 2018/9/19
 */
public interface UserService {
    int add(User user);
    void delete(int uid);
    int update(User user);
    User get(String username);
    User getByCode(String code);
    User getById(Integer uid);

    List<User> list();
}
