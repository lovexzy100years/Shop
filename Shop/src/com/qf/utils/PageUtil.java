package com.qf.utils;

public class PageUtil {

    public static int getTotalPage(int allCount,int count){
        if(allCount % count == 0){
            return allCount/count;
        }
        return allCount/count + 1;
    }
}
