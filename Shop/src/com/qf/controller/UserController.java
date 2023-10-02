package com.qf.controller;

import com.qf.service.IUserService;
import com.qf.service.impl.UserServiceImpl;
import com.qf.servlet.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/UserController")
public class UserController extends BaseServlet {

    private IUserService userService = new UserServiceImpl();

    public void getUserList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        userService.getUserList(request,response);
        request.getRequestDispatcher("back/user/userinfo.jsp").forward(request,response);

    }

    public void add(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        userService.register(request,response);
        response.sendRedirect("UserController?action=getUserList&curPage=1");
    }

    public void register(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException, InvocationTargetException, IllegalAccessException {
        boolean register = userService.register(request, response);
        if(register){
            response.sendRedirect("back/main.jsp");
        }else{
            request.getRequestDispatcher("back/main.jsp").forward(request,response);
        }
    }

    //删除功能
    public void delete(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        userService.delete(request,response);
        request.getRequestDispatcher("UserController?action=getUserList&curPage=1").forward(request,response);

    }
    //初始化修改功能
    public void initModify(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        userService.initModify(request);
        request.getRequestDispatcher("back/user/updateuser.jsp").forward(request,response);
    }

    //
    public void modify(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        userService.modify(request,response);

        response.sendRedirect("UserController?action=getUserList&curPage=1");
    }

    public void backLogin(HttpServletRequest request,HttpServletResponse response) throws IOException, InvocationTargetException, IllegalAccessException {
        boolean bool = userService.backLogin(request, response);
        if(bool){
            response.sendRedirect("back/main.jsp");
        }else{
            response.sendRedirect("backLogin.jsp");
        }
    }

    public void outLogin(HttpServletRequest request,HttpServletResponse response) throws IOException {
        userService.outLogin(request,response);
        response.sendRedirect("backLogin.jsp");
    }

    public void login(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        boolean bool = userService.login(request, response);
        if(bool){
            response.sendRedirect("home.jsp");
        }else{
            response.sendRedirect("login.jsp");
        }
    }
}
