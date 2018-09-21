package com.dayo.service;

import com.dayo.pojo.Reply;

import java.util.List;

/**
 * @author DayoWong on 2018/9/21
 */
public interface ReplyService {
    int add(Reply reply);
    int delete(Integer id);
    int update(Reply reply);
    Reply get(Integer id);
    List<Reply> list();
    List<Reply> listByPid(Integer pid);
    void fillUser(List<Reply> replies);
}
