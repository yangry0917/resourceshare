package com.jky.resourceshare.dao;

import com.jky.resourceshare.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {

    @Select("select user_name from tbl_user_info where user_name = #{userName}")
    User findUserByUsername(String userName);
}
