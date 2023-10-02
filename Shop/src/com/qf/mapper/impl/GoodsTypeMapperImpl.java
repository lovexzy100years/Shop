package com.qf.mapper.impl;

import com.qf.mapper.IGoodsTypeMapper;
import com.qf.pojo.GoodsType;
import com.qf.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsTypeMapperImpl implements IGoodsTypeMapper {
    @Override
    public int add(GoodsType goodsType) {
        String sql="insert into t_goods_type(gtype_name,gtype_parentid) values(?,?)";
        int num = DBUtil.commonUpdate(sql, goodsType.getGtype_name(), goodsType.getGtype_parentid());
        return num;
    }

    @Override
    public int delete(int id) {
        String sql="delete from t_goods_type where id=?";
        int num = DBUtil.commonUpdate(sql, id);
        return num;
    }

    @Override
    public int update(GoodsType goodsType) {
        String sql="update t_goods_type set gtype_name=?,gtype_parentid=? where id=?";
        int num = DBUtil.commonUpdate(sql, goodsType.getGtype_name(), goodsType.getGtype_parentid(),goodsType.getId());
        return num;
    }

    @Override
    public GoodsType query(int id) {
        List<GoodsType> goodsTypes = DBUtil.commonQuery(GoodsType.class, "select * from t_goods_type where id=?", id);
        if (goodsTypes.isEmpty()) {
            return null;
        }
        return goodsTypes.get(0);
    }

    @Override
    public List<GoodsType> query(int offset, int count) {
        String sq1 = "select * from t_goods_type limit ?,?";
        List<GoodsType> goodsTypes = DBUtil.commonQuery(GoodsType.class, sq1, offset, count);
        return goodsTypes;
    }

    @Override
    public int getAllcount(String tableName) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(1) from  " + tableName;
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


    @Override
    public List<GoodsType> queryParentGoodsType() {
        String sql = "select * from t_goods_type where gtype_parentid=0";
        List<GoodsType> goodsTypes = DBUtil.commonQuery(GoodsType.class, sql);
        return goodsTypes;
    }

    @Override
    public List<GoodsType> queryChildGoodType(int parentId) {
        String sql = "select * from t_goods_type where gtype_parentid=?";
        List<GoodsType> childGoodsTypes = DBUtil.commonQuery(GoodsType.class, sql, parentId);
        return childGoodsTypes;
    }

    @Override
    public List<GoodsType> getAllGoodsType() {
        String sql="select * from t_goods_type";
        List<GoodsType> goodsTypes = DBUtil.commonQuery(GoodsType.class, sql);
        return goodsTypes;
    }
}
