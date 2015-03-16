package com.scxh.android.ui.handler;

import android.os.Handler;

public class AsyncTaskUtil {
	private CallBackInterface mCallBackInterface;
	private Handler mHanlder = new Handler();
	public void registerCallBackInterface(CallBackInterface callBackInterface) {
		mCallBackInterface = callBackInterface;
	}

	public void startAsyncTask() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				final String mData = doSome();
				
				mHanlder.post(new Runnable() {
					
					@Override
					public void run() {
						mCallBackInterface.execute(mData);
						
					}
				});
				
			}
		}).start();
	}

	/**
	 * 从网络取数据
	 * 
	 * @return
	 */
	public String doSome() {
		return "我是网络返回数据";
	}

}
