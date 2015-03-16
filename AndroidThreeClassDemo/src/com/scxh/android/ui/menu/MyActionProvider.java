package com.scxh.android.ui.menu;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.Toast;

import com.scxh.android.R;

public class MyActionProvider extends ActionProvider {
	private Context mContext;

	public MyActionProvider(Context context) {
		super(context);
		mContext = context;
	}

	@Override
	public View onCreateActionView() {
		return null;
	}

	@Override
	public boolean hasSubMenu() {
		return true;
	}

	@Override
	public void onPrepareSubMenu(SubMenu subMenu) {
		subMenu.clear();
		subMenu.add("新建").setIcon(R.drawable.action_seting)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(mContext, "新建", Toast.LENGTH_SHORT)
								.show();
						return false;
					}
				});

		subMenu.add("编辑").setIcon(R.drawable.actionbar_search_icon)
				.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem item) {
						Toast.makeText(mContext, "编辑", Toast.LENGTH_SHORT)
								.show();
						return false;
					}
				});

		super.onPrepareSubMenu(subMenu);
	}
}
