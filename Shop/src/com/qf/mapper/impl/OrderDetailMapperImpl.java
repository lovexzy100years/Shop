package com.qf.mapper.impl;

import com.qf.mapper.IOrderDetailMapper;
import com.qf.pojo.OrderDetail;
import com.qf.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailMapperImpl implements IOrderDetailMapper {
    @Override
    public void add(OrderDetail orderDetail) {
        String sql = "insert into t_order_detail(goods_date,o_orderid,goodsid,goodsname,goodsprice,goods_description,goodsnum,goodspic,goods_total_price) values(?,?,?,?,?,?,?,?,?)";
        DBUtil.commonUpdate(sql,orderDetail.getGoods_date(),orderDetail.getO_orderid(),orderDetail.getGoodsid(),orderDetail.getGoodsname(),orderDetail.getGoodsprice(),orderDetail.getGoods_description(),orderDetail.getGoodsnum(),orderDetail.getGoodspic(),orderDetail.getGoods_total_price());
    }

    @Override
    public void delete(int o_orderid) {
        String sql = "delete from t_order_detail where o_orderid = ?";
        DBUtil.commonUpdate(sql,o_orderid);
    }

    @Override
    public List<OrderDetail> query() {
        String sql = "select * from t_order_detail";
        List<OrderDetail> orderDetails = DBUtil.commonQuery(OrderDetail.class, sql);
        return orderDetails;
    }

    @Override
    public List<OrderDetail> query(int offset, int count) {

        String sql = "select * from t_order_detail limit ?,? ";
        List<OrderDetail> orderDetails = DBUtil.commonQuery(OrderDetail.class, sql, offset, count);
        return orderDetails;

    }

    @Override
    public int getAllCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(1) from  t_order_detail";
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

