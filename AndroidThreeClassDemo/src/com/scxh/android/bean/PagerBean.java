package com.scxh.android.bean;

import java.util.List;

public class PagerBean {
	private int pageCount;
	private List<String> listData;

	public List<String> getListData() {
		return listData;
	}

	public void setListData(List<String> listData) {
		this.listData = listData;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
}
