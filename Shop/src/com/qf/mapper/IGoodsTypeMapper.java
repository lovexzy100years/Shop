package com.qf.mapper;

import com.qf.pojo.GoodsType;

import java.util.List;

public interface IGoodsTypeMapper {

    public int add(GoodsType goodsType);

    public int delete(int id);

    public int update(GoodsType goodsType);

    public GoodsType query(int id);

    public List<GoodsType> query(int offset,int count);

    public int getAllcount(String tableName);

    public List<GoodsType> queryParentGoodsType();

    public List<GoodsType> queryChildGoodType(int parentId);

    public List<GoodsType> getAllGoodsType();
}
