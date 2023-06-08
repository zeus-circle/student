package com.student.util;

import java.util.ArrayList;

import com.google.gson.Gson;
/**
 * @Author 瑾瑜风禾
 * @Date 2023/6/2 20:31
 */
public class InfoHandler<T> {
    //    保存数据
    private ArrayList<T> list = new ArrayList<>();

    private static Gson gson = new Gson();
    public InfoHandler(){

    }

    public ArrayList<T> getList() {
        return list;
    }

    public void add(T t) {
        list.add(t);
    }
    public String getJSON(){
        return gson.toJson(list);
    }

    public static String toJSON(Object obj){
        return gson.toJson(obj);
    }
}
