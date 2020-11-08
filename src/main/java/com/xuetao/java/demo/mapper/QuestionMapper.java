package com.xuetao.java.demo.mapper;

import com.xuetao.java.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: TestRookie * @Date: 2020/11/7 19:56 * @Description: *
 */
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);
}
