package com.qf.mapper;

import com.qf.pojo.OrderDetail;

import java.util.List;

public interface IOrderDetailMapper {
    public void add(OrderDetail orderDetail);

    public void delete(int o_orderid);

    public List<OrderDetail> query();

    public List<OrderDetail> query(int offset,int count);

    public int getAllCount();
}
