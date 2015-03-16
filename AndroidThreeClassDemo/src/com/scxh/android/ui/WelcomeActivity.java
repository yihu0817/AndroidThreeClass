package com.scxh.android.ui;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.scxh.android.R;
import com.scxh.android.UIMainDemoActivity;

public class WelcomeActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_layout);
		
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(4000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				Intent intent = new Intent(WelcomeActivity.this,UIMainDemoActivity.class);
//				startActivity(intent);
//				finish();
//			}
//		}).start();
		
		Runnable runablde = new Runnable() {
			
			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this,UIMainDemoActivity.class);
				startActivity(intent);
				finish();
				
			}
		};
		
		Handler handler = new Handler();
		
		handler.postDelayed(runablde, 1000);
		
	}
}
