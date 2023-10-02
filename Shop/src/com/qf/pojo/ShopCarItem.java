package com.qf.pojo;

//购物车项
public class ShopCarItem {

    private GoodsInfo goodsInfo;
    private int count;
    private double money;

    public ShopCarItem() {
    }

    public ShopCarItem(GoodsInfo goodsInfo, int count, double money) {
        this.goodsInfo = goodsInfo;
        this.count = count;
        this.money = money;
    }

    public GoodsInfo getGoodsInfo() {
        return goodsInfo;
    }

    public void setGoodsInfo(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "ShopCarItem{" +
                "goodsInfo=" + goodsInfo +
                ", count=" + count +
                ", money=" + money +
                '}';
    }
}
