package com.lm.mapper;

import com.lm.pojo.User;
import com.lm.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {
    @Test
    void testSelectUser() {
        try(SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            for (User user : sqlSession.getMapper(UserMapper.class).selectUser()) {
                System.out.println(user);
            }

        }
    }

    @Test
    void testSelectUser2() {
        final ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        final UserMapper mapper = context.getBean("userMapperImpl2", UserMapper.class);
        for (User user : mapper.selectUser()) {
            System.out.println(user);

        }
    }
}