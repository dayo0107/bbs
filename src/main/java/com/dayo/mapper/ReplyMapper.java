package com.dayo.mapper;

import com.dayo.pojo.Reply;
import com.dayo.pojo.ReplyExample;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    int insertSelective(Reply record);

    List<Reply> selectByExample(ReplyExample example);

    Reply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Reply record);

    int updateByPrimaryKey(Reply record);

    @Select("select count(*) from reply where pid=#{pid}")
    int count(int pid);
}