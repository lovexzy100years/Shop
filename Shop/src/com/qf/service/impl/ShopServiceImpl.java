package com.qf.service.impl;

import com.qf.pojo.ShopCar;
import com.qf.pojo.ShopCarItem;
import com.qf.service.IShopService;
import com.qf.utils.ShopCarUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ShopServiceImpl implements IShopService {
    @Override
    public void addShopCar(HttpServletRequest request) {
        String id = request.getParameter("id");
        String count = request.getParameter("count");
        ShopCarUtil.add(request,Integer.parseInt(id),Integer.parseInt(count));
    }

    @Override
    public String update(HttpServletRequest request) {
        String id = request.getParameter("id");
        String count = request.getParameter("count");

        System.out.println(id);
        System.out.println(count);
        ShopCarUtil.update(request,Integer.parseInt(id),Integer.parseInt(count));
        ShopCarItem shopCarItem=ShopCarUtil.getShopCarItem(request,Integer.parseInt(id));
        ShopCar shopCar = ShopCarUtil.getShopCar(request);

        String jsonStr="{money:"+shopCarItem.getMoney()+",allCount:"+shopCar.getAllCount()+",allMoney:"+shopCar.getAllMoney()+"}";
        return jsonStr;

    }

    @Override
    public void delete(HttpServletRequest request) {
        String id = request.getParameter("id");
        ShopCarUtil.delete(request,Integer.parseInt(id));
    }

    @Override
    public void insert(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String[]> map = request.getParameterMap();
    }
}
