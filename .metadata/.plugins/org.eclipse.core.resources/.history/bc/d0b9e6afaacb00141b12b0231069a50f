package com.scxh.android.volley;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.VolleyErrorHelper;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.scxh.android.R;
import com.scxh.android.bean.MessageVolleyBean;
import com.squareup.picasso.Picasso;

public class VolleyCacheActivity extends Activity {
	private final static int LIST_TYPE_LEFT = 0;
	private final static int LIST_TYPE_RIGHT = 1;
	private ListView mListView;
	private ProgressBar mProgressBar;
	private LinearLayout mLinearLayout;
	private MyBaseAdapter mMyBaseAdapter;
	private SwipeRefreshLayout mSwipeRefreshLayout;
	private RequestQueue mRequestQueue;
	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {

			List<MessageVolleyBean> mList = (List<MessageVolleyBean>) msg.obj;
			mMyBaseAdapter.setData(mList);
			mSwipeRefreshLayout.setRefreshing(false); // 停止刷新
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_baseadapter_layout);

		mListView = (ListView) findViewById(R.id.mybaselist);
		mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swip);
		mProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);

		mSwipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_orange_dark,
				android.R.color.holo_green_dark, android.R.color.holo_red_dark);

		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				getData();
			}
		});

		mRequestQueue = Volley.newRequestQueue(this);

		mMyBaseAdapter = new MyBaseAdapter(this);

		mListView.setAdapter(mMyBaseAdapter);

		mLinearLayout = (LinearLayout) findViewById(R.id.progressLayout);
		mListView.setEmptyView(mLinearLayout);

		getData();

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				MyBaseAdapter adapter = (MyBaseAdapter) parent.getAdapter();
				MessageVolleyBean msg = (MessageVolleyBean) adapter.getItem(position);

				Toast.makeText(
						VolleyCacheActivity.this,
						" position  :" + position + " title :" + msg.getTitle(),
						Toast.LENGTH_SHORT).show();
			}

		});
	}

	class MyBaseAdapter extends BaseAdapter {
		private List<MessageVolleyBean> mList = new ArrayList<MessageVolleyBean>();

		private Context mContext;
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(mContext);
		}

		public MyBaseAdapter(Context context, List<MessageVolleyBean> list) {
			mContext = context;
			mList = list;
			inflater = LayoutInflater.from(mContext);
		}

		public void setData(List<MessageVolleyBean> list) {
			mList = list;
			notifyDataSetChanged();
		}

		/**
		 * 数据源记录条数,ListView项数
		 */
		@Override
		public int getCount() {
			return mList.size();
		}

		/**
		 * 获取数据源指定位置(position)的数据项对象
		 */
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		/**
		 * 获取指定位置ID
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.v("tag", "getView position  >>> :" + position
					+ " convertView  :" + convertView);
			if (getItemViewType(position) == LIST_TYPE_LEFT) {
				return getLeftView(position, convertView, parent);
			} else {
				return getRightView(position, convertView, parent);
			}

		}

		public View getRightView(int position, View convertView,
				ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				// ====一级优化==缓存View=======
				convertView = inflater.inflate(
						R.layout.item_baseadapter_right_view, parent, false);

				// ====二级优化===缓存View对象里的控件=====
				holder = new ViewHolder(); // 因为View setTag方法只能放一个Object,
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.iconimageView);
				holder.titleTxt = (TextView) convertView
						.findViewById(R.id.titletextView);
				holder.infoTxt = (TextView) convertView
						.findViewById(R.id.infotextView);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// ==========取数据项给View对应控件赋值=============
			MessageVolleyBean msg = (MessageVolleyBean) getItem(position);

			Picasso.with(mContext).load(msg.getHeaderIcon()).into(holder.imageView);
			
			holder.titleTxt.setText(msg.getTitle());
			holder.infoTxt.setText(msg.getContent());
			// ===========================================
			return convertView;
		}

		public View getLeftView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;

			if (convertView == null) {
				// ====一级优化==缓存View=======
				convertView = inflater.inflate(R.layout.item_baseadapter_view,
						parent, false);

				// ====二级优化===缓存View对象里的控件=====
				holder = new ViewHolder(); // 因为View setTag方法只能放一个Object,
				holder.imageView = (ImageView) convertView
						.findViewById(R.id.iconImg);
				holder.titleTxt = (TextView) convertView
						.findViewById(R.id.titletextView);
				holder.infoTxt = (TextView) convertView
						.findViewById(R.id.infotextView);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// ==========取数据项给View对应控件赋值=============
			MessageVolleyBean msg = (MessageVolleyBean) getItem(position);

			Picasso.with(mContext).load(msg.getHeaderIcon()).into(holder.imageView);
			holder.titleTxt.setText(msg.getTitle());
			holder.infoTxt.setText(msg.getContent());
			// ===========================================
			return convertView;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			MessageVolleyBean message = (MessageVolleyBean) getItem(position);

			return message.getType();
		}


		class ViewHolder {
			ImageView imageView = null;
			TextView titleTxt = null;
			TextView infoTxt = null;
		}
	}

	private void getData() {
		String url = "http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html";
		JsonObjectRequest jsonRequest = new JsonObjectRequest(url, null,
				new Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject response) {
						List<MessageVolleyBean> mList = new ArrayList<MessageVolleyBean>();
						try {
							JSONArray jsonArray = response.getJSONArray("T1348647909107");

							for (int i = 0; i < jsonArray.length(); i++) {

								JSONObject jsonObject = jsonArray.getJSONObject(i);
								String title = jsonObject.getString("title");
								String content = jsonObject.has("digest") ? jsonObject.getString("digest") : "";
								String headerIcon = jsonObject.has("imgsrc") ? jsonObject.getString("imgsrc") : "";

								MessageVolleyBean msg = new MessageVolleyBean();
								msg.setId(i);
								msg.setTitle(title);
								msg.setContent(content);
								msg.setHeaderIcon(headerIcon);
								msg.setType(LIST_TYPE_LEFT);
								
								mList.add(msg);
								
								Message msgs = new Message();
								msgs.obj = mList;
								mHandler.sendMessage(msgs);
							}

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}
				}, new ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
//						Toast.makeText(VolleyCacheActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
						Toast.makeText(VolleyCacheActivity.this, VolleyErrorHelper.getMessage(error, VolleyCacheActivity.this), Toast.LENGTH_SHORT).show();
					}
				});

		mRequestQueue.add(jsonRequest);
	}
}
