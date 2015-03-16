package com.scxh.android.ui.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.bean.MessageBean;
import com.scxh.android.ui.custom.TitleView;
import com.scxh.android.util.Logs;
import com.yalantis.pulltorefresh.library.PullToRefreshView;

public class PullToRefreshActivity extends Activity {
	private TitleView mTitleView;
    public static final int REFRESH_DELAY = 2000;

    private PullToRefreshView mPullToRefreshView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_pull_to_refresh);
        mTitleView = (TitleView) findViewById(R.id.title_view);
        ListView listView = (ListView) findViewById(R.id.list_view);
        
        MyBaseAdapter adapter = new MyBaseAdapter(this);
        adapter.setData(getData());
        listView.setAdapter(adapter);
        
        mTitleView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.left_imagebtn:
					PullToRefreshActivity.this.finish();
					break;
				case R.id.right_imagebtn:
					Toast.makeText(PullToRefreshActivity.this, "设置", Toast.LENGTH_SHORT).show();
					break;
				}
				
			}
		});
        
        mPullToRefreshView = (PullToRefreshView) findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	Logs.v("111111111111111");
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, REFRESH_DELAY);
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
		
		public void setData(List<MessageBean> list){
			mList = list;
			notifyDataSetChanged();
		}
		/**
		 * 鏁版嵁婧愯褰曟潯鏁�,ListView椤规暟
		 */
		@Override
		public int getCount() {
			return mList.size();
		}
		/**
		 * 鑾峰彇鏁版嵁婧愭寚瀹氫綅缃�(position)鐨勬暟鎹」瀵硅薄
		 */
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		/**
		 * 鑾峰彇鎸囧畾浣嶇疆ID
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.v("tag", "getView position  >>> :"+position+ " convertView  :"+convertView);
			
			ViewHolder holder;
			
			if(convertView == null){
				//====涓�绾т紭鍖�==缂撳瓨View=======
				convertView = inflater.inflate(R.layout.item_baseadapter_view, parent,false);
				
				//====浜岀骇浼樺寲===缂撳瓨View瀵硅薄閲岀殑鎺т欢=====
				holder = new ViewHolder();  //鍥犱负View setTag鏂规硶鍙兘鏀句竴涓狾bject,
				holder.imageView = (ImageView) convertView.findViewById(R.id.iconImg);
				holder.titleTxt = (TextView) convertView.findViewById(R.id.titletextView);
				holder.infoTxt = (TextView) convertView.findViewById(R.id.infotextView);
				
				convertView.setTag(holder);

			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			//==========鍙栨暟鎹」缁橵iew瀵瑰簲鎺т欢璧嬪��=============
			MessageBean msg = (MessageBean) getItem(position);

			holder.imageView.setImageResource(msg.getHeaderIcon());
			holder.titleTxt.setText(msg.getTitle());
			holder.infoTxt.setText(msg.getContent());
            //===========================================
			return convertView;
		}
		
		class ViewHolder{
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

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("2黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("3千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("4黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("5千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);

		mList.add(msg);
		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("6黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("7千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);

		mList.add(msg);
		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("8黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("9千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);

		mList.add(msg);
		msg = new MessageBean();
		msg.setId(11);
		msg.setTitle("10黄记煌三汁焖锅");
		msg.setContent("因过年商户运营调整，接待门店有所改变，详情请看团购规则，如有疑问，请致电客服咨询。不便之处，深表歉意！");
		msg.setHeaderIcon(R.drawable.m2);

		mList.add(msg);

		msg = new MessageBean();
		msg.setId(12);
		msg.setTitle("11千味涮");
		msg.setContent("[多商区] 现金券，2城5店同庆重庆万象城店开");
		msg.setHeaderIcon(R.drawable.m3);

		mList.add(msg);

		return mList;
	}

}
