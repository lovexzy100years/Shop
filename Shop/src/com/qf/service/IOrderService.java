package com.qf.service;

import com.qf.pojo.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

public interface IOrderService {
    public Timestamp add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void delete(HttpServletRequest request,HttpServletResponse response);

    public void update(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void getOrdersType(HttpServletRequest request,HttpServletResponse response);

    public void getOrdersTypeList(HttpServletRequest request,HttpServletResponse response);

    public List<Order> query(HttpServletRequest request,HttpServletResponse response);

    public Order query(HttpServletRequest request, HttpServletResponse response, Timestamp date);
}
