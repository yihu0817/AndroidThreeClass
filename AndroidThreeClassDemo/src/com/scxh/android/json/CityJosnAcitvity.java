package com.scxh.android.json;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.scxh.android.R;
import com.scxh.android.json.bean.CityBean;
import com.scxh.android.json.bean.CityMessage;
import com.scxh.android.util.AsyncImageLoader;
import com.scxh.android.util.AsyncImageLoader.ImageCallbackForBitmap;
import com.scxh.android.util.Constances;
import com.scxh.android.util.HttpConnectUtil;
import com.scxh.android.util.HttpConnectUtil.HttpConnectInterface;
import com.scxh.android.util.HttpConnectUtil.HttpMethod;
import com.scxh.android.util.Logs;

public class CityJosnAcitvity extends Activity {
	private ListView mListView;
	private ProgressBar mProgressBar;
	private MyBaseAdapter adapter;
	private AsyncImageLoader mAsyncImageLoader;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arraylist_layout);
		mListView = (ListView) findViewById(R.id.arraylist);
		mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
//		String json = ReadAssetsFile.readtxt(this, "json_list_test");
		
		adapter = new MyBaseAdapter(this);
		mListView.setAdapter(adapter);
		
		mListView.setEmptyView(mProgressBar); //有适配器有数据显示ListView,无数据显示ProgressBar
		
		HttpConnectUtil mHttpConnectUtil = new HttpConnectUtil();
		HttpConnectInterface mHttpConnectInterface = new HttpConnectInterface(){

			@Override
			public void execute(String result) {
				Logs.v("result >>> :"+result);
				
				CityMessage cityMessage = JSON.parseObject(result, CityMessage.class);
				
				List<CityBean> cityList = cityMessage.getInfo();
				adapter.setData(cityList);
			}
			
		};
		
		mHttpConnectUtil.setmHttpConnectInterface(mHttpConnectInterface);
		
		mHttpConnectUtil.asyncConnectJson(Constances.BASE_URL+"/json/json_h", HttpMethod.POST);
		
		mAsyncImageLoader = new AsyncImageLoader();
		
	}
	
	
	class MyBaseAdapter extends BaseAdapter {
		private List<CityBean> mList = new ArrayList<CityBean>();
	
		private Context mContext;
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(mContext);
		}
		
		public MyBaseAdapter(Context context, List<CityBean> list) {
			mContext = context;
			mList = list;
			inflater = LayoutInflater.from(mContext);
		}
		
		public void setData(List<CityBean> list){
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
			final ViewHolder holder;
			
			if(convertView == null){
				convertView = inflater.inflate(R.layout.item_baseadapter_view, parent,false);
				
				holder = new ViewHolder();  //因为View setTag方法只能放一个Object,
				holder.codeTxt = (TextView) convertView.findViewById(R.id.titletextView);
				holder.nameTxt = (TextView) convertView.findViewById(R.id.infotextView);
				holder.headerImg = (ImageView)convertView.findViewById(R.id.iconImg);
				convertView.setTag(holder);

			}else{
				holder = (ViewHolder) convertView.getTag();
			}
			
			//==========取数据项给View对应控件赋值=============
			CityBean msg = (CityBean) getItem(position);

			holder.codeTxt.setText(msg.getKey());
			holder.nameTxt.setText(msg.getValue());

			Bitmap bitmap = mAsyncImageLoader.loadBitmap(msg.getNearest(), new ImageCallbackForBitmap() {
				
				@Override
				public void imageLoaded(Bitmap bitmap, String imageUrl) { //第一次网络获取图片
					holder.headerImg.setImageBitmap(bitmap);
				}
			});
			
			if(bitmap != null){ //获取内存缓存图片
				holder.headerImg.setImageBitmap(bitmap);
			}
			
			
//			Picasso.with(CityJosnAcitvity.this).load(msg.getNearest()).into(holder.headerImg);
			
            //===========================================
			return convertView;
		}
		
		class ViewHolder{
			TextView codeTxt = null;
			TextView nameTxt = null;
			ImageView headerImg = null;
		}
	}
	
}
