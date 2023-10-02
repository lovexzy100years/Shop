package com.qf.service.impl;

import com.qf.mapper.IOrderDetailMapper;
import com.qf.mapper.impl.OrderDetailMapperImpl;
import com.qf.pojo.Order;
import com.qf.pojo.OrderDetail;
import com.qf.pojo.Page;
import com.qf.service.IOrderDetailService;
import com.qf.service.IOrderService;
import com.qf.utils.PageUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class OrderDetailServiceImpl implements IOrderDetailService {
    IOrderDetailMapper orderDetailMapper = new OrderDetailMapperImpl();
    IOrderService orderService = new OrderServiceImpl();

    @Override
    public void addOrderDetail(HttpServletRequest request, HttpServletResponse response, Timestamp date) throws InvocationTargetException, InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        OrderDetail orderDetail = new OrderDetail();
        BeanUtils.populate(orderDetail,map);
        System.out.println(date);
        Order order = orderService.query(request, response, date);
        System.out.println(order);


        int orderId = order.getId();

        orderDetail.setGoods_date(date);
        orderDetail.setO_orderid(orderId);
        orderDetailMapper.add(orderDetail);
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        orderDetailMapper.delete(id);
    }

    @Override
    public List<OrderDetail> query(HttpServletRequest request, HttpServletResponse response) {
        List<OrderDetail> orderDetailList = orderDetailMapper.query();
        return orderDetailList;
    }

    @Override
    public void getOrderDetailList(HttpServletRequest request) {
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int count = 8;
        int offset = (curPage - 1) * count;
        int allCount = orderDetailMapper.getAllCount();
        int totalPage = PageUtil.getTotalPage(allCount, count);
        String url = "GoodsInfoController?action=getGoodsInfoList&curPage=";
        List<OrderDetail> orderDetails = orderDetailMapper.query(offset, count);

        Page<OrderDetail> page = new Page<>(count, curPage, totalPage, url, orderDetails);
        request.setAttribute("page", page);
    }
}
