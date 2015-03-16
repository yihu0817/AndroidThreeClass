package com.scxh.android.ui.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

import com.scxh.android.R;
import com.scxh.android.ui.RadioActivity;
import com.scxh.android.ui.layout.FramLayoutActivity;
import com.scxh.android.ui.listview.SimpleActivity;

public class TabRadioViewActivity extends TabActivity implements
		OnTabChangeListener {
	private RadioGroup mRadioGroup;
	private static final int tab1 = 1;
	private static final int tab2 = 2;
	private static final int tab3 = 3;
	private TabHost mTabHost;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_raido_tab_view_layout);
		mRadioGroup = (RadioGroup) findViewById(R.id.raidoGroupTab);

		mTabHost = getTabHost();

		TabSpec tabSpec1 = mTabHost.newTabSpec("tab1");
		tabSpec1.setIndicator("tag1");
		tabSpec1.setContent(new Intent(this, RadioActivity.class));

		mTabHost.addTab(tabSpec1);

		TabSpec tabSpec2 = mTabHost.newTabSpec("tab2");
		tabSpec2.setIndicator("tag2");
		tabSpec2.setContent(new Intent(this, SimpleActivity.class));
		mTabHost.addTab(tabSpec2);

		TabSpec tabSpec3 = mTabHost.newTabSpec("tab3");
		tabSpec3.setIndicator("tag3");
		tabSpec3.setContent(new Intent(this, FramLayoutActivity.class));
		mTabHost.addTab(tabSpec3);

		mTabHost.setOnTabChangedListener(this);

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
		((RadioButton) mRadioGroup.getChildAt(0)).toggle();
	}

	@Override
	public void onTabChanged(String tabId) {
		Log.v("TAG", "onTabChanged  tag   :" + tabId);
	}
}
