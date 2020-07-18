# Spring

## spring简介

> Spring框架的Maven依赖

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.2.7.RELEASE</version>
</dependency>
```

## Spring优点：

- Spring是一个开源的免费框架（容器）
- Spring是一个轻量级的非侵入式的框架
- **控制反转（IOC）,面向切面编程（AOP）**
- 支持事务的处理

总结成一句话：Spring是一个轻量级的控制反转（IOC）和面向切面编程（AOP）的框架

## Spring拓展

- Spring Boot

- - 一个快速开发的脚手架
  - 约定大于配置

- Spring Cloud

- - Spring Cloud 是基于 Spring Boot实现的



现在大多数公司都在使用Spring Boot进行快速开发，学习Spring Boot的前提，需要完全掌握Spring及SpringMVC

**Spring 和SpringMVC有着承上启下的作用**



弊端：发展了太久，违背了原来的理念，配置十分繁琐，人称“配置地狱”

# IOC

## IOC理论推导

1. UserDao接口
2. UserDaoImil实现类
3. UserService业务接口
4. UserServiceImpl业务实现类

在之前的业务中，用户的需求可能影响原来的代码，因为我们可能需要根据用户的需求去修改源代码，如果程序代码量非常大，修改一次非常昂贵



我们使用一个set接口实现动态值的注入

- 之前程序是主动创建对象，控制权在程序员手上
- 使用set注入后，程序不再有主动性，而是变成了**被动的接收对象，控制权在用户手上**



这种思想，从本质上解决了问题，程序员不用再去管理对象的创建，系统的耦合性大大降低，可以更加专注在业务的实现(**解耦**)

service实现

```java
public class UserServiceImpl implements UserService{
    private UserDao userDao;

    //使用set方法实现了用户直接决定使用哪个实现方式，实现了解耦
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void getUser() {
        userDao.getUser();
    }
}
```



test方法

```java
class UserDaoTest {
    @Test
    void testGetUser() {
        UserServiceImpl service = new UserServiceImpl();
        //用户可以调用set方法，决定使用哪种实现方法，而不用程序员修改代码
        service.setUserDao(new UserDaoSqlServerImpl());
        service.getUser();
    }
}
```

## IOC本质

控制反转是一种设计思想，DI（依赖注入）是实现IOC的一种方法。在没有IOC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖关系完全硬编码在程序中，对象的创建由程序自己控制，控制反转后**将对象的创建转移给第三方**，所谓的控制反转就是：获得依赖对象的方式反转了



 **控制反转是一种描述（XML或注解）并通过第三方去生产或获取特定对象的方式。在Spring中实现控制反转的是IOC容器，其实现方法是依赖注入（DI）** 



## HelloSpring

> 改进后的UserDao操作

增加了beans.xml文件，将dao 的实现类全部交由Spring对象工厂创建

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="origin" class="dao.UserDaoOriginImpl"/>
    <bean id="sqlServer" class="dao.UserDaoSqlServerImpl"/>
    <bean id="mysql" class="dao.UserDapMysqlImpl"/>
    <bean id="userService" class="service.UserServiceImpl">
<!--        ref是引spring容器中创建好的对象-->
<!--        value是基本数据类型-->
        <property name="userDao" ref="mysql"/>
    </bean>
</beans>
```

测试类

```java
class UserDaoTest {
    @Test
    void testGetUser() {
        //拿到Spring的容器
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserServiceImpl service = (UserServiceImpl) context.getBean("userService");
        service.getUser();
    }
}
```

## IOC创建对象的方式



1. 使用无参构造器创建对象（默认）
2. 假设我们要使用有参数的构造器创建对象

1. 1. 下标赋值

```xml
<bean id="user" class="com.lm.pojo.User">
  <constructor-arg index="0" value="Java"/>
</bean>
```

1. 1. 参数类型赋值（不推荐）



一旦将对象注册进bean，无论有没有get这个bean都会被实例化，**注册即实例化**

# Spring配置

## bean的配置



```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
<!--    bean = 对象 一个bean-->
<!--    Hello hello =new Hello()-->
<!--    id对应的是hello（变量名）-->
<!--    class对应的是new Hello()-->
<!--    property相当于给属性赋值 name是属性的名字value是要给属性赋的值-->
<!--    name属性为起的别名 name更高级，可以同时取多个别名-->
<bean id="hello" class="com.lm.pojo.Hello" name="hello2,h2">
    <property name="name" value="Spring"/>
</bean>
</beans>
```

## import

> import一般用于团队开发，可以将多个配置文件导入合并为一个



# 依赖注入（DI）

## 构造器注入



## set方式注入（重点）

- 依赖注入：set注入

