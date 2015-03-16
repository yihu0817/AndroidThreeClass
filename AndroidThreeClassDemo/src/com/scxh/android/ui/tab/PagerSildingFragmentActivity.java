package com.scxh.android.ui.tab;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;

import com.scxh.android.R;

public class PagerSildingFragmentActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private List<Fragment> mList;
	private List<String> mTitleList;
	private MyPagerAdapter pageAdapter;
	private PagerSlidingTabStrip pagerTabStrip;
	private DisplayMetrics dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_pagersliding_layout);
		mViewPager = (ViewPager) findViewById(R.id.viewpagerContainer1);
		pagerTabStrip = (PagerSlidingTabStrip) findViewById(R.id.pagerTabStrip);

		initViewPagerDataSet();
		
		pageAdapter = new MyPagerAdapter(getSupportFragmentManager(), mList,mTitleList);
		mViewPager.setAdapter(pageAdapter);
		
		mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
		
		//自定义控件PagerSlidingTabStrip关连ViewPager，从Viewpager适配器里取Titile数据
		pagerTabStrip.setViewPager(mViewPager);
		setPageTitlesValue();
	}
	/**
	 * 初始化viewPage控件数据源
	 */
	private void initViewPagerDataSet(){
		mList = new ArrayList<Fragment>();
		mList.add(new FragmentMain());
		mList.add(new FragmentSearch());
		mList.add(new FragmentSetting());
		mList.add(new FragmentMain());
		mList.add(new FragmentSearch());
		mList.add(new FragmentSetting());

		mTitleList = new ArrayList<String>();
		mTitleList.add("首页");
		mTitleList.add("搜索");
		mTitleList.add("设置");
		mTitleList.add("首页");
		mTitleList.add("搜索");
		mTitleList.add("设置");
	}
	/**
	 * 设置Titile相关属性
	 */
	private void setPageTitlesValue() {
		dm = getResources().getDisplayMetrics();
		// 设置为true 均匀分配title位置
		pagerTabStrip.setShouldExpand(true);

		pagerTabStrip.setDividerColor(Color.TRANSPARENT);

		//(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm)  将数值1转成dp为单位
		//设置下划分割线高度
		pagerTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, dm)); 

		//设置指示条高条
		pagerTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, dm));

		//设置文本字大小
		pagerTabStrip.setTextSize((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16, dm));

		//设置指示条颜色
		pagerTabStrip.setIndicatorColor(Color.parseColor("#45c01a"));

		//设置选中文本颜色
		pagerTabStrip.setSelectedTextColor(Color.parseColor("#45c01a"));

		//设置Title背景颜色
		pagerTabStrip.setTabBackground(0);//android.R.color.darker_gray
	}

	class MyPagerAdapter extends FragmentPagerAdapter {
		private List<Fragment> fragmentList;
		private List<String> mTititleList;

		public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList,
				List<String> titleList) {
			super(fm);
			this.fragmentList = fragmentList;
			mTititleList = titleList;
		}

		@Override
		public Fragment getItem(int position) {
			return (fragmentList == null || fragmentList.size() == 0) ? null
					: fragmentList.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return (mTititleList == null || mTititleList.size() == 0) ? null
					: mTititleList.get(position);
		}

		@Override
		public int getCount() {
			return fragmentList == null ? 0 : fragmentList.size();

		}

	}
	
	
	public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
	    private static final float MIN_SCALE = 0.85f;
	    private static final float MIN_ALPHA = 0.5f;

	    public void transformPage(View view, float position) {
	        int pageWidth = view.getWidth();
	        int pageHeight = view.getHeight();

	        if (position < -1) { // [-∞,-1)
	            // 这一页已经是最左边的屏幕页
	            view.setAlpha(0);

	        } else if (position <= 1) { // [-1,1]
	            // 修改默认的滑动过渡效果为缩放效果
	            float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
	            float vertMargin = pageHeight * (1 - scaleFactor) / 2;
	            float horzMargin = pageWidth * (1 - scaleFactor) / 2;
	            if (position < 0) {
	                view.setTranslationX(horzMargin - vertMargin / 2);
	            } else {
	                view.setTranslationX(-horzMargin + vertMargin / 2);
	            }

	            // 开始根据缩放系数进行变化 (在 MIN_SCALE 和 1 之间变化)
	            view.setScaleX(scaleFactor);
	            view.setScaleY(scaleFactor);

	            // 根据大小（缩放系数）变化化透明度实现淡化页面效果
	            view.setAlpha(MIN_ALPHA +
	                    (scaleFactor - MIN_SCALE) /
	                    (1 - MIN_SCALE) * (1 - MIN_ALPHA));

	        } else { // (1,+∞ ]
	            // 这一页已经是最右边的屏幕页
	            view.setAlpha(0);
	        }
	    }
	}
}
