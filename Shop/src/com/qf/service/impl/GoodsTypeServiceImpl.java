package com.qf.service.impl;

import com.qf.mapper.IGoodsTypeMapper;
import com.qf.mapper.impl.GoodsTypeMapperImpl;
import com.qf.pojo.GoodsType;
import com.qf.pojo.Page;
import com.qf.service.IGoodsTypeService;
import com.qf.utils.PageUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class GoodsTypeServiceImpl implements IGoodsTypeService {

    private IGoodsTypeMapper goodsTypeMapper=new GoodsTypeMapperImpl();
    @Override
    public void add(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        GoodsType goodsType = new GoodsType();
        BeanUtils.populate(goodsType,map);
        goodsTypeMapper.add(goodsType);
    }

    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        int num = Integer.parseInt(id);
        //num--解析出来的num

        //如果删除的是大类，先把对应的小类给删除;
        List<GoodsType> goodsTypes = goodsTypeMapper.queryChildGoodType(num);
        for (GoodsType goodsType : goodsTypes) {
            goodsTypeMapper.delete(goodsType.getId());
        }
        goodsTypeMapper.delete(num);
    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Map<String, String[]> map = request.getParameterMap();
        GoodsType goodsType = new GoodsType();
        BeanUtils.populate(goodsType,map);

        goodsTypeMapper.update(goodsType);

    }

    @Override
    public List<GoodsType> getParentGoodsType(HttpServletRequest request, HttpServletResponse response) {
        List<GoodsType> parentGoodsType = goodsTypeMapper.queryParentGoodsType();
        request.setAttribute("parentGoodsType",parentGoodsType);
        return parentGoodsType;
    }

    @Override
    public void getChildGoodsType(HttpServletRequest request, HttpServletResponse response) {
        String gtype_parentid = request.getParameter("gtype_parentid");
        List<GoodsType> childGoodsType = goodsTypeMapper.queryChildGoodType(Integer.parseInt(gtype_parentid));
        request.setAttribute("childGoodsType",childGoodsType);

    }
    @Override
    public List<GoodsType> getChildGoodsType(int gtype_parentid) {
        List<GoodsType> childGoodsType = goodsTypeMapper.queryChildGoodType(gtype_parentid);
        return childGoodsType;
    }

    @Override
    public void getGoodsType(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        GoodsType goodsType = goodsTypeMapper.query(Integer.parseInt(id));

        request.setAttribute("goodsType",goodsType);
    }

    @Override
    public void getGoodsTypeList(HttpServletRequest request, HttpServletResponse response) {
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int count = 8;
        int offset = (curPage-1)*count;
        int allCount = goodsTypeMapper.getAllcount("t_goods_type");
        int totalPage = PageUtil.getTotalPage(allCount,count);
        String url = "GoodsTypeController?action=getGoodsTypeList&curPage=";
        List<GoodsType> goodsTypes = goodsTypeMapper.query(offset, count);

        Page<GoodsType> page = new Page<>(count, curPage, totalPage,url, goodsTypes);
        request.setAttribute("page",page);
    }

    @Override
    public void getAllGoodsType(HttpServletRequest request, HttpServletResponse response) {
        List<GoodsType> goodsTypes = goodsTypeMapper.getAllGoodsType();
        request.setAttribute("goodsTypes",goodsTypes);

    }
}
