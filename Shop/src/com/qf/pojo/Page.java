package com.qf.pojo;

import java.util.List;

public class Page<T> {

    private int count;
    private int curPage;
    private int totalPage;
    private String url;
    private List<T> list;


    public Page() {
    }

    public Page(int count, int curPage, int totalPage, String url, List<T> list) {
        this.count = count;
        this.curPage = curPage;
        this.totalPage = totalPage;
        this.url = url;
        this.list = list;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Page{" +
                "count=" + count +
                ", curPage=" + curPage +
                ", totalPage=" + totalPage +
                ", url='" + url + '\'' +
                ", list=" + list +
                '}';
    }
}
