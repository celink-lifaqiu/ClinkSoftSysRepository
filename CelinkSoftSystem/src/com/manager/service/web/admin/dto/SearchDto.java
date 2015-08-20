package com.manager.service.web.admin.dto;

public class SearchDto {
	private int rows = 10;// 每页显示记录条数，默认显示十条数据
	private int page = 1;// 当前显示页数，默认显示第一页
	private String sort;// 排序字段
	private String order = "asc";// 排序方式，升序或者降序

	public SearchDto() {
	}

	public SearchDto(int rows, int page, String sort, String order) {
		this.rows = rows;
		this.page = page;
		this.sort = sort;
		this.order = order;
	}

	public String toString() {
		return rows + " " + page + " " + sort + " " + order;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
