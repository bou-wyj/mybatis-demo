package com.itheima;

import com.itheima.Mapper.UserMapper;
import com.itheima.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @program: mybatis
 * @description: mybatis —— Mapper代理开发
 * @author: Mr.wyj
 * @create: 2022-10-22 15:44
 **/
public class MybatisDemo2 {
    public static void main(String[] args) throws IOException {
        //1、加载核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取SqlSession对象，执行SQL语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2、1获取Mapper接口代理对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //3、执行方法，其实就时执行sql语句

        List<User> users = mapper.selectAll();
//        List<User> users = sqlSession.selectList("test.selectAll");--原来的方式

        //4、处理结果
        System.out.println(users);
        //5、释放资源
        sqlSession.close();
    }
}
