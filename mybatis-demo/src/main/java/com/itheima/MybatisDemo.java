package com.itheima;

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
 * @description: mybatis快速入门代码
 * @author: Mr.wyj
 * @create: 2022-10-22 15:44
 **/
public class MybatisDemo {
    public static void main(String[] args) throws IOException {
        //1、加载核心配置文件，获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2、获取SqlSession对象，执行SQL语句
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3、执行sql
        List<User> users = sqlSession.selectList("test.selectAll");

        //4、处理结果
        System.out.println(users);
        //5、释放资源
        sqlSession.close();
    }
}
