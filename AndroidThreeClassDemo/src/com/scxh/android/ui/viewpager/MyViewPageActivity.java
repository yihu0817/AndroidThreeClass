package com.scxh.android.ui.viewpager;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.scxh.android.R;

public class MyViewPageActivity extends Activity {
	private ViewPager mViewPager;
	private ArrayList<View> mList;
	private MyPagerAdapter mPageAdapter;
	private ImageView mPagerOneImg, mPagerTwoImg, mPagerThreeImg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewpager_layout);

		mViewPager = (ViewPager) findViewById(R.id.myviewpager);
		mPagerOneImg = (ImageView) findViewById(R.id.pager1);
		mPagerTwoImg = (ImageView) findViewById(R.id.pager2);
		mPagerThreeImg = (ImageView) findViewById(R.id.pager3);

		mList = new ArrayList<View>();

		// ===============================================
		LayoutInflater inflater = LayoutInflater.from(this);
		View view1 = inflater.inflate(R.layout.whats1, null);
		View view2 = inflater.inflate(R.layout.whats2, null);
		View view3 = inflater.inflate(R.layout.whats3, null);

		mList.add(view1);
		mList.add(view2);
		mList.add(view3);
		// ==================================================

		mPageAdapter = new MyPagerAdapter(this);
		mViewPager.setAdapter(mPageAdapter);

		mPageAdapter.setData(mList);

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				Log.v("tag", "onPageSelected arg0  :" + arg0);
				switch (arg0) {
				case 0:
					mPagerOneImg.setImageResource(R.drawable.page_now);
					mPagerTwoImg.setImageResource(R.drawable.page);
					mPagerThreeImg.setImageResource(R.drawable.page);
					break;
				case 1:
					mPagerOneImg.setImageResource(R.drawable.page);
					mPagerTwoImg.setImageResource(R.drawable.page_now);
					mPagerThreeImg.setImageResource(R.drawable.page);
					break;
				case 2:
					mPagerOneImg.setImageResource(R.drawable.page);
					mPagerTwoImg.setImageResource(R.drawable.page);
					mPagerThreeImg.setImageResource(R.drawable.page_now);
					break;
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				Log.v("tag", "onPageScrolled");
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				Log.v("tag", "onPageScrollStateChanged");
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
			// 刷新适配器数据
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
		public Object instantiateItem(ViewGroup container, int position) {
			View v = mList.get(position);
			container.addView(v);
			return v;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			View v = mList.get(position);
			container.removeView(v);
		}

	}
}
