package com.scxh.android.ui.slidlemenu;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android.R;
import com.scxh.android.ui.tab.FragmentMain;
import com.scxh.android.ui.tab.FragmentSearch;
import com.scxh.android.ui.tab.FragmentSetting;
import com.scxh.android.ui.tab.PagerSlidingTabStrip;

public class PagerSildingTabFragment extends Fragment {
	private ViewPager mViewPager;
	private List<Fragment> mList;
	private List<String> mTitleList;
	private MyPagerAdapter pageAdapter;
	private PagerSlidingTabStrip pagerTabStrip;
	private DisplayMetrics dm;

	public static PagerSildingTabFragment newInsanse(){
		PagerSildingTabFragment pagerSildingTabFragment = new PagerSildingTabFragment();
		return pagerSildingTabFragment;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_pagersliding_layout, container, false);
		return v;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mViewPager = (ViewPager) getView().findViewById(R.id.viewpagerContainer1);
		pagerTabStrip = (PagerSlidingTabStrip) getView().findViewById(R.id.pagerTabStrip);

		initViewPagerDataSet();
		
		pageAdapter = new MyPagerAdapter(getFragmentManager(), mList,mTitleList);
		mViewPager.setAdapter(pageAdapter);

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
		mTitleList.add("头条");
		mTitleList.add("娱乐");
		mTitleList.add("NBA");
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
}
