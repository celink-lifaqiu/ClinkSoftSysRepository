package com.manager.service.web.admin.dto;

import java.util.ArrayList;
import java.util.List;

public class ResultDto {
	@SuppressWarnings("rawtypes")
	private List rows = new ArrayList(0);
	private int total;
	private boolean isPrePage;
	private boolean isNextPage;
	private int totalPage;
	private int currentPage;
	private int pageSize;

	public void initResultDto(int pageSize, int currentPage, int total) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.total = total;
		this.totalPage = total % pageSize == 0 ? total / pageSize : total
				/ pageSize + 1;
		this.isPrePage = currentPage > 1 && currentPage <= this.totalPage ? true
				: false;
		this.isNextPage = currentPage >= 1 && currentPage < this.totalPage ? true
				: false;

	}

	public <T extends SearchDto> void initReultDto(T searchDto, int total) {
		this.initResultDto(searchDto.getRows(), searchDto.getPage(), total);
	}

	public <T extends SearchDto> void initReultDto(T searchDto) {
		this
				.initResultDto(searchDto.getRows(), searchDto.getPage(),
						this.total);
	}

	public ResultDto() {
	}

	public ResultDto(int total) {
		this.total = total;
	}

	public ResultDto(@SuppressWarnings("rawtypes") List rows, int total) {
		this.rows = rows;
		this.total = total;
	}

	public ResultDto(@SuppressWarnings("rawtypes") List rows) {
		this.rows = rows;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	public void setRows(@SuppressWarnings("rawtypes") List rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public boolean isPrePage() {
		return isPrePage;
	}

	public void setPrePage(boolean isPrePage) {
		this.isPrePage = isPrePage;
	}

	public boolean isNextPage() {
		return isNextPage;
	}

	public void setNextPage(boolean isNextPage) {
		this.isNextPage = isNextPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
