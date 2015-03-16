package com.scxh.android.ui;

import com.scxh.android.R;
import com.scxh.android.R.color;
import com.scxh.android.R.dimen;
import com.scxh.android.pattern.SinglePattern;

import android.app.Activity;
import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LifeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		LinearLayout linearLayout = new LinearLayout(this);

		linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		linearLayout.setBackgroundColor(getResources()
				.getColor(R.color.blue_bg));

		TextView textView = new TextView(this);
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		textView.setText("activity 生命周期");
		textView.setTextSize(getResources().getDimension(
				R.dimen.text_size_small));
		textView.setBackgroundColor(getResources().getColor(
				android.R.color.holo_green_dark));

		linearLayout.addView(textView);

		setContentView(linearLayout);

		Log.v("TAG", "LifeActivity onCreate >>>>>>>>>>>");

	}

	@Override
	protected void onStart() {
		super.onStart();
		Log.d("TAG", "LifeActivity onStart >>>>>>>>>>>");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.i("TAG", "LifeActivity onResume >>>>>>>>>>>");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.w("TAG", "LifeActivity onPause >>>>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
		Log.e("TAG", "LifeActivity onStop >>>>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v("TAG", "LifeActivity onDestroy >>>>>>>>>>>");
	}
}
