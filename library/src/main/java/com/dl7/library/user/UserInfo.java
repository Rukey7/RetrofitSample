package com.dl7.library.user;

/**
 * Created by long on 2016/6/28.
 * 用户信息
 */
public class UserInfo {


    /**
     * name : Long
     * age : 27
     * interest : DOTA
     */

    private String name;
    private int age;
    private String interest;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", interest='" + interest + '\'' +
                '}';
    }
}
