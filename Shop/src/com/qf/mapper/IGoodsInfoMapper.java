package com.qf.mapper;

import com.qf.pojo.GoodsInfo;

import java.util.List;

public interface IGoodsInfoMapper {

    public int add(GoodsInfo goodsInfo);

    public int delete(int id);

    public int update(GoodsInfo goodsInfo);

    public GoodsInfo query(int id);

    public List<GoodsInfo> query(int offset, int count);

    public int getAllCount();

    public List<GoodsInfo> getAllGoodsInfo();
}
