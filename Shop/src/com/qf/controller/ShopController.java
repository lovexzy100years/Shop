package com.qf.controller;

import com.qf.pojo.*;
import com.qf.service.*;
import com.qf.service.impl.*;
import com.qf.servlet.BaseServlet;
import com.qf.utils.DBUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@WebServlet("/ShopController")
public class ShopController extends BaseServlet {

    private IGoodsInfoService goodsInfoService=new GoodsInfoServiceImpl();
    private IGoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();
    private IShopService shopService=new ShopServiceImpl();
    private IAddressService addressService=new AddressServiceImpl();
    private IOrderService orderService=new OrderServiceImpl();
    private IOrderDetailService orderDetailService=new OrderDetailServiceImpl();

    public void initShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        goodsInfoService.getAllGoodsInfo(request);
        goodsTypeService.getAllGoodsType(request,response);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }

    public void addShopCar(HttpServletRequest request,HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("temporaryShopCar");
        shopService.addShopCar(request);
        response.sendRedirect("shopcat.jsp");

    }

    public void update(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String jsonStr = shopService.update(request);

        response.getWriter().write(jsonStr);
    }

    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        shopService.delete(request);
        response.sendRedirect("shopcat.jsp");
    }

    //初始化购物车支付
    public void initShopCatPay(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //判断用户是否登录
        String username = (String) request.getSession().getAttribute("username");
        String userId= (String) request.getSession().getAttribute("userId");
        String allCount = request.getParameter("allCount");
        request.setAttribute("allCount",allCount);

        if(username==null || userId==null){
            response.sendRedirect("login.jsp");
        }else{
            addressService.getAllAddress(request);
            request.getRequestDispatcher("pay.jsp").forward(request,response);
        }
    }

    //初始化立即支付
    public void initNowPay(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        //判断用户是否登录
        String username = (String) request.getSession().getAttribute("username");
        String userId= (String) request.getSession().getAttribute("userId");

        if(username==null || userId==null){
            response.sendRedirect("login.jsp");
        }else{

            int count = Integer.parseInt(request.getParameter("count"));
            System.out.println(count);

            GoodsInfo goodsInfo = goodsInfoService.queryById(request);
            BigDecimal big1 = new BigDecimal(String.valueOf(goodsInfo.getGoods_price_off()));
            BigDecimal big2 = new BigDecimal(String.valueOf(count));
            double money = big1.multiply(big2).doubleValue();

            ShopCarItem shopCarItem = new ShopCarItem(goodsInfo, count, money);

            //临时购物车
            ShopCar shopCar = new ShopCar();
            shopCar.getShopCarItems().add(shopCarItem);
            shopCar.setAllCount(count);
            shopCar.setAllMoney(money);

            request.setAttribute("shopCar",shopCar);
            request.getSession().setAttribute("temporaryShopCar",shopCar);

            addressService.getAllAddress(request);
            request.getRequestDispatcher("pay.jsp").forward(request,response);
        }

    }

    public void pay(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException, ServletException {

        Timestamp date = orderService.add(request, response);

        orderDetailService.addOrderDetail(request,response,date);
        Order order = orderService.query(request, response, date);
        request.setAttribute("order",order);
        request.getRequestDispatcher("success.jsp").forward(request,response);
        shopService.delete(request);

    }

    public void getOrderList(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<OrderDetail> orderDetails = orderDetailService.query(request, response);
        request.setAttribute("orderDetails",orderDetails);
        orderService.getOrdersTypeList(request,response);
        request.getRequestDispatcher("back/order/orderlist.jsp").forward(request,response);
    }

    public void deleteOrder(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        orderDetailService.delete(request,response);
        orderService.delete(request,response);
        request.getRequestDispatcher("ShopController?action=getOrderList&curPage=1").forward(request,response);
    }
}
