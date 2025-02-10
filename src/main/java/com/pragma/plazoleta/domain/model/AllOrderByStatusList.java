package com.pragma.plazoleta.domain.model;

import java.util.List;

public class AllOrderByStatusList {

    List<Order> ordersList;
    int size;
    int page;
    long totalElements;
    int totalPage;

    public AllOrderByStatusList(List<Order> ordersList, int size, int page, long totalElements, int totalPage) {
        this.ordersList = ordersList;
        this.size = size;
        this.page = page;
        this.totalElements = totalElements;
        this.totalPage = totalPage;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
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
