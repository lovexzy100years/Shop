package com.qf.service;

import com.qf.pojo.GoodsInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IGoodsInfoService {

    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public void delete(HttpServletRequest request,HttpServletResponse response);

    public void update(HttpServletRequest request,HttpServletResponse response) throws Exception;

    public void initModify(HttpServletRequest request,HttpServletResponse response);

    public void getGoodsInfoList(HttpServletRequest request);

    public void getAllGoodsInfo(HttpServletRequest request);

    public GoodsInfo queryById(HttpServletRequest request);
}
