package com.pragma.plazoleta.domain.model;

import java.util.List;


public class DishByCategoryList {

    List<Dish> dishList;
    int size;
    int page;
    long totalElements;
    int totalPage;

    public DishByCategoryList(List<Dish> dishList, int size, int page, long totalElements, int totalPage) {
        this.dishList = dishList;
        this.size = size;
        this.page = page;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}
