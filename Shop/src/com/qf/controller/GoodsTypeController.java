package com.qf.controller;

import com.alibaba.fastjson.JSON;
import com.qf.pojo.GoodsType;
import com.qf.service.IGoodsTypeService;
import com.qf.service.impl.GoodsTypeServiceImpl;
import com.qf.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@WebServlet("/GoodsTypeController")
public class GoodsTypeController extends BaseServlet {

    private IGoodsTypeService goodsTypeService=new GoodsTypeServiceImpl();

    public void getGoodsTypeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        goodsTypeService.getGoodsTypeList(request,response);
        request.getRequestDispatcher("back/goodstype/goodstype.jsp").forward(request,response);
    }

    public void initAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        goodsTypeService.getParentGoodsType(request,response);
        request.getRequestDispatcher("back/goodstype/goodsadd.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, ServletException, IOException {
        goodsTypeService.add(request,response);
        request.getRequestDispatcher("GoodsTypeController?action=getGoodsTypeList&curPage=1").forward(request,response);
    }

    public void initModify(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        goodsTypeService.getGoodsType(request,response);
        goodsTypeService.getParentGoodsType(request,response);
        request.getRequestDispatcher("back/goodstype/goodstypeupdate.jsp").forward(request,response);
    }

    public void modify(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        goodsTypeService.update(request,response);
        response.sendRedirect("GoodsTypeController?action=getGoodsTypeList&curPage=1");
    }

    public void delete(HttpServletRequest request,HttpServletResponse response) throws IOException {
        goodsTypeService.delete(request,response);
        response.sendRedirect("GoodsTypeController?action=getGoodsTypeList&curPage=1");
    }
    public void getChildGoodsType(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String gtype_parentid = request.getParameter("gtype_parentid");
        List<GoodsType> childGoodsTypes = goodsTypeService.getChildGoodsType(Integer.parseInt(gtype_parentid));
        String jsonStr= JSON.toJSONString(childGoodsTypes);
        response.getWriter().write(jsonStr);
    }

}
