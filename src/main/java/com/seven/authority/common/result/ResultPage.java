package com.seven.authority.common.result;

import com.github.pagehelper.PageInfo;
import com.seven.authority.common.enums.StatusCodeEnums;

import java.util.List;

/**
 * @author seven
 */
public class ResultPage<T> extends AbstractResult {
    /**
     * 列表数据
     */
    private List<T> dataList;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Integer totalNumber;
    /**
     * 当前页
     */
    private Integer pageIndex;
    /**
     * 当前页条数
     */
    private Integer pageSize;

    public ResultPage(StatusCodeEnums statusCodeEnums) {
        super(statusCodeEnums);
    }

    public ResultPage(String code, String message) {
        super(code, message);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultPage<T> error() {
        return new ResultPage(StatusCodeEnums.SYSTEM_ERROR);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultPage<T> error(StatusCodeEnums statusCodeEnums) {
        return new ResultPage(statusCodeEnums);
    }

    @SuppressWarnings(value = "unchecked")
    public static <T> ResultPage<T> list(PageInfo<T> datas) {
        ResultPage<T> res = new ResultPage(StatusCodeEnums.SUCCESS);
        res.dataList = datas.getList();
        res.totalPage = datas.getPages();
        res.totalNumber = (int) datas.getTotal();
        res.pageIndex = datas.getPageNum();
        res.pageSize = datas.getSize();
        return res;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
