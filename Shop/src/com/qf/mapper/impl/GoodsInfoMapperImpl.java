package com.qf.mapper.impl;

import com.qf.mapper.IGoodsInfoMapper;
import com.qf.pojo.GoodsInfo;
import com.qf.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GoodsInfoMapperImpl implements IGoodsInfoMapper {
    @Override
    public int add(GoodsInfo goodsInfo) {
        String sql="insert into t_goods_info (goods_name,goods_description,goods_pic,goods_price,goods_stock,goods_price_off,goods_discount,goods_fatherid,goods_parentid,isdelete) value(?,?,?,?,?,?,?,?,?,'否')";
        int num = DBUtil.commonInsert(sql, goodsInfo.getGoods_name(), goodsInfo.getGoods_description(),goodsInfo.getGoods_pic(), goodsInfo.getGoods_price(), goodsInfo.getGoods_stock(), goodsInfo.getGoods_price_off(), goodsInfo.getGoods_discount(), goodsInfo.getGoods_fatherid(), goodsInfo.getGoods_parentid());
        return num;
    }

    @Override
    public int delete(int id) {
        String sql="update t_goods_info set isdelete='是' where id=?";
        int num = DBUtil.commonUpdate(sql,id);
        return num;
    }

    @Override
    public int update(GoodsInfo goodsInfo) {
        String sql="update t_goods_info set goods_name=?,goods_description=?,goods_pic=?,goods_price=?,goods_stock=?,goods_price_off=?,goods_discount=?,goods_fatherid=?,goods_parentid=? where id=? and isdelete='否'";
        int num = DBUtil.commonUpdate(sql, goodsInfo.getGoods_name(), goodsInfo.getGoods_description(), goodsInfo.getGoods_pic(),goodsInfo.getGoods_price(), goodsInfo.getGoods_stock(), goodsInfo.getGoods_price_off(), goodsInfo.getGoods_discount(), goodsInfo.getGoods_fatherid(), goodsInfo.getGoods_parentid(), goodsInfo.getId());
        return num;
    }

    @Override
    public GoodsInfo query(int id) {
        String sql="select * from t_goods_info where id=? and isdelete='否'";
        List<GoodsInfo> goodsInfos = DBUtil.commonQuery(GoodsInfo.class, sql, id);
        if (goodsInfos.isEmpty()){
            return null;
        }
        return goodsInfos.get(0);
    }

    @Override
    public List<GoodsInfo> query(int offset, int count) {
        String sq1 = "select * from t_goods_info limit ?,?";
        List<GoodsInfo> goodsInfos = DBUtil.commonQuery(GoodsInfo.class, sq1, offset, count);
        return goodsInfos;
    }

    @Override
    public int getAllCount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtil.getConnection();
            String sql = "select count(1) from  t_goods_info where isdelete='否'";
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
    public List<GoodsInfo> getAllGoodsInfo() {
        String sql="select * from t_goods_info where isdelete='否'";
        List<GoodsInfo> goodsInfos = DBUtil.commonQuery(GoodsInfo.class, sql);
        return goodsInfos;
    }
}
