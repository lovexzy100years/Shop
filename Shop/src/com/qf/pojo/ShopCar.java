package com.qf.pojo;

import java.util.ArrayList;
import java.util.List;

public class ShopCar {

    private List<ShopCarItem> shopCarItems;
    private int allCount;
    private double allMoney;

    public ShopCar() {
         shopCarItems = new ArrayList<>();

    }

    public ShopCar(int allCount, double allMoney) {
        this.allCount = allCount;
        this.allMoney = allMoney;
        shopCarItems=new ArrayList<>();
    }

    public List<ShopCarItem> getShopCarItems() {
        return shopCarItems;
    }

    public void setShopCarItems(List<ShopCarItem> shopCarItems) {
        this.shopCarItems = shopCarItems;
    }

    public int getAllCount() {
        return allCount;
    }

    public void setAllCount(int allCount) {
        this.allCount = allCount;
    }

    public double getAllMoney() {
        return allMoney;
    }

    public void setAllMoney(double allMoney) {
        this.allMoney = allMoney;
    }

    @Override
    public String toString() {
        return "ShopCar{" +
                "shopCarItems=" + shopCarItems +
                ", allCount=" + allCount +
                ", allMoney=" + allMoney +
                '}';
    }
}
