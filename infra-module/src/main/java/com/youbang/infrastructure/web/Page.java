package com.youbang.infrastructure.web;

import java.awt.print.Pageable;

public class Page
{

	/**
	 * 页数,第一页开始
	 */
	protected int curPage;

	/**
	 * 每页显示多少条
	 */
	protected int pageSize;

	/**
	 * 记录总数
	 */
	private int totalRows;

	public Page()
	{
	}

	public Page(int curPage, int pageSize, int totalRows)
	{
		this.curPage = curPage;
		this.pageSize = pageSize;
		this.totalRows = totalRows;
	}

	public int getCurPage()
	{
		return curPage;
	}

	public void setCurPage(int curPage)
	{
		this.curPage = curPage;
	}

	public void setCurPage(int curPage, int pageSize)
	{
		this.curPage = (curPage - 1) * pageSize;
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public void setPageSize(int pageSize, int curPage)
	{
		this.pageSize = pageSize * curPage;
	}

	public int getTotalRows()
	{
		return totalRows;
	}

	public void setTotalRows(int totalRows)
	{
		this.totalRows = totalRows;
	}

}
