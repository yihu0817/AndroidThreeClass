package com.scxh.android.ui.slidlemenu;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.scxh.android.R;

public class SlidemenuLeftFragment extends Fragment {
	private Button mToutiaoBtn;
	private Button mYuleBtn;
	public OnSwitchListener mOnSwitchListener;

	interface OnSwitchListener {
		public void onToutiao();

		public void onYule();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mOnSwitchListener = (OnSwitchListener)activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.slidmenu_left_layout, container,
				false);
		mToutiaoBtn = (Button) v.findViewById(R.id.toutiaoBtn);
		mYuleBtn = (Button) v.findViewById(R.id.yuleBtn);

		mToutiaoBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 添加头条Fragment
				mOnSwitchListener.onToutiao();
			}
		});

		mYuleBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 添加娱乐Fragment
				mOnSwitchListener.onYule();
			}
		});
		return v;
	}
}
