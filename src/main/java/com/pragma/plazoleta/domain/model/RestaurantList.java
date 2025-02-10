package com.pragma.plazoleta.domain.model;

import java.util.List;

public class RestaurantList {

    List<Restaurant> listRestaurants;
    int size;
    int page;
    long totalElements;
    int totalPage;

    public RestaurantList(List<Restaurant> listRestaurants, int size, int page, long totalElements, int totalPage) {
        this.listRestaurants = listRestaurants;
        this.size = size;
        this.page = page;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
    }

    public List<Restaurant> getListRestaurants() {
        return listRestaurants;
    }

    public void setListRestaurants(List<Restaurant> listRestaurants) {
        this.listRestaurants = listRestaurants;
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
