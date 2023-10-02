package com.qf.controller;

import com.qf.pojo.Address;
import com.qf.service.IAddressService;
import com.qf.service.impl.AddressServiceImpl;
import com.qf.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/AddressController")
public class AddressController extends BaseServlet {

    private IAddressService addressService=new AddressServiceImpl();

    public void addAddress(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {
        addressService.addAddress(request,response);
        request.getRequestDispatcher("ShopController?action=initShopCatPay").forward(request,response);
    }

    public void initModify(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        Address address = addressService.initModify(request, response);
        request.setAttribute("address",address);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }

    public void modify(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        addressService.modify(request, response);
        request.getRequestDispatcher("ShopController?action=initShopCatPay").forward(request,response);
    }

    public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        addressService.delete(request,response);
        request.getRequestDispatcher("ShopController?action=initShopCatPay").forward(request,response);
    }

}
