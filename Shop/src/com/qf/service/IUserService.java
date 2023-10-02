package com.qf.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;

public interface IUserService {

    public boolean register(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public boolean login(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public boolean backLogin(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public boolean update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void delete(HttpServletRequest request,HttpServletResponse response);

    public void getUserList(HttpServletRequest request, HttpServletResponse response);

    public void initModify(HttpServletRequest request);

    public boolean modify(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException;

    public void outLogin(HttpServletRequest request,HttpServletResponse response);

    public void rememberMe(HttpServletRequest request,HttpServletResponse response,String username,String password);
}
