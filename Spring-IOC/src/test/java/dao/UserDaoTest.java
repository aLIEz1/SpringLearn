package dao;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserServiceImpl;

class UserDaoTest {
    @Test
    void testGetUser() {
        //拿到Spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl service = (UserServiceImpl) context.getBean("userService");
        service.getUser();
    }
}