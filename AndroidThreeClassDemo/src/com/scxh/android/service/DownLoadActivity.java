package com.scxh.android.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.scxh.android.R;

public class DownLoadActivity extends Activity implements OnClickListener {
	private Button mFile1Btn, mFile2Btn, mFile3Btn, mFile4Btn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_download_layout);

		mFile1Btn = (Button) findViewById(R.id.button1);
		mFile2Btn = (Button) findViewById(R.id.button2);
		mFile3Btn = (Button) findViewById(R.id.button3);
		mFile4Btn = (Button) findViewById(R.id.button4);

		mFile1Btn.setOnClickListener(this);
		mFile2Btn.setOnClickListener(this);
		mFile3Btn.setOnClickListener(this);
		mFile4Btn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button1:
			startService(new Intent(this, DownLoadFileIntentService.class).putExtra("filename", "文件1"));
			break;
		case R.id.button2:
			startService(new Intent(this, DownLoadFileIntentService.class).putExtra("filename", "文件2"));
			break;
		case R.id.button3:
			startService(new Intent(this, DownLoadFileIntentService.class).putExtra("filename", "文件3"));
			break;
		case R.id.button4:
			startService(new Intent(this, DownLoadFileIntentService.class).putExtra("filename", "文件4"));
			break;
		}
	}
}
