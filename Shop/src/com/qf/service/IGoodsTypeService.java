package com.qf.service;

import com.qf.pojo.GoodsType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface IGoodsTypeService {

    public void add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void delete(HttpServletRequest request,HttpServletResponse response);

    public void update(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public List<GoodsType> getParentGoodsType(HttpServletRequest request,HttpServletResponse response);

    public void getChildGoodsType(HttpServletRequest request,HttpServletResponse response);

    public List<GoodsType> getChildGoodsType(int gtype_parentid);

    public void getGoodsType(HttpServletRequest request,HttpServletResponse response);

    public void getGoodsTypeList(HttpServletRequest request,HttpServletResponse response);


    public  void getAllGoodsType(HttpServletRequest request, HttpServletResponse response);
}
