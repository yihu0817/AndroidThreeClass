package com.scxh.android.ui;

import com.scxh.android.R;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentActivity extends Activity {
	private Button mLiulqBtn, mDianhuaBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.v("tag", "onCreate ");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_layout);
		mLiulqBtn = (Button) findViewById(R.id.liulqBtn);
		mDianhuaBtn = (Button) findViewById(R.id.dianhuaBtn);
		mLiulqBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("http://www.baidu.com");

				Intent intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(uri);
				startActivity(intent);

			}
		});

		mDianhuaBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:110");
				Intent it = new Intent(Intent.ACTION_DIAL, uri);
				startActivity(it);

			}
		});

	}

	public void onStandardClick(View v) {
		startActivity(new Intent(this, IntentActivity.class));
	}

	public void onSingleTaskClick(View v) {
		startActivity(new Intent(this, TwoAcitity.class));
	}

	@Override
	protected void onStart() {
		Log.v("tag", "onStart ");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.v("tag", "onRestart ");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.v("tag", "onResume ");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.v("tag", "onPause ");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.v("tag", "onStop ");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.v("tag", "onDestroy ");
		super.onDestroy();
	}

}
