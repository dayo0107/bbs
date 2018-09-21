package com.dayo.service.impl;

import com.dayo.mapper.ReplyMapper;
import com.dayo.pojo.Reply;
import com.dayo.pojo.ReplyExample;
import com.dayo.service.ReplyService;
import com.dayo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DayoWong on 2018/9/21
 */
@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    ReplyMapper replyMapper;

    @Autowired
    UserService userService;

    @Override
    public int add(Reply reply) {
        return replyMapper.insert(reply);
    }

    @Override
    public int delete(Integer id) {
        return replyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Reply reply) {
        return replyMapper.updateByPrimaryKeySelective(reply);
    }

    @Override
    public Reply get(Integer id) {
        return replyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Reply> list() {
        ReplyExample example = new ReplyExample();
        example.setOrderByClause("id asc");
        return replyMapper.selectByExample(example);
    }

    @Override
    public List<Reply> listByPid(Integer pid) {
        ReplyExample example = new ReplyExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id asc");
        return replyMapper.selectByExample(example);
    }

    @Override
    public void fillUser(List<Reply> replies) {
        for (Reply r : replies) {
            r.setUser(userService.getById(r.getUid()));
        }
    }
}