- - 依赖：bean对象的创建依赖于容器
  - 注入：bean对象中的所有属性由容器来注入

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="address" class="com.lm.pojo.Address"/>
<bean id="student" class="com.lm.pojo.Student">
<!--    普通值注入直接使用value-->
    <property name="name" value="lm"/>
<!--    Bean注入，ref-->
    <property name="address" ref="address"/>
<!--    数组注入-->
    <property name="books">
        <array>
            <value>红楼梦</value>
            <value>西游记</value>
            <value>水浒传</value>
        </array>
    </property>
<!--    List注入-->
    <property name="hobbies">
        <list>
            <value>唱</value>
            <value>跳</value>
            <value>rap</value>
        </list>
    </property>
<!--    Map注入-->
    <property name="card">
        <map>
            <entry key="a" value="1"/>
            <entry key="b" value="2"/>
            <entry key="c" value="3"/>
        </map>
    </property>
<!--    Set注入-->
    <property name="games">
        <set>
            <value>斗地主</value>
            <value>麻将</value>
            <value>三国杀</value>
        </set>
    </property>
<!--    Properties注入-->
    <property name="info">
        <props>
            <prop key="学号">123456</prop>
            <prop key="性别">男</prop>
            <prop key="username">lm</prop>
            <prop key="password">123123</prop>
        </props>
    </property>
    <property name="wife">
        <null/>
    </property>
</bean>
</beans>
```

## 拓展方式注入



p命名空间和c命名空间

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
<!--    p命名空间注入可以直接注入属性的值-->
<!--    c命名空间可以方便实现有参构造器的注入-->
    <bean id="user" class="com.lm.pojo.User" p:name="lm" p:age="18"/>
    <bean id="user2" class="com.lm.pojo.User" c:name="lm2" c:age="10"/>
</beans>
```

## Bean的作用域



