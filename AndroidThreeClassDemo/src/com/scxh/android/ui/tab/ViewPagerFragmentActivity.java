package com.scxh.android.ui.tab;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.scxh.android.R;

public class ViewPagerFragmentActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private RadioGroup mRadioGroup;
	private FragmentMain mainFragment;
	private FragmentSearch searchFragment;
	private FragmentSetting settingFragment;
	private List<Fragment> mList;
	private List<String> mTitleList;
	private MyPagerAdapter pageAdapter;
	private PagerTabStrip mPagerTabStrip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragment_viewpager_layout);
		mViewPager = (ViewPager) findViewById(R.id.viewpagerContainer);
		mRadioGroup = (RadioGroup) findViewById(R.id.bottomRg);
		mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertab);

		mPagerTabStrip.setTabIndicatorColor(getResources().getColor(
				android.R.color.holo_red_light));

		mPagerTabStrip.setBackgroundColor(getResources().getColor(
				android.R.color.holo_blue_dark));

		mainFragment = new FragmentMain();
		searchFragment = new FragmentSearch();
		settingFragment = new FragmentSetting();

		mList = new ArrayList<Fragment>();
		mList.add(mainFragment);
		mList.add(searchFragment);
		mList.add(settingFragment);

		mTitleList = new ArrayList<String>();
		mTitleList.add("首页");
		mTitleList.add("搜索");
		mTitleList.add("设置");

		pageAdapter = new MyPagerAdapter(getSupportFragmentManager(), mList,
				mTitleList);
		mViewPager.setAdapter(pageAdapter);

		/**
		 * 实现滑动时TAB页同步变化
		 */
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			@Override
			public void onPageSelected(int arg0) {
				((RadioButton) mRadioGroup.getChildAt(arg0)).toggle(); // 默认选中第一项
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});

		/**
		 * 实现Tab页点击切换效果
		 */
		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rbOne:
					mViewPager.setCurrentItem(0);
					break;
				case R.id.rbTwo:
					mViewPager.setCurrentItem(1);
					break;
				case R.id.rbThree:
					mViewPager.setCurrentItem(2);
					break;
				}

			}
		});

		((RadioButton) mRadioGroup.getChildAt(0)).toggle(); // 默认选中第一项
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
