package com.elefirst.base.entity;

/**
 * Created by VM on 2/24/2017.
 */
import java.util.List;

/**
 * 分页封装类
 * 用于做分页查询的基础类，封装了一些分页的相关属性
 * @author 闫洲
 * @version v1.0
 * @param <T>
 */
public class PageResults<T> {

    // 下一页
    private int page;

    // 当前页
    private int currentPage;

    // 每页个个数
    private int rows;

    // 总条数
    private int totalCount;

    // 总页数
    private int pageCount;

    // 记录
    private List<T> results;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPage() {
        if (page <= 0) {
            return 1;
        } else{
            return page > pageCount ? pageCount : page;
        }
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows <= 0 ? 10 : rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void resetPage() {
        page = currentPage + 1;
        pageCount = totalCount % rows == 0 ? totalCount / rows
                : totalCount / rows + 1;
    }

}
