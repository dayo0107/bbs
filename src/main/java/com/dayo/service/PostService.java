package com.dayo.service;

import com.dayo.pojo.Post;

import java.util.List;

/**
 * @author DayoWong on 2018/9/21
 */
public interface PostService {
    int add(Post post);
    int delete(Integer id);
    int update(Post post);
    Post get(Integer id);
    List<Post> list();
    List<Post> listByUid(Integer uid);
    List<Post> listByKeyword(String keyword);
}
