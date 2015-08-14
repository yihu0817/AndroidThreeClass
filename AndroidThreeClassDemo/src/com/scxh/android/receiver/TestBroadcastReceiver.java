package com.scxh.android.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.scxh.android.R;

public class TestBroadcastReceiver extends Activity implements
		View.OnClickListener {
	private Button mTongTaiReceiverBtn, mJingTaiReceiverBtn,mSystemReceiverBtn;
	static final String INTENAL_ACTION_DONGTAI = "com.testBroadcastReceiver.Internal_1";
	static final String INTENAL_ACTION_JINGTAI = "action.scxh.android.receiver.MyBroadCastReceiver.stop";
	static final String INTENAL_ACTION_SYSTEM = "com.testBroadcastReceiver.Internal_3";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.receiver_main_act);
		mTongTaiReceiverBtn = (Button) this.findViewById(R.id.Button01);
		mJingTaiReceiverBtn = (Button) this.findViewById(R.id.Button02);
		mSystemReceiverBtn = (Button) this.findViewById(R.id.Button03);

		mTongTaiReceiverBtn.setOnClickListener(this);
		mJingTaiReceiverBtn.setOnClickListener(this);
		mSystemReceiverBtn.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 动态注册广播消息
		IntentFilter filter = new IntentFilter();
		filter.addAction(INTENAL_ACTION_DONGTAI);
		registerReceiver(mDongTaiReceiver, filter);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mDongTaiReceiver != null) {
			unregisterReceiver(mDongTaiReceiver);
		}
		if (mSystemReceiver != null) {
			try{
				unregisterReceiver(mSystemReceiver);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	@Override
	public void onClick(View v) {
		if (v == mTongTaiReceiverBtn)// 给动态注册的BroadcastReceiver发送数据
		{
			Intent intent = new Intent(INTENAL_ACTION_DONGTAI);
			sendBroadcast(intent);
		} else if (v == mJingTaiReceiverBtn)// 给静态注册的BroadcastReceiver发送数据
		{
			Intent intent = new Intent(INTENAL_ACTION_JINGTAI);
			intent.putExtra("Name", "新华教育1111");
			intent.putExtra("Blog", "http://www.xinhua.com.cn");
			sendBroadcast(intent);
		} else if (v == mSystemReceiverBtn)//给动态注册的BroadcastReceiver发送数据
		{
			
			// 注册系统电量检测信息广播
			IntentFilter filter = new IntentFilter();
			filter.addAction(Intent.ACTION_BATTERY_CHANGED);// 系统电量检测信息
			filter.addAction(INTENAL_ACTION_SYSTEM);// 第三组自定义消息
			registerReceiver(mSystemReceiver, filter);
			
			Intent intent = new Intent(INTENAL_ACTION_SYSTEM);
			intent.putExtra("Name", "新华教育");
			intent.putExtra("Blog", "http://www.xinhua.com");
			sendBroadcast(intent);// 传递过去
		}
	}

	/*
	 * 接收动态注册广播的BroadcastReceiver
	 */
	private BroadcastReceiver mDongTaiReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Toast.makeText(context, "动态>>>>>>>>>>>>:" + action, 1000).show();
		}
	};

	private BroadcastReceiver mSystemReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// 如果捕捉到的action是ACTION_BATTERY_CHANGED
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
				// 当未知Intent包含的内容，则需要通过以下方法来列举
				Bundle b = intent.getExtras();
				Object[] lstName = b.keySet().toArray();

				for (int i = 0; i < lstName.length; i++) {
					String keyName = lstName[i].toString();
					Log.e(keyName, String.valueOf(b.get(keyName)));
				}
			}
			// 如果捕捉到的action是INTENAL_ACTION_3
			if (INTENAL_ACTION_SYSTEM.equals(action)) {
				// 当未知Intent包含的内容，则需要通过以下方法来列举
				Bundle b = intent.getExtras();
				Object[] lstName = b.keySet().toArray();

				for (int i = 0; i < lstName.length; i++) {
					String keyName = lstName[i].toString();
					Log.e(keyName, b.getString(keyName));
				}

			}
		}
	};

}