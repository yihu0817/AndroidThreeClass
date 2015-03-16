package com.scxh.android.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.scxh.android.R;
import com.scxh.android.util.Logs;

public class ViewPagerPagerAdapterAct extends Activity {
	private ViewPager mViewPager;
	private ImageView mPagerImageView1,mPagerImageView2,mPagerImageView3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager_layout);

		mPagerImageView1 = (ImageView)findViewById(R.id.pager1);
		mPagerImageView2 = (ImageView)findViewById(R.id.pager2);
		mPagerImageView3 = (ImageView)findViewById(R.id.pager3);
		mViewPager = (ViewPager) findViewById(R.id.viewPager1);

		// =================================
		LayoutInflater inflater = LayoutInflater.from(this);
		View view1 = inflater.inflate(R.layout.whats1, null);
		View view2 = inflater.inflate(R.layout.whats2, null);
		View view3 = inflater.inflate(R.layout.whats3, null);

		ArrayList<View> list = new ArrayList<View>();
		list.add(view1);
		list.add(view2);
		list.add(view3);
		// =================================

		MyPagerAdapter adapter = new MyPagerAdapter(this);

		mViewPager.setAdapter(adapter);

		adapter.setData(list);
		
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int pagerNum) {
				switch(pagerNum){
				case 0:
					mPagerImageView1.setImageResource(R.drawable.page_now);
					mPagerImageView2.setImageResource(R.drawable.page);
					mPagerImageView3.setImageResource(R.drawable.page);
					break;
				case 1:
					mPagerImageView1.setImageResource(R.drawable.page);
					mPagerImageView2.setImageResource(R.drawable.page_now);
					mPagerImageView3.setImageResource(R.drawable.page);
					break;
				case 2:
					mPagerImageView1.setImageResource(R.drawable.page);
					mPagerImageView2.setImageResource(R.drawable.page);
					mPagerImageView3.setImageResource(R.drawable.page_now);
					break;
				}
			
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Logs.v("onPageScrolled");
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				Logs.v("onPageScrollStateChanged");
			}
		});
	}

	public class MyPagerAdapter extends PagerAdapter {
		private ArrayList<View> mList = new ArrayList<View>();
		private Context mContext;

		public MyPagerAdapter(Context context) {
			mContext = context;
		}

		public void setData(ArrayList<View> list) {
			mList = list;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			((ViewPager)container).removeView(mList.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			((ViewPager)container).addView(mList.get(position));
			return mList.get(position);
		}

	}
}
