package com.scxh.android.receiver.sms;

import android.app.Service;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.IBinder;

public class BootService extends Service {
	public static final String TAG = "BootService";
	private ContentObserver mObserver;

	@Override
	public void onCreate(){
		super.onCreate();
		addSMSObserver();
	}

	public void addSMSObserver(){
		mObserver = new SMSObserver(getContentResolver(), new SMSHandler(this));
		getContentResolver().registerContentObserver(SMS.CONTENT_URI, true, mObserver);
	}

	@Override
	public IBinder onBind(Intent intent){
		return null;
	}

	@Override
	public void onDestroy(){
		super.onDestroy();
		getContentResolver().unregisterContentObserver(mObserver);
	}

}