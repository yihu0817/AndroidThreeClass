package com.scxh.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class StopBroadCastReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Toast.makeText(context, "静态广播 消息已收到 播放音乐停止>>>>>>>>>>", Toast.LENGTH_SHORT).show();
	}

}
