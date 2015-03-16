package com.scxh.android.ui.listview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.scxh.android.R;
import com.scxh.android.bean.PagerBean;
import com.scxh.android.util.Constances;
import com.scxh.android.util.HttpConnectionUtil;
import com.scxh.android.util.HttpConnectionUtil.HttpConnectionCallback;
import com.scxh.android.util.HttpConnectionUtil.HttpMethod;
import com.scxh.android.util.Logs;

public class PageListAct extends ListActivity implements OnScrollListener {
	//浏览器测试地址:http://192.168.1.127:8080/app/pager?pageNo=1&pageSize=20
	private static String sBaseHttpUrl = Constances.BASE_URL+"/app/pager";
	private HttpConnectionUtil mHttpConnectionUtil;
	private int currentPage = 1;
	private int pageCount = 1; // 总页数
	private String pageSize = "20";//页大小
	private boolean isLastRow;  //判断是否到最后一行

	private LinearLayout mPagelayout;
	private MyBaseAdapter mMyBaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_pagelist1_layout);

		mPagelayout = (LinearLayout) findViewById(R.id.pagelayout);

		mHttpConnectionUtil = new HttpConnectionUtil();

		mMyBaseAdapter = new MyBaseAdapter(this);
		setListAdapter(mMyBaseAdapter);
		
		getListData(currentPage);

		getListView().setOnScrollListener(this);

	}

	/**
	 * 获取服务器数据
	 */
	public void getListData(int pageNo) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("pageNo", String.valueOf(pageNo));
		map.put("pageSize", pageSize);

		mHttpConnectionUtil.asyncConnect(sBaseHttpUrl, map, HttpMethod.GET,
				new HttpConnectionCallback() {

			@Override
			public void execute(String response) {
				
				mPagelayout.setVisibility(View.GONE);

				PagerBean pageBean = JSON.parseObject(response, PagerBean.class);
				
				List<String> list = pageBean.getListData();
				pageCount = pageBean.getPageCount();
				
				if(currentPage == 1){
					mMyBaseAdapter.setListData(list);
				}else{
					mMyBaseAdapter.addListData(list);
				}
			}
		});

	}

	public class MyBaseAdapter extends BaseAdapter {
		private List<String> list = new ArrayList<String>();
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			inflater = LayoutInflater.from(context);
		}

		public void setListData(List<String> listmap) {
			list = listmap;
			notifyDataSetChanged();
		}
		
		public void addListData(List<String> listmap) {
			if(listmap != null){
				list.addAll(listmap);
			}
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
//			Logs.v("getView :" + position);
			
			TextView messageTxt;
			if(convertView == null){
				convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
				messageTxt = (TextView) convertView.findViewById(android.R.id.text1);
				convertView.setTag(messageTxt);
			}else{
				messageTxt = (TextView)convertView.getTag();
			}
			
			messageTxt.setText((String)getItem(position));
			
			return convertView;
		}
	}

	/**
	 * 滚动时一直回调，直到停止滚动时才停止回调。单击时回调一次。 
	 * firstVisibleItem：当前能看见的第一个列表项ID（从0开始）
	 * visibleItemCount：当前能看见的列表项个数（小半个也算） 
	 * totalItemCount：列表项共数
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
//		Logs.v("onScroll >>> firstVisibleItem :" + firstVisibleItem
//				+ "visibleItemCount" + visibleItemCount + " totalItemCount :"
//				+ totalItemCount);
		// 判断是否滚到最后一行
		if (firstVisibleItem + visibleItemCount == totalItemCount
				&& totalItemCount > 0) {
			isLastRow = true;
		}
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		Logs.v("onScrollStateChanged" + scrollState);
		// 当滚到最后一行且停止滚动时，执行加载
		if (isLastRow
				&& scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
			// 加载元素
			isLastRow = false;

			mPagelayout.setVisibility(View.VISIBLE);
			
			if (++currentPage > pageCount) {  //是否加载完全部记录
				currentPage = pageCount;
				mPagelayout.setVisibility(View.GONE);
			}else{
				getListData(currentPage);
			}

			
		}
	}
}
