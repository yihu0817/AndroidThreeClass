package com.scxh.android.ui.tab;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

import com.scxh.android.R;
import com.scxh.android.ui.layout.FramLayoutActivity;
import com.scxh.android.ui.listview.MyBaseAdapterActivity;
import com.scxh.android.ui.listview.SimpleActivity;

public class TabViewActivity extends TabActivity implements OnTabChangeListener{
	private static final int tab1 = 1;
	private static final int tab2 = 2;
	private static final int tab3 = 3;
	private TabHost mTabHost;
	private LayoutInflater mInflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_view_layout);

		mInflater = LayoutInflater.from(this);

		mTabHost = getTabHost();

		TabSpec tabSpec1 = mTabHost.newTabSpec("tab1");
		tabSpec1.setIndicator(createTabView(tab1));
		tabSpec1.setContent(new Intent(this, MyBaseAdapterActivity.class));
		
		mTabHost.addTab(tabSpec1);

		TabSpec tabSpec2 = mTabHost.newTabSpec("tab2");
		tabSpec2.setIndicator(createTabView(tab2));
		tabSpec2.setContent(new Intent(this, SimpleActivity.class));
		mTabHost.addTab(tabSpec2);

		TabSpec tabSpec3 = mTabHost.newTabSpec("tab3");
		tabSpec3.setIndicator(createTabView(tab3));
		tabSpec3.setContent(new Intent(this, FramLayoutActivity.class));
		mTabHost.addTab(tabSpec3);
		
		mTabHost.setOnTabChangedListener(this);

	}
	private View createTabView(int tab) {
		View v = mInflater.inflate(R.layout.tab_tabviewactivity_view, null);
		TextView titleTxt = (TextView) v.findViewById(R.id.titleTxt);
		ImageView iconImg = (ImageView) v.findViewById(R.id.iconImg);
		
		switch (tab) {
		case tab1:
			titleTxt.setText("首页");
			iconImg.setImageResource(R.drawable.btn_tab_main);
			break;
		case tab2:
			titleTxt.setText("美食");
			iconImg.setImageResource(R.drawable.btn_tab_meishi);

			break;
		case tab3:
			titleTxt.setText("娱乐");
			iconImg.setImageResource(R.drawable.btn_tab_yule);
		
			break;
		}

		return v;
	}

	@Override
	public void onTabChanged(String tabId) {
		Log.v("TAG", "onTabChanged  tag   :"+tabId);
	}
}
