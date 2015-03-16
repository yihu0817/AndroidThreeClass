package com.scxh.android.pattern;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.scxh.android.pattern.MyButton.MyOnClickListener;

public class MyCallBackActivity extends Activity {
	private MyButton mMyButton;
	private Button mButton;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			Drawable drawable = (Drawable)msg.obj;
			mButton.setBackgroundDrawable(drawable);
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mButton = new Button(this);
		mButton.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mButton.setText("回调");
		
		mMyButton = new MyButton(this);
		mMyButton.setOnclickListener(new MyOnClickListener() {
			
			@Override
			public void onclick(Drawable drawable) {
				Message message = Message.obtain();
				message.obj = drawable;
				mHandler.sendMessage(message);
			}
		});
		
		
		
		//==============================================
	
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mMyButton.getDrawableByNet();
				
			}
		});
		
		setContentView(mButton);
	}
}
