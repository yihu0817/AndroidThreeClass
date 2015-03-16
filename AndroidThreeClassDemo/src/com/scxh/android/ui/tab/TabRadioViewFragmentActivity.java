package com.scxh.android.ui.tab;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.scxh.android.R;

public class TabRadioViewFragmentActivity extends FragmentActivity {
	private RadioGroup mRadioGroup;
//	private static final int tab1 = 1;
//	private static final int tab2 = 2;
//	private static final int tab3 = 3;
	private FragmentTabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_raido_tab_fragment_layout);
		mRadioGroup = (RadioGroup) findViewById(R.id.raidoGroupTab);

		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(),android.R.id.tabcontent);

		mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("首页"),FragmentMain.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("搜索"),FragmentSearch.class, null);
		mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("设置"),FragmentSetting. class, null);

		mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.raidoTab1:
					mTabHost.setCurrentTabByTag("tab1");
					break;
				case R.id.raidoTab2:
					mTabHost.setCurrentTabByTag("tab2");
					break;
				case R.id.raidoTab3:
					mTabHost.setCurrentTabByTag("tab3");
					break;
				}

			}
		});
		((RadioButton) mRadioGroup.getChildAt(0)).toggle(); //默认选中第一项
	}

}
