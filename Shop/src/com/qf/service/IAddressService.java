package com.qf.service;

import com.qf.pojo.Address;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface IAddressService {

    public void addAddress(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void getAllAddress(HttpServletRequest request);

    public Address initModify(HttpServletRequest request,HttpServletResponse response);

    public void modify(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void delete(HttpServletRequest request,HttpServletResponse response);
}
