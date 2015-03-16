package com.scxh.android.ui.slidlemenu;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.scxh.android.R;
import com.scxh.android.ui.slidlemenu.SlidemenuLeftFragment.OnSwitchListener;
import com.warmtel.slidingmenu.lib.SlidingMenu;
import com.warmtel.slidingmenu.lib.app.SlidingFragmentActivity;

public class SlidenMenuMainAct extends SlidingFragmentActivity implements
		OnSwitchListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.slidenmenu_layout);

		// 添加头条Fragment
//		ToutiaoFragment toutiaoFragment = new ToutiaoFragment();
		PagerSildingTabFragment pagerSildingTabFragment = PagerSildingTabFragment.newInsanse();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.slidenmenu_content_layout, pagerSildingTabFragment)
				.commit();

		initSlidingMenu();
	}

	/**
	 * 初始化侧滑菜单
	 */
	private void initSlidingMenu() {
		// 初始化左侧滑菜单
		SlidingMenu sm = getSlidingMenu();
		sm.setMode(SlidingMenu.LEFT); // 左侧滑

		setBehindContentView(R.layout.left_menu_frame);
		sm.setSlidingEnabled(true);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		sm.setShadowWidthRes(R.dimen.shadow_width);

		getSupportFragmentManager().beginTransaction()
				.replace(R.id.left_menu_frame, new SlidemenuLeftFragment())
				.commit();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setBehindScrollScale(0);
		sm.setFadeDegree(0.25f);
	}

	public void onSwitchClick(View v) {
		toggle();
	}

	@Override
	public void onToutiao() {
		// 添加头条Fragment
		ToutiaoFragment toutiaoFragment = new ToutiaoFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.slidenmenu_content_layout, toutiaoFragment)
				.commit();

		new Handler().postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);

	}

	@Override
	public void onYule() {
		// 添加头条Fragment
		YuleFragment yuleFragment = new YuleFragment();
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.slidenmenu_content_layout, yuleFragment).commit();
		new Handler().postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}
}
