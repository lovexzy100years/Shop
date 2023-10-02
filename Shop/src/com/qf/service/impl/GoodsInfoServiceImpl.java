package com.qf.service.impl;

import com.qf.mapper.IGoodsInfoMapper;
import com.qf.mapper.impl.GoodsInfoMapperImpl;
import com.qf.pojo.GoodsInfo;
import com.qf.pojo.GoodsType;
import com.qf.pojo.Page;
import com.qf.service.IGoodsInfoService;
import com.qf.service.IGoodsTypeService;
import com.qf.utils.PageUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class GoodsInfoServiceImpl implements IGoodsInfoService {

    private IGoodsInfoMapper goodsInfoMapper=new GoodsInfoMapperImpl();
    @Override
    public void add(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GoodsInfo goodsInfo=createGoodsInfo(request);
        System.out.println(goodsInfo);
        goodsInfoMapper.add(goodsInfo);
    }

    public GoodsInfo createGoodsInfo(HttpServletRequest request) throws Exception {

        //获取文件上传工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //获取文件上传类
        ServletFileUpload upload = new ServletFileUpload(factory);

        //解析请求
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        List<FileItem> list = upload.parseRequest(request);
        for (FileItem fileItem : list) {

            if(fileItem.isFormField()){//文本数据

                String fieldName = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8");
                map.put(fieldName,value);

            }else{//二进制数据

                //文件路径
                String path = request.getServletContext().getRealPath("images");
                //文件名
                String fileName = fileItem.getName();

                if(fileName != null && !fileName.trim().equals("")){
                    //拼接文件名
                    path = path + File.separator + fileName;
                    //将商品的图片下载到本地服务器
                    fileItem.write(new File(path));

                    map.put("goods_pic",fileItem.getName());
                }
            }
        }

        GoodsInfo goodsInfo = new GoodsInfo();
        BeanUtils.populate(goodsInfo,map);

        return goodsInfo;
    }
    @Override
    public void delete(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        goodsInfoMapper.delete(Integer.parseInt(id));
    }

    @Override
    public void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        GoodsInfo goodsInfo=createGoodsInfo(request);
        goodsInfoMapper.update(goodsInfo);
    }

    private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

    @Override
    public void initModify(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        GoodsInfo goodsInfo = goodsInfoMapper.query(Integer.parseInt(id));
        request.setAttribute("goodsInfo",goodsInfo);

        goodsTypeService.getParentGoodsType(request,response);
        List<GoodsType> childGoodsTypes = goodsTypeService.getChildGoodsType(goodsInfo.getGoods_parentid());
        request.setAttribute("childGoodsTypes",childGoodsTypes);
    }

    @Override
    public void getGoodsInfoList(HttpServletRequest request) {
        int curPage = Integer.parseInt(request.getParameter("curPage"));
        int count = 8;
        int offset = (curPage-1)*count;
        int allCount = goodsInfoMapper.getAllCount();
        int totalPage = PageUtil.getTotalPage(allCount,count);
        String url = "GoodsInfoController?action=getGoodsInfoList&curPage=";
        List<GoodsInfo> users = goodsInfoMapper.query(offset,count);

        Page<GoodsInfo> page = new Page<>(count, curPage, totalPage,url, users);
        request.setAttribute("page",page);
    }

    @Override
    public void getAllGoodsInfo(HttpServletRequest request) {
        List<GoodsInfo> goodsInfos = goodsInfoMapper.getAllGoodsInfo();
        request.setAttribute("goodsInfos",goodsInfos);
    }

    @Override
    public GoodsInfo queryById(HttpServletRequest request) {
        String id = request.getParameter("id");
        GoodsInfo goodsInfo = goodsInfoMapper.query(Integer.parseInt(id));
        request.setAttribute("goodsInfo",goodsInfo);
        return goodsInfo;
    }
}
