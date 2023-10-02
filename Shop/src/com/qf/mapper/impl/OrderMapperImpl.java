package com.qf.mapper.impl;

import com.qf.mapper.IOrderMapper;
import com.qf.pojo.GoodsType;
import com.qf.pojo.Order;
import com.qf.utils.DBUtil;

import java.sql.*;
import java.util.List;

public class OrderMapperImpl implements IOrderMapper {
    @Override
    public void add(Order order) {
        String sql="insert into t_order(o_sendtype,o_paytype,o_paycount,o_orderdate,userid,o_shperson,o_shphone,o_shaddress) values(?,?,?,?,?,?,?,?)";
        DBUtil.commonUpdate(sql, order.getO_sendtype(), order.getO_paytype(), order.getO_paycount(), order.getO_orderdate(), order.getUserid(), order.getO_shperson(), order.getO_shphone(),order.getO_shaddress());
    }

    @Override
    public void delete(int id) {
        String sql="delete from t_order where id=?";
        DBUtil.commonUpdate(sql,id);
    }

    @Override
    public int update(Order order) {
        String sql="update t_order set o_sendtype=?,o_paytype=?,o_paycount=?,o_orderdate=?,userid=?,o_shperson=?,o_shphone=?o_shaddress=? where id=?";
        int num = DBUtil.commonUpdate(sql, order.getO_sendtype(), order.getO_paytype(), order.getO_paycount(), order.getO_orderdate(), order.getUserid(), order.getO_shperson(),order.getO_shphone(), order.getO_shaddress(),order.getId()                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           );
        return num;
    }

    @Override
    public Order query(Timestamp date) {
        System.out.println(date);
        String sql ="select * from t_order where o_orderdate=?";
        List<Order> orders = DBUtil.commonQuery(Order.class, sql, date);
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }

    @Override
    public Order query(int id) {
        List<Order> orders = DBUtil.commonQuery(Order.class, "select * from t_order where id=?", id);
        if (orders.isEmpty()) {
            return null;
        }
        return orders.get(0);
    }

    @Override
    public List<Order> query(int offset, int count) {
        String sq1 = "select * from t_order limit ?,?";
        List<Order> orders = DBUtil.commonQuery(Order.class, sq1, offset, count);
        return orders;
    }


    @Override
    public int getAllCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(1) from  t_order";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                int allCount = resultSet.getInt(1);
                return allCount;
            }
        } catch (SQLException e) {
        }
        return -1;
    }

}
