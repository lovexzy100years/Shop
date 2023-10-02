package com.qf.mapper;


import com.qf.pojo.Order;

import java.sql.Timestamp;
import java.util.List;

public interface IOrderMapper {

    public void add(Order order);

    public void delete(int id);

    public int update(Order order);

    public Order query(Timestamp date);

    public Order query(int id);

    public List<Order> query(int offset, int count);

    public int getAllCount();

}
