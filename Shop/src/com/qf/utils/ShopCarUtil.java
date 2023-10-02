package com.qf.utils;

import com.qf.mapper.IGoodsInfoMapper;
import com.qf.mapper.impl.GoodsInfoMapperImpl;
import com.qf.pojo.GoodsInfo;
import com.qf.pojo.ShopCar;
import com.qf.pojo.ShopCarItem;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class ShopCarUtil {

    private static IGoodsInfoMapper goodsInfoMapper=new GoodsInfoMapperImpl();

    public static ShopCar getShopCar(HttpServletRequest request){
        ShopCar shopCar = (ShopCar) request.getAttribute("shopCar");
        if(shopCar != null){
            return shopCar;
        }
        shopCar= (ShopCar) request.getSession().getAttribute("temporaryShopCar");
        if(shopCar!=null){
            return shopCar;
        }

        shopCar=(ShopCar) request.getSession().getAttribute("shopCar");

        if(shopCar == null){
            shopCar=new ShopCar();
            request.getSession().setAttribute("shopCar",shopCar);
        }
        return shopCar;

    }

    public static ShopCarItem getShopCarItem(HttpServletRequest request,int id){
        ShopCar shopCar = getShopCar(request);
        List<ShopCarItem> shopCarItems = shopCar.getShopCarItems();
        for (ShopCarItem shopCarItem : shopCarItems) {
            if(shopCarItem.getGoodsInfo().getId()==id){
                return shopCarItem;
            }
        }
        return null;
    }

    public static void add(HttpServletRequest request,int id,int count){
        ShopCar shopCar = getShopCar(request);
        ShopCarItem shopCarItem = getShopCarItem(request, id);
        if(shopCarItem==null){//说明该商品没有在购物车里
            GoodsInfo goodsInfo = goodsInfoMapper.query(id);
            double money = calculateShopCarItemMoney(goodsInfo.getGoods_price_off(), count);
            shopCarItem  = new ShopCarItem(goodsInfo, count, money);
            shopCar.getShopCarItems().add(shopCarItem);
        }else{
            GoodsInfo goodsInfo = shopCarItem.getGoodsInfo();
            int shopCarItemCount = shopCarItem.getCount();
            count=shopCarItemCount+count;
            if(shopCarItemCount+count>goodsInfo.getGoods_stock()){//新增个数+原个数>商品的库存
                count=goodsInfo.getGoods_stock();
            }
            shopCarItem.setCount(count);

            double money=calculateShopCarItemMoney(goodsInfo.getGoods_price_off(),count);

            shopCarItem.setMoney(money);
        }
        replaceShopCar(request);
    }

    //加法运算
    private static double calculateShopCarItemMoney(double goods_price_off,int count){
        BigDecimal big1 = new BigDecimal(String.valueOf(count));
        BigDecimal big2 = new BigDecimal(String.valueOf(goods_price_off));
        BigDecimal result = big1.multiply(big2);
        double money=result.doubleValue();
        return money;
    }

    //更新购物车里的数据(总个数和总金额)
    private static void replaceShopCar(HttpServletRequest request){

        ShopCar shopCar = getShopCar(request);

        int allCount=0;
        double allMoney=0;
        List<ShopCarItem> shopCarItems = shopCar.getShopCarItems();
        for (ShopCarItem shopCarItem : shopCarItems) {
            int count = shopCarItem.getCount();
            allCount+=count;

            BigDecimal big1 = new BigDecimal(String.valueOf(shopCarItem.getMoney()));
            BigDecimal big2 = new BigDecimal(String.valueOf(allMoney));
            BigDecimal result = big1.add(big2);
            allMoney=result.doubleValue();
        }
        shopCar.setAllCount(allCount);
        shopCar.setAllMoney(allMoney);
    }
    public static void delete(HttpServletRequest request,int id){
        ShopCar shopCar = getShopCar(request);
        List<ShopCarItem> shopCarItems = shopCar.getShopCarItems();
        Iterator<ShopCarItem> it = shopCarItems.iterator();
        while (it.hasNext()){
            ShopCarItem shopCarItem = it.next();
            if(shopCarItem.getGoodsInfo().getId()==id){
                it.remove();
                break;
            }
        }
        replaceShopCar(request);
    }

    //对购物车里数据的增/减
    public static void update(HttpServletRequest request,int id,int count){

        ShopCarItem shopCarItem = getShopCarItem(request, id);
        shopCarItem.setCount(count);
        double money = calculateShopCarItemMoney(shopCarItem.getGoodsInfo().getGoods_price_off(), count);
        shopCarItem.setMoney(money);
        replaceShopCar(request);
    }
}
