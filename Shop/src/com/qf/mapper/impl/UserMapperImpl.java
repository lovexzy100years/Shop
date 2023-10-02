package com.qf.mapper.impl;

import com.qf.mapper.IUserMapper;
import com.qf.pojo.User;
import com.qf.utils.DBUtil;
import java.util.List;

public class UserMapperImpl implements IUserMapper {
    @Override
    public int add(User user) {
        String sql = "insert into t_user(username,password,sex,phone,is_admin) values(?,?,?,?,?)";
        int num = DBUtil.commonUpdate(sql,user.getUsername(), user.getPassword(),user.getSex(),user.getPhone(),user.getIs_admin());
        return num;
    }

    @Override
    public int delete(int id) {
        String sql = "delete from t_user where id=?";
        int num = DBUtil.commonUpdate(sql, id);
        return num;
    }

    @Override
    public int update(User user) {
        String sql = "update t_user set phone=?,sex=?,is_admin=? where username=?";
        int num = DBUtil.commonUpdate(sql, user.getPhone(),user.getSex(), user.getIs_admin(), user.getUsername());
        return num;
    }

    @Override
    public User query(String username) {
        List<User> users = DBUtil.commonQuery(User.class, "select * from t_user where username=?", username);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public User query(String username, String password) {
        List<User> users = DBUtil.commonQuery(User.class, "select * from t_user where username=? and password=?", username, password);
        if (users.isEmpty()) {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<User> query(int offset, int count) {
        String sql = "select * from t_user limit ?,?";
        List<User> users = DBUtil.commonQuery(User.class, sql, offset, count);
        return users;
    }

    @Override
    public int getAllCount() {
        int allCount = DBUtil.getAllCount("t_user");
        return allCount;
    }
}
