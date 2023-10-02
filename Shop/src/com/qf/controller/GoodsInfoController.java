package com.qf.controller;

import com.qf.pojo.GoodsType;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IGoodsTypeService;
import com.qf.service.impl.GoodsInfoServiceImpl;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/GoodsInfoController")
public class GoodsInfoController extends BaseServlet {

    private IGoodsInfoService goodsInfoService=new GoodsInfoServiceImpl();
    private IGoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();

    public void getGoodsInfoList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goodsInfoService.getGoodsInfoList(request);
        request.getRequestDispatcher("back/goods/goodsList.jsp").forward(request,response);
    }

    public void initAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<GoodsType> parentGoodsType = goodsTypeService.getParentGoodsType(request, response);
        List<GoodsType> childGoodsTypes = goodsTypeService.getChildGoodsType(parentGoodsType.get(0).getId());
        request.setAttribute("childGoodsTypes",childGoodsTypes);
        request.getRequestDispatcher("back/goods/goodsadd.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request,HttpServletResponse response) throws Exception {
        goodsInfoService.add(request,response);
        request.getRequestDispatcher("GoodsInfoController?action=getGoodsInfoList&curPage=1").forward(request,response);
    }

    public void initModify(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
       goodsInfoService.initModify(request,response);

        request.getRequestDispatcher("back/goods/goodsupdate.jsp").forward(request,response);
    }
    public void modify(HttpServletRequest request,HttpServletResponse response) throws Exception {
        goodsInfoService.update(request,response);
        response.sendRedirect("GoodsInfoController?action=getGoodsInfoList&curPage=1");
    }

    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        goodsInfoService.delete(request,response);
        response.sendRedirect("GoodsInfoController?action=getGoodsInfoList&curPage=1");
    }

    public void queryById(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        goodsInfoService.queryById(request);
        request.getRequestDispatcher("introduction.jsp").forward(request,response);
    }


}
