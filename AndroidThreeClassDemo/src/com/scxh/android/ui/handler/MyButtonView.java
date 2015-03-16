package com.scxh.android.ui.handler;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

public class MyButtonView extends Button {
	private OnCallBackInterface mCallBackInterface;

	public interface OnCallBackInterface {
		public void execute(String data);
	}

	public void setOnCallBackInterface(OnCallBackInterface callBackIntentface) {
		mCallBackInterface = callBackIntentface;
	}

	public MyButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void registerOnClickListeners() {
		setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mCallBackInterface.execute("我是回调数据");
			}
		});
		
	}
	
}
