package com.scxh.android.ui.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.scxh.android.R;
import com.scxh.android.bean.MessageBean;
import com.scxh.android.ui.TwoAcitity;
import com.scxh.android.util.Logs;

public class MyBaseAdapterActivity extends Activity {
	private final static int LIST_TYPE_LEFT = 0;
	private final static int LIST_TYPE_RIGHT = 1;
	private ListView mListView;
	private ProgressBar mProgressBar;
	private LinearLayout mLinearLayout;
	private MyBaseAdapter mMyBaseAdapter;
	public int mPostion;
	public boolean isFlag = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mybaseadapter_layout);

		mListView = (ListView) findViewById(R.id.mybaselist);
		mProgressBar = (ProgressBar) findViewById(R.id.myProgressBar);

		mMyBaseAdapter = new MyBaseAdapter(this);

		mListView.setAdapter(mMyBaseAdapter);

		mLinearLayout = (LinearLayout) findViewById(R.id.progressLayout);
		mListView.setEmptyView(mLinearLayout);

		List<MessageBean> mList = getData();
		mMyBaseAdapter.setData(mList);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if (!isFlag) {
					mPostion = position;
					mMyBaseAdapter.notifyDataSetChanged();
					isFlag = true;
				}

				Toast.makeText(MyBaseAdapterActivity.this,"响应 第一次选中项" + position, Toast.LENGTH_SHORT).show();
			}

		});
	}

	class MyBaseAdapter extends BaseAdapter {
		private List<MessageBean> mList = new ArrayList<MessageBean>();

		private Context mContext;
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(mContext);
		}

		public MyBaseAdapter(Context context, List<MessageBean> list) {
			mContext = context;
			mList = list;
			inflater = LayoutInflater.from(mContext);
		}

		public void setData(List<MessageBean> list) {
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
			return getLeftView(position, convertView, parent);

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
			MessageBean msg = (MessageBean) getItem(position);

			holder.imageView.setImageResource(msg.getHeaderIcon());
			holder.titleTxt.setText(msg.getTitle());
			holder.infoTxt.setText(msg.getContent());
			// ===========================================
			return convertView;
		}

		@Override
		public boolean isEnabled(int position) {
			if (isFlag == true) {
				if (mPostion == position)
					return true;
				else
					return false;
			} else {
				return true;
			}

		}

		class ViewHolder {
			ImageView imageView = null;
			TextView titleTxt = null;
			TextView infoTxt = null;
		}
	}

	private List<MessageBean> getData() {
		List<MessageBean> mList = new ArrayList<MessageBean>();

		MessageBean msg = new MessageBean();
		msg.setId(10);
		msg.setTitle("1粉彩&挑灯talking");
		msg.setContent("仅售79元，价值100元代金券，不限时段通用，免费WiFi，全场通用！");
		msg.setHeaderIcon(R.drawable.m1);
		msg.setType(LIST_TYPE_LEFT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("2黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);
		msg.setType(LIST_TYPE_LEFT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("3千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);
		msg.setType(LIST_TYPE_LEFT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("4黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);
		msg.setType(LIST_TYPE_RIGHT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("5千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);
		msg.setType(LIST_TYPE_RIGHT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("6黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);
		msg.setType(LIST_TYPE_RIGHT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("7千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);
		msg.setType(LIST_TYPE_LEFT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("8黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);
		msg.setType(LIST_TYPE_RIGHT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("9千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);
		msg.setType(LIST_TYPE_LEFT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("10黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);
		msg.setType(LIST_TYPE_RIGHT);
		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("11千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);
		msg.setType(LIST_TYPE_LEFT);
		mList.add(msg);

		return mList;
	}
}
