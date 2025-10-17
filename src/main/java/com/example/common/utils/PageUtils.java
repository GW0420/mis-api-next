package com.example.common.utils;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PageUtils {
    @NotNull(message = "页码不能为空")
    @Min(value = 1,message = "页码不能小于1")
    private int pageNum = 1;

    @NotNull(message = "每页条数不能为空")
    @Max(value = 100,message = "每页条数不能超过100条")
    private int pageSize = 10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

