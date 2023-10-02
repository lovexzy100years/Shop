package com.qf.service.impl;

import com.qf.mapper.IUserMapper;
import com.qf.mapper.impl.UserMapperImpl;
import com.qf.pojo.Page;
import com.qf.pojo.User;
import com.qf.service.IUserService;
import com.qf.utils.CookieUtil;
import com.qf.utils.PageUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements IUserService {

    private IUserMapper userMapper = new UserMapperImpl();

    @Override
    public boolean register(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);

        if(userMapper.query(user.getUsername()) != null){
            return false;
        }
        userMapper.add(user);
        return true;
    }

    public boolean login(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);

        user = userMapper.query(user.getUsername(), user.getPassword());
        if( user== null){
            return false;
        }
       request.getSession().setAttribute("username",user.getUsername());
        request.getSession().setAttribute("userId",String.valueOf(user.getId()));
        return true;
    }



    @Override
    public boolean backLogin(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {

        //通过前台表单中的name值进行获取的，获取到后又进行了一次封装。 之所以返回的map中的value为字符串类型的数组，是为了解决表单中有多个name值一样的项。
        //返回的是一个Map类型的值，该返回值记录着前端（如jsp页面）所提交请求中的请求参数和请求参数值的映射关系。这个返回值有个特别之处——只能读
        //Map(key,value[]),即:key是String型，value是String型数组。
        Map<String, String[]> map = request.getParameterMap();


        User user = new User();
        //BeanUtils.populate(Object bean, Map properties)
        //这个方法会遍历map<key, value>中的key,如果bean中有这个属性，就把这个key对应的value值赋给bean的属性
        BeanUtils.populate(user,map);

        User query = userMapper.query(user.getUsername(), user.getPassword());
        if(query == null || query.getIs_admin().equals("否")){
            return false;
        }
        return true;

    }

    @Override
    public boolean update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {

        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);

        userMapper.update(user);
        return true;
    }

    @Override
    public void delete(HttpServletRequest request,HttpServletResponse response) {
        String id = request.getParameter("id");
        userMapper.delete(Integer.parseInt(id));
    }

    @Override
    public void getUserList(HttpServletRequest request, HttpServletResponse response) {

        int curPage = Integer.parseInt(request.getParameter("curPage"));
        //数据条数
        int count = 8;
        //计算偏移量=(当前页数-1)*数据条数
        //0-8;8-8;16-8
        int offset = (curPage-1)*count;
        int allCount = userMapper.getAllCount();
        int totalPage = PageUtil.getTotalPage(allCount,count);
        String url = "UserController?action=getUserList&curPage=";
        List<User> users = userMapper.query(offset, count);

        Page<User> page = new Page<>(count, curPage, totalPage,url, users);
        request.setAttribute("page",page);
    }

    @Override
    public void initModify(HttpServletRequest request) {
        User user = userMapper.query(request.getParameter("username"));
        request.setAttribute("user",user);
    }

    @Override
    public boolean modify(HttpServletRequest request,HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        BeanUtils.populate(user,map);

        userMapper.update(user);
        return true;

    }

    @Override
    public void outLogin(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("password");

        response.addCookie(CookieUtil.createCookie("username","",0));
        response.addCookie(CookieUtil.createCookie("password","",0));
    }
    @Override
    public void rememberMe(HttpServletRequest request, HttpServletResponse response,String username,String password) {
        response.addCookie(CookieUtil.createCookie("username",username,60*60*24*10));
        response.addCookie(CookieUtil.createCookie("password",password,60*60*24*10));
    }

}
