package com.itheima.test;

import com.itheima.Mapper.BrandMapper;
import com.itheima.pojo.Brand;
import org.apache.ibatis.io.Resources;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: mybatis
 * @description: 测试用例
 * @author: Mr.wyj
 * @create: 2022-10-23 09:16
 **/
public class MyBatisTest {
    //查询所有
    @Test
    public void selectAll() throws IOException {
        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        List<Brand> brands = brandMapper.selectAll();
        //4、输出结果
        System.out.println(brands);
        //5、释放资源
        sqlSession.close();
    }

    //查看详情
    @Test
    public void selectById() throws IOException {
        //模拟传递参数id
        int id = 1;

        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        Brand brands = brandMapper.selectById(id);
        //4、输出结果
        System.out.println(brands);
        //5、释放资源
        sqlSession.close();
    }

    //条件查询
    @Test
    public void selectByCondition() throws IOException {
        //模拟传递参数
        int status = 1;
        String companyName = "华为";
        String brandName = "华为";
        //处理参数——保证模糊参数
        companyName = "%" + companyName + "%";
        brandName = "%" + brandName + "%";
        //参数方式二——封装对象
        Brand brand = new Brand();
        brand.setStatus(status);
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        //参数方式三——封装Map集合
        Map map = new HashMap();
//        map.put("status",status);
        map.put("companyName", companyName);
//        map.put("brandName",brandName);
        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        //3、执行接口中对应的方法
        //3.1参数设置1——散装参数
        List<Brand> brands1 = brandMapper.selectByCondition(status, companyName, brandName);
        //3.2参数设置2——对象参数
        List<Brand> brands2 = brandMapper.selectByCondition(brand);
        //3.3参数设置3——Map集合
        List<Brand> brands3 = brandMapper.selectByCondition(map);

        //4、输出结果
//        System.out.println(brands1);
//        System.out.println(brands2);
        System.out.println(brands3);
        //5、释放资源
        sqlSession.close();
    }

    //单条件动态查询
    @Test
    public void selectByConditionSingle() throws IOException {
        //模拟传递参数——单条件
        String companyName = "华为";
        //处理数据
        companyName = "%" + companyName + "%";
        //封装数据
        Brand brand = new Brand();
//        brand.setCompanyName(companyName); //如果用户一个都不选的话，就会执行otherwise里面的语句

        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        List<Brand> brands = brandMapper.selectByConditionSingle(brand);
        //4、输出结果
        System.out.println(brands);
        //5、释放资源
        sqlSession.close();
    }

    //信息添加
    @Test
    public void testAdd() throws IOException {
        //模拟传递参数——添加用
        String brandName = "菠萝";
        String companyName = "菠萝手机";
        Integer ordered = 100;
        String description = "美国有苹果，中国有菠萝，菠萝手机，手机中的战斗机~";
        Integer status = 1;

        //封装数据
        Brand brand = new Brand();
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setStatus(status);
        brand.setOrdered(ordered);
        brand.setDescription(description);

        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //开启了自动提交事务
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        brandMapper.add(brand);
        //4、提交事务
//        sqlSession.commit();
        //5、释放资源
        sqlSession.close();
    }


    //信息添加——包含主键返回值
    @Test
    public void testAdd2() throws IOException {
        //模拟传递参数——添加用
        String brandName = "菠萝";
        String companyName = "菠萝手机";
        Integer ordered = 100;
        String description = "美国有苹果，中国有菠萝，菠萝手机，手机中的战斗机~";
        Integer status = 1;

        //封装数据
        Brand brand = new Brand();
        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setStatus(status);
        brand.setOrdered(ordered);
        brand.setDescription(description);

        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //开启了自动提交事务
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        brandMapper.add(brand);
        //3.1获取添加后的主键的值
        Integer id = brand.getId();
        System.out.println(id);

        //4、提交事务
//        sqlSession.commit();
        //5、释放资源
        sqlSession.close();
    }

    //信息修改——依据主键修改
    @Test
    public void testUpdate() throws IOException {
        //模拟传递参数——修改用
        String brandName = "菠萝";
        String companyName = "菠萝手机";
        Integer ordered = 100;
        String description = "美国有苹果，中国有菠萝，菠萝手机，手机中的战斗机 欧耶~";
        Integer status = 0;
        int id = 6;

        //封装数据
        Brand brand = new Brand();
/*        brand.setCompanyName(companyName);
        brand.setBrandName(brandName);
        brand.setStatus(status);
        brand.setOrdered(ordered);*/
        brand.setDescription(description);
        brand.setId(id);
        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //开启了自动提交事务
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        int update = brandMapper.update(brand);
        System.out.println(update);
        //5、释放资源
        sqlSession.close();
    }


    //信息删除——根据主键删除
    @Test
    public void testDeleteById() throws IOException {
       //传递主键信息
        int id = 6;

        //封装数据
        Brand brand = new Brand();
        brand.setId(id);

        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //开启了自动提交事务
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        brandMapper.deleteById(brand);
        //5、释放资源
        sqlSession.close();
    }
    //信息删除——根据主键数组批量删除
    @Test
    public void testDeleteByIds() throws IOException {
        //传递主键信息
        int[] ids = {5,7,8};
        //1、加载Mybatis核心配置文件,获取SqlSessionFactory对象
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //2、获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession = sqlSessionFactory.openSession(true); //开启了自动提交事务
        //2.1获取Mapper接口对象
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //3、执行接口中对应的方法
        brandMapper.deleteByIds(ids);
        //5、释放资源
        sqlSession.close();
    }
}