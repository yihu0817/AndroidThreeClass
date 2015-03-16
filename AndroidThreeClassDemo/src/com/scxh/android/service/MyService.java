package com.scxh.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.scxh.android.util.Logs;

public class MyService extends Service {

	@Override
	public void onCreate() {
		super.onCreate();
		Logs.v("MyService  onCreate>>>>>");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Logs.v("MyService  onStartCommand>>>>>");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		Logs.v("MyService  onBind>>>>>");
		return null;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Logs.v("MyService  onUnbind>>>>>");
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Logs.v("MyService  onDestroy>>>>>");
		super.onDestroy();
	}

}
