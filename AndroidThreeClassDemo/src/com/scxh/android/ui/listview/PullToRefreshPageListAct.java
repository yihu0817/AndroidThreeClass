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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.scxh.android.R;
import com.scxh.android.bean.PagerBean;
import com.scxh.android.util.Constances;
import com.scxh.android.util.HttpConnectionUtil;
import com.scxh.android.util.HttpConnectionUtil.HttpConnectionCallback;
import com.scxh.android.util.HttpConnectionUtil.HttpMethod;
import com.scxh.android.util.Logs;

public class PullToRefreshPageListAct extends ListActivity{
	// 浏览器测试地址:http://192.168.1.127:8080/app/pager?pageNo=1&pageSize=20
	private static String sBaseHttpUrl = Constances.BASE_URL + "/app/pager";
	private HttpConnectionUtil mHttpConnectionUtil;
	private int currentPage = 1;
	private int pageCount = 1; // 总页数
	private String pageSize = "20";// 页大小

	private LinearLayout mPagelayout;
	private MyBaseAdapter mMyBaseAdapter;
	private PullToRefreshListView mPullRefreshListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view_pullrefreshpagelist_layout);

		mPullRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_refresh_list);

		mPagelayout = (LinearLayout) findViewById(R.id.pagelayout);

		mHttpConnectionUtil = new HttpConnectionUtil();

		mMyBaseAdapter = new MyBaseAdapter(this);
		setListAdapter(mMyBaseAdapter);

		getListData(currentPage);

		//添加一个下拉刷新的监听
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
					@Override
					public void onRefresh(PullToRefreshBase<ListView> refreshView) {
						currentPage = 1;
						getListData(currentPage);
					}
				});
		
		//添加一个上拉到当前页结束的监听
		mPullRefreshListView.setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

			@Override
			public void onLastItemVisible() {
				mPagelayout.setVisibility(View.VISIBLE);  //从网络获取数据,显示加载进度条

				if (++currentPage > pageCount) { //判断是否到最后一页
					currentPage = pageCount;
					mPagelayout.setVisibility(View.GONE); //加载完全部记录，隐藏加载进度条
				} else {
					getListData(currentPage);
				}
			}
		});

	}

	/**
	 * 根据页号获取服务器数据
	 */
	public void getListData(int pageNo) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("pageNo", String.valueOf(pageNo));
		map.put("pageSize", pageSize);

		mHttpConnectionUtil.asyncConnect(sBaseHttpUrl, map, HttpMethod.GET,
				new HttpConnectionCallback() {

					@Override
					public void execute(String response) {
						
						mPagelayout.setVisibility(View.GONE);  //网络获取数据完成,隐藏加载进度条

						PagerBean pageBean = JSON.parseObject(response,PagerBean.class);

						List<String> list = pageBean.getListData();
						pageCount = pageBean.getPageCount();

						if (currentPage == 1) {
							mMyBaseAdapter.setListData(list);
						} else {
							mMyBaseAdapter.addListData(list);
						}
						
						// Call onRefreshComplete when the list has been refreshed.
						mPullRefreshListView.onRefreshComplete();
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
			if (listmap != null) {
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

			TextView messageTxt;
			if (convertView == null) {
				convertView = inflater.inflate(
						android.R.layout.simple_list_item_1, null);
				messageTxt = (TextView) convertView
						.findViewById(android.R.id.text1);
				convertView.setTag(messageTxt);
			} else {
				messageTxt = (TextView) convertView.getTag();
			}

			messageTxt.setText((String) getItem(position));

			return convertView;
		}
	}

}
