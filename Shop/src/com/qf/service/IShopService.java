package com.qf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IShopService {

    public void addShopCar(HttpServletRequest request);

    String update(HttpServletRequest request);

    public void delete(HttpServletRequest request);

    public void insert(HttpServletRequest request, HttpServletResponse response);
}
