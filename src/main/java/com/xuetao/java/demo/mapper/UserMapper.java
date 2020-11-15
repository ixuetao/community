package com.xuetao.java.demo.mapper;

import com.xuetao.java.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @Author: TestRookie * @Date: 2020/11/4 7:37 * @Description: *
 */
@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,ACCOUNT_ID,token,GMT_CREATE,GMT_MODIFIED,AVATAR_URL) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token") String token);

    @Select("select * from user where id = #{id}")
    User findById(@Param("id") Integer id);
}
