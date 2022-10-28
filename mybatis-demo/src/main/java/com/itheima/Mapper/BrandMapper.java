package com.itheima.Mapper;

import com.itheima.pojo.Brand;
import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis
 * @description: Mapper代理开发接口配置——Brand
 * @author: Mr.wyj
 * @create: 2022-10-22 22:34
 **/
public interface BrandMapper {
    //通过SQL语义明确知道返回值类型和方法名
    List<Brand> selectAll();

    //通过MyBatisX插件实现快捷插入statement语句
    Brand selectById(int id); //写好方法名后直接alt+enter可以快速在SQL映射文件中生成对应的statement语句

    //条件查询——使用注解 @Param来确认参数与占位符一一对应
    /**
     * * 参数接收
     *      1、散装参数：如果方法中有多个参数，需要使用@Param("SQL参数占位符名称")
     *      2、对象参数
     *      3、map集合参数
     */
    List<Brand> selectByCondition(@Param("status")int status,
                                @Param("companyName")String companyName ,
                                @Param("brandName")String brandName);
    List<Brand> selectByCondition(Brand brand);
    List<Brand> selectByCondition(Map map);

    /**
     *单条件的动态查询
     *
     */
    List<Brand> selectByConditionSingle(Brand brand);


    /**
     * 添加
     */
    void add(Brand brand);

    /**
     * 修改
     */
    int update(Brand brand);

    /**
     * 删除
     */
    void deleteById(Brand brand);
    /**
     * 删除
     */
    void deleteByIds(@Param("ids")int[] ids);

}
