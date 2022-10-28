package com.itheima.pojo;

/**
 * @program: mybatis
 * @description: 用户实体类
 * @author: Mr.wyj
 * @create: 2022-10-22 15:31
 **/
public class User {
   private int id;
   private String userName;
   private String passWord;
   private String gender;
   private String addr;

    public User(int id, String userName, String passWord, String gender, String addr) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.gender = gender;
        this.addr = addr;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", gender='" + gender + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }
}
