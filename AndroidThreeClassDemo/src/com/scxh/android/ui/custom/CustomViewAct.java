package com.scxh.android.ui.custom;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.scxh.android.R;

public class CustomViewAct extends Activity implements OnClickListener {
	private TitleView mTitleView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_custom1_layout);

		mTitleView = (TitleView) findViewById(R.id.customTitle);
		mTitleView.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.left_imagebtn:
			finish();
			break;
		}

	}

}
