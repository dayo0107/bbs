package com.dayo.service.impl;

import com.dayo.mapper.PostMapper;
import com.dayo.pojo.Post;
import com.dayo.pojo.PostExample;
import com.dayo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DayoWong on 2018/9/21
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostMapper postMapper;
    @Override
    public int add(Post post) {
        return postMapper.insert(post);
    }

    @Override
    public int delete(Integer id) {
        return postMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Post post) {
        return postMapper.updateByPrimaryKeySelective(post);
    }

    @Override
    public Post get(Integer id) {
        return postMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Post> list() {
        PostExample example = new PostExample();
        example.setOrderByClause("id desc");
        return postMapper.selectByExample(example);
    }

    @Override
    public List<Post> listByUid(Integer uid) {
        PostExample example = new PostExample();
        example.createCriteria().andUidEqualTo(uid);
        example.setOrderByClause("id desc");
        return postMapper.selectByExample(example);
    }

    @Override
    public List<Post> listByKeyword(String keyword) {
        PostExample example = new PostExample();
        example.createCriteria().andTitleLike("%"+keyword+"%");
        example.setOrderByClause("id desc");
        return postMapper.selectByExample(example);
    }
}