![image.png](https://cdn.nlark.com/yuque/0/2020/png/1792508/1595003649223-fd0e50c4-73b9-4597-b35b-f46d8aa032fc.png)

1. 单例模式（**全局唯一，Spring默认机制**）

```xml
    <bean id="user" class="com.lm.pojo.User" p:name="lm" p:age="18" scope="singleton"/>
```

1. 原型模式(**每次getBean都会产生一个新的对象，全局不唯一**)

```xml
    <bean id="user" class="com.lm.pojo.User" p:name="lm" p:age="18" scope="prototype"/>
```

1. request，session，application，websocket，只能在web开发中使用



# Bean的自动装配

- 自动装配是Spring满足bean依赖的一种方式
- Spring会在上下文中自动寻找，并自动给bean装配属性



在Spring中有三种装配的方式

1. 在xml显式配置
2. 在java中显式配置
3. **隐式自动装配bean**



# 使用Java的方式配置Spring

> 我们现在要完全不适用Spring的xml配置了，全交给Java来做
>
> JavaConfig是Spring的一个子项目，在Spring4之后，它成为了一个核心功能

ApplicationContext的实现类

![image.png](https://cdn.nlark.com/yuque/0/2020/png/1792508/1595073858186-ff44bc77-692e-4a07-a69d-a0507cdc9fd8.png)



实体类

```java
package com.lm.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author super
 * Component 注解 说明这个类被Spring接管了也就是说注册到了容器中
 */
@Component
public class User {
    /**
     * 属性注入值
     */
    @Value("lm")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

```

JavaConfig

```java
package com.lm.config;

import com.lm.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author super
 * Configuration 这个也会被Spring托管，注册到容器中，自己本省就是一个 Component
 * Configuration 表示这是一个配置类，就是和beans.xml一样的
 */
@Configuration
@ComponentScan("com.lm.pojo")
public class LmConfig {
    /**
     * 注册 Bean 相当于之前写的<bean></bean>
     * 这个方法的名字就相当于bean标签中的id属性
     * 这个方法的返回值就相当于bean标签中的class对象
     * @return user类
     */
    @Bean
    public User getUser(){
        //返回要注入到bean中的对象
        return new User();
    }
}

```

测试类

```java
package com.lm.pojo;

import com.lm.config.LmConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void testGetUser() {
        //如果完全使用了配置类方式去做，
        // 我们就只能通过AnnotationConfigApplicationContext上下文来获取配置，
        // 通过配置类的class对象加载
        ApplicationContext context =new AnnotationConfigApplicationContext(LmConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user.getName());
    }
}
```



**这种纯java的配置方式，在Spring Boot中随处可见**

# 代理模式

为什么要学习代理模式？因为这是**Spring AOP的底层实现**

代理模式的分类：

1. 静态代理
2. 动态代理

角色分析：

- 抽象角色：一般会使用接口或者抽象类来解决
- 真实角色：被代理的角色
- 代理角色：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- 客户：访问代理对象的人



代理模式的好处：

- 可以使真实角色的操作更加纯粹，不用去关注以哦写公共的业务
- 公共业务交给代理角色，实现了业务的分工
- 公共业务发生扩展的时候，方便集中管理

缺点：一个真实角色就会产生一个代理角色，代码量会翻倍，**开发效率会变低**



## 静态代理

代码步骤：

1. 接口

```java
package com.lm.demo01;

/**
 * @author super
 * 模拟租房接口
 */
public interface Rent {
    /**
     * 租房方法
     */
    void rent();
}

```



2. 真实角色

```java
package com.lm.demo01;

/**
 * @author super
 * 房东
 */
public class Host implements Rent
{
    @Override
    public void rent() {
        System.out.println("房东要出租房子");
    }
}

```



3. 代理角色

```java
package com.lm.demo01;

/**
 * @author super
 * 代理帮房东出租房子
 */
public class Proxy implements Rent{
    private Host host;

    public Proxy(Host host) {
        this.host = host;
    }

    public Proxy() {
    }


    @Override
    public void rent() {
        seeHouse();
        host.rent();
        signContract();
        fare();

    }

    /**
     * 看房方法
     */
    private void seeHouse(){
        System.out.println("中介带你看房");
    }

    /**
     * 收中介费
     */
    private void fare(){
        System.out.println("中介收取中介费");
    }

    /**
     * 签合同
     */
    private void signContract(){
        System.out.println("签合同");
    }
}

```



4. 客户端访问代理角色

```java
package com.lm.demo01;

/**
 * @author super
 */
public class Client {
    public static void main(String[] args) {
        //代理，中介帮房东出租房子
        // 代理角色一般会有一些附属操作
        // 你不用面对房东，直接找中介租房即可
        Proxy proxy = new Proxy(new Host());
        proxy.rent();
    }
}

```

## 静态代理再理解：

在原有的crud基础上增加日志的功能，不可能去一行一行改原有的代码，使用静态代理实现增加的日志功能

业务接口：

```java
package com.lm.demo02;

/**
 * @author super
 */
public interface UserService {
    /**
     * 增
     */
    void add();

    /**
     * 删
     */
    void delete();

    /**
     * 改
     */
    void update();

    /**
     * 查
     */
    void query();

}

```

增删改查实现类

```java
package com.lm.demo02;

public class UserServiceImpl implements UserService{
    @Override
    public void add() {
        System.out.println("增加了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");

    }

    @Override
    public void update() {
        System.out.println("更改了一个用户");

    }

    @Override
    public void query() {
        System.out.println("查询了一个用户");

    }
}

```



日志实现类（使用了Spring中推荐的set方法注入对象，而不是使用构造器）

```java
package com.lm.demo02;

public class UserServiceProxyImpl implements UserService{
    private UserServiceImpl service;

    public void setService(UserServiceImpl service) {
        this.service = service;
    }

    @Override
    public void add() {
        log("add");
        service.add();
    }

    @Override
    public void delete() {
        log("delete");
        service.delete();
    }

    @Override
    public void update() {
        log("update");
        service.update();

    }

    @Override
    public void query() {
        log("query");
        service.query();

    }
    private void log(String msg){
        System.out.println("使用了"+msg+"方法");
    }
}

```

```java
package com.lm.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceProxyImpl proxy =new UserServiceProxyImpl();
        proxy.setService(new UserServiceImpl());
        proxy.add();
        System.out.println("===================");
        proxy.delete();
        System.out.println("===================");
        proxy.update();
        System.out.println("===================");
        proxy.query();
    }
}
```



测试类

```java
package com.lm.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceProxyImpl proxy =new UserServiceProxyImpl();
        proxy.setService(new UserServiceImpl());
        proxy.add();
        System.out.println("===================");
        proxy.delete();
        System.out.println("===================");
        proxy.update();
        System.out.println("===================");
        proxy.query();
    }
}

```



## 动态代理

- 动态代理和静态代理角色一样
- 动态代理类是动态生成的，不是我们直接写好的
- 动态代理分为两大类
  - 基于接口的动态代理：JDK的动态代理
  - 基于类的动态代理：cglib
  -  java字节码实现： Javasist



需要了解两个类：

- Proxy：代理
- InvocationHandler ：调用处理程序



动态代理的好处

- 一个动态代理类代理的是一个接口，一般是对应的**一类业务**
- **一个动态代理类可以代理多个类，只要这些类实现了同一个接口即可**，不用像静态代理一样每个类都要重写一种方法