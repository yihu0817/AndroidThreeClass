package com.scxh.android.ui.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.util.Logs;

public class HandlerActivity extends Activity {
	private TextView mShowTxt;
	private Button mGetDataBtn,mShowMsgBtn;
	public Handler mLooperHandler;
//	private Handler mHandler = new Handler() {
//		public void handleMessage(Message msg) {
//			String data = msg.obj.toString();
//			mShowTxt.setText(data);
//		};
//	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler_text_layout);
		mShowTxt = (TextView) findViewById(R.id.textviewhandler);
		mGetDataBtn = (Button) findViewById(R.id.getDatabtn);
		mShowMsgBtn = (Button) findViewById(R.id.getDatabtnone);
		
		new LooperThread().start();
		
		mGetDataBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GetDataThread mGetDataThread = new GetDataThread();
				mGetDataThread.start();
				
			}
		});

		mShowMsgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Message msg1 = new Message();
				msg1.what=2;
				mLooperHandler.sendMessage(msg1);
			}
		});
	}

	class GetDataThread extends Thread {
		public String mData = "初始值";

		@Override
		public void run() {
			super.run();
			mData = getDataByNet();

			Message msg = new Message();
			msg.what=1;
			msg.obj = mData;
			mLooperHandler.sendMessage(msg);
			
		}
	}
	class LooperThread extends Thread {
		
		public void run() {
			Looper.prepare();
			Logs.v("初始化LooperThread消息队列消息");
//			Looper looper = Looper.myLooper();
			Looper looper = Looper.getMainLooper();
			mLooperHandler = new Handler(looper) {
				public void handleMessage(Message msg) {
					int what = msg.what;
					switch(what){
					case 1:
						Logs.v("处理LooperThread消息队列消息");
						String data = msg.obj.toString();
						mShowTxt.setText(data);
						break;
					case 2:
						Toast.makeText(HandlerActivity.this, "我是消息二", Toast.LENGTH_SHORT).show();
						break;
					}
					
					
				}
			};

			Looper.loop();
		}
	}

	/**
	 * 从网络取数据
	 * 
	 * @return
	 */
	public String getDataByNet() {
		return "我是网络返回数据";
	}
}
