package com.city.erp.result;

import java.util.List;

//用于返回列表的结果对象
public class ListResultJson 
{
	private int total=0;
	private int pages=0;
	private List rows=null;
	private int page=0;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
