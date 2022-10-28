package com.itheima.Mapper;

import com.itheima.pojo.User;

import java.util.List;

/**
 * @program: mybatis
 * @description: Mapper代理开发接口配置
 * @author: Mr.wyj
 * @create: 2022-10-22 22:34
 **/
public interface UserMapper {
    //通过SQL语义明确知道返回值类型和方法名
    List<User> selectAll();

    //通过MyBatisX插件实现快捷插入statement语句
    User selectById(int id); //写好方法名后直接alt+enter可以快速在SQL映射文件中生成对应的statement语句
}
