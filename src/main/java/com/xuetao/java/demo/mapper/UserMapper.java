package com.xuetao.java.demo.mapper;

import com.xuetao.java.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: TestRookie * @Date: 2020/11/4 7:37 * @Description: *
 */
@Mapper
public interface UserMapper {
    @Insert("inser into user(name,accountId,token,gmtCreate,gmtModified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}
