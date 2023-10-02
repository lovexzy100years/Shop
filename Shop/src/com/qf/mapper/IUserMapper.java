package com.qf.mapper;

import com.qf.pojo.User;

import java.util.List;

public interface IUserMapper {

    public int add(User user);

    public int delete(int id);

    public int update(User user);

    public User query(String username);

    public User query(String username,String password);

    //分页
    public List<User> query(int offset,int count);

    //获取总的页数
    public int getAllCount();
}
