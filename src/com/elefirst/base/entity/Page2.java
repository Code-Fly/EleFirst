package com.elefirst.base.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 123 on 2016/4/20.
 */
public class Page2<T> {
    private int rows;
    private int total;
    private List<T> list;

    public Page2(List<T> list, int rows) {
        this.list = list;
        this.total = list.size();
        this.rows = rows;
    }

    public List<T> getPages(int page) {
        List<T> newList = new ArrayList<>();
        for (int i = ((page - 1) * rows); i < (page * rows); i++) {
            if (i >= total) {
                break;
            }
            newList.add(list.get(i));
        }
        return newList;
    }


}
