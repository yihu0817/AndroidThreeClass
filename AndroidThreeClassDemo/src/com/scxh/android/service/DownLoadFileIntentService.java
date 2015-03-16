package com.scxh.android.service;

import com.scxh.android.util.Logs;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class DownLoadFileIntentService extends IntentService {

	public DownLoadFileIntentService() {
		super("DownLoadFileIntentService");
	}
	@Override
	protected void onHandleIntent(Intent intent) {
		String fileName = intent.getStringExtra("filename");
		long endTime = System.currentTimeMillis() + 5 * 1000;
		while (System.currentTimeMillis() < endTime) {
			synchronized (this) {
				try {
					wait(endTime - System.currentTimeMillis());
				} catch (Exception e) {
				}
			}
		}
		Logs.v("下载完成"+fileName);

	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String fileName = intent.getStringExtra("filename");
		Logs.i("下载开始"+fileName);
		Toast.makeText(this, "下载开始"+fileName, Toast.LENGTH_SHORT).show();
		return super.onStartCommand(intent, flags, startId);
	}

}
