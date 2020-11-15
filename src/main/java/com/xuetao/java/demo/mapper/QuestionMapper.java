package com.xuetao.java.demo.mapper;

import com.xuetao.java.demo.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: TestRookie * @Date: 2020/11/7 19:56 * @Description: *
 */
@Component
@Mapper
public interface QuestionMapper {
    @Insert("insert into question (TITLE,DESCRIPTION,GMT_CREATE,GMT_MODIFIED,CREATOR,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from QUESTION limit #{offset},#{size}")
    List<Question> list(@Param("offset")Integer offset, @Param("size") Integer size);

    @Select("select count(1) from question")
    Integer count();

}
