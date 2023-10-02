package com.qf.service;

import com.qf.pojo.OrderDetail;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;

public interface IOrderDetailService {

    public void addOrderDetail(HttpServletRequest request, HttpServletResponse response, Timestamp date) throws InvocationTargetException, IllegalAccessException;

    public void delete(HttpServletRequest request, HttpServletResponse response);

    public List<OrderDetail> query(HttpServletRequest request, HttpServletResponse response);

    public void getOrderDetailList(HttpServletRequest request);

}
