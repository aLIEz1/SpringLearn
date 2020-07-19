package com.lm.mapper;

import com.lm.pojo.User;

import java.util.List;

/**
 * @author super
 */
public interface UserMapper {

    /**
     * 查询用户
     * @return 用户列表
     */
    List<User> selectUser();
}
