package com.qf.service.impl;

import com.qf.mapper.IOrderMapper;
import com.qf.mapper.impl.OrderMapperImpl;
import com.qf.pojo.GoodsType;
import com.qf.pojo.Order;
import com.qf.pojo.Page;
import com.qf.service.IOrderService;
import com.qf.utils.PageUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements IOrderService {

    private IOrderMapper orderMapper=new OrderMapperImpl();
    @Override
    public Timestamp add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        String o_shaddress = request.getParameter("o_shaddress");
        System.out.println(o_shaddress);
        Map<String, String[]> map = request.getParameterMap();
        Order order = new Order();
        BeanUtils.populate(order,map);

        LocalDateTime date = LocalDateTime .now();
        Timestamp sqlDate = Timestamp.valueOf(date);
        order.setO_orderdate(sqlDate);
        orderMapper.add(order);


        Order query = query(request, response, sqlDate);
        System.out.println(query);
        return sqlDate;
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        orderMapper.delete(Integer.parseInt(id));
    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {

    }

    @Override
    public void getOrdersType(HttpServletRequest request, HttpServletResponse response) {

    }

    @Override
    public void getOrdersTypeList(HttpServletRequest request, HttpServletResponse response) {
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int count = 8;
        int offset = (curPage - 1) * count;
        int allCount = orderMapper.getAllCount();
        int totalPage = PageUtil.getTotalPage(allCount, count);
        String url = "ShopController?action=getOrderList&curPage=";
        List<Order> orders = orderMapper.query(offset, count);

        Page<Order> page = new Page<>(count, curPage, totalPage, url, orders);
        request.setAttribute("page", page);

    }

    @Override
    public List<Order> query(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Order query(HttpServletRequest request, HttpServletResponse response, Timestamp date) {
        Order order = orderMapper.query(date);
        return order;
    }
}
