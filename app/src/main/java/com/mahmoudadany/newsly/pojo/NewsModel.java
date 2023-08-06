package com.mahmoudadany.newsly.pojo;

import java.util.List;

public class NewsModel {
    private PaginationModel pagination;
    private List  <Data> data;

    public PaginationModel getPagination() {
        return pagination;
    }

    public void setPagination(PaginationModel pagination) {
        this.pagination = pagination;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
