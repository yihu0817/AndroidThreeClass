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
		// ��̬ע��㲥��Ϣ
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
		if (v == mTongTaiReceiverBtn)// ����̬ע���BroadcastReceiver��������
		{
			Intent intent = new Intent(INTENAL_ACTION_DONGTAI);
			sendBroadcast(intent);
		} else if (v == mJingTaiReceiverBtn)// ����̬ע���BroadcastReceiver��������
		{
			Intent intent = new Intent(INTENAL_ACTION_JINGTAI);
			intent.putExtra("Name", "�»�����1111");
			intent.putExtra("Blog", "http://www.xinhua.com.cn");
			sendBroadcast(intent);
		} else if (v == mSystemReceiverBtn)//����̬ע���BroadcastReceiver��������
		{
			
			// ע��ϵͳ���������Ϣ�㲥
			IntentFilter filter = new IntentFilter();
			filter.addAction(Intent.ACTION_BATTERY_CHANGED);// ϵͳ���������Ϣ
			filter.addAction(INTENAL_ACTION_SYSTEM);// �������Զ�����Ϣ
			registerReceiver(mSystemReceiver, filter);
			
			Intent intent = new Intent(INTENAL_ACTION_SYSTEM);
			intent.putExtra("Name", "�»�����");
			intent.putExtra("Blog", "http://www.xinhua.com");
			sendBroadcast(intent);// ���ݹ�ȥ
		}
	}

	/*
	 * ���ն�̬ע��㲥��BroadcastReceiver
	 */
	private BroadcastReceiver mDongTaiReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Toast.makeText(context, "��̬>>>>>>>>>>>>:" + action, 1000).show();
		}
	};

	private BroadcastReceiver mSystemReceiver = new BroadcastReceiver() {

		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			// �����׽����action��ACTION_BATTERY_CHANGED
			if (Intent.ACTION_BATTERY_CHANGED.equals(action)) {
				// ��δ֪Intent���������ݣ�����Ҫͨ�����·������о�
				Bundle b = intent.getExtras();
				Object[] lstName = b.keySet().toArray();

				for (int i = 0; i < lstName.length; i++) {
					String keyName = lstName[i].toString();
					Log.e(keyName, String.valueOf(b.get(keyName)));
				}
			}
			// �����׽����action��INTENAL_ACTION_3
			if (INTENAL_ACTION_SYSTEM.equals(action)) {
				// ��δ֪Intent���������ݣ�����Ҫͨ�����·������о�
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