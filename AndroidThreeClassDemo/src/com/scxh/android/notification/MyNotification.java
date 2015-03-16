package com.scxh.android.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

import com.scxh.android.R;
import com.scxh.android.service.PlayerNotificationService;
import com.scxh.android.service.PlayerUIActivity;

public class MyNotification extends Activity {
	private static final String MUSIC_PATH = "/mnt/sdcard/music/dzw.mp3";
	private Button mSendNotifyBtn;
	private NotificationManager mNotifyManager;
	private NotificationCompat.Builder mBuilder;
	private Handler mHandler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mynotify_layout);
		mSendNotifyBtn = (Button) findViewById(R.id.notificationone);

		mSendNotifyBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				createCustomNotifiaction();
			}
		});
	}

	public void onBaseNotificationClick(View v) {
		createBaseNotification();
	}

	public void onBaseTedingNotificationClick(View v) {
		createBaseNotificationOne();
	}

	/**
	 * 创建一个自定义Notification
	 */
	public void createCustomNotifiaction() {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				MyNotification.this);
		mBuilder.setSmallIcon(R.drawable.m1);
		mBuilder.setTicker("自定义通知，你有新消息");
		RemoteViews view = new RemoteViews(getPackageName(),
				R.layout.notification_player_layout);
		mBuilder.setContent(view);

		Intent intent = new Intent(this, PlayerNotificationService.class);
		intent.putExtra("MUSIC_PATH", MUSIC_PATH);
		PendingIntent pendingIntent = PendingIntent.getService(this, 01,
				intent, PendingIntent.FLAG_UPDATE_CURRENT);
		view.setOnClickPendingIntent(R.id.playImageBtn, pendingIntent);

		Notification notification = mBuilder.build();
		int id = 29;

		NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		mNotifyManager.notify(id, notification);
	}

	/**
	 * 创建一个带进度条的Notification
	 */
	private void createProgressNotification() {
		mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		final int id = 001;
		// 第一步
		mBuilder = new NotificationCompat.Builder(MyNotification.this);
		mBuilder.setSmallIcon(R.drawable.m3);
		mBuilder.setContentTitle("图片下载");
		mBuilder.setContentText("正在下载中...");

		// mBuilder.setProgress(0, 0, false);
		new Thread() {
			public void run() {
				int max = 100;
				for (int i = 0; i < 100; i++) {
					mBuilder.setProgress(max, i, true);
					mNotifyManager.notify(id, mBuilder.build());
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				mHandler.post(new Runnable() {

					@Override
					public void run() {
						mBuilder.setContentText("下载完成");
						mNotifyManager.notify(id, mBuilder.build());
					}
				});
			};
		}.start();

		// 第三步 发布通知
		Notification notification = mBuilder.build();
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		mNotifyManager.notify(id, notification);
	}
	/**
	 * 创建一个普通的Notification
	 */
	private void createBaseNotification() {
		// 第一步
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				MyNotification.this);
		mBuilder.setSmallIcon(R.drawable.m3);
		mBuilder.setContentTitle("通知标题");
		mBuilder.setContentText("通知内容，晚上来陪我");
		mBuilder.setTicker("收到一条新通知");
		
		//============BigView================
		Intent playIntent = new Intent(this,PlayerNotificationService.class);
		playIntent.setAction("action.scxh.android.service.PlayerNotificationService");
		playIntent.putExtra("MUSIC_PATH", MUSIC_PATH);
		PendingIntent playPengdingIntent = PendingIntent.getService(this,0, playIntent, 0);
		
		
		Intent playUIIntent = new Intent(this,PlayerUIActivity.class);
		PendingIntent playUiPengdingIntent = PendingIntent.getActivity(this,0, playUIIntent, 0);
		//================================
		
		
		// 第二步 设置通知点击行为
		Intent intent = new Intent(MyNotification.this, PlayerUIActivity.class);

		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
		stackBuilder.addParentStack(PlayerUIActivity.class);
		stackBuilder.addNextIntent(intent);
		PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);

		mBuilder.setContentIntent(pendingIntent);
		mBuilder.setAutoCancel(true);

//		mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("BigViewText"));
		mBuilder.addAction(R.drawable.player_play, "播放", playPengdingIntent);
		mBuilder.addAction(R.drawable.player_pause,"暂停",playUiPengdingIntent);
		// 第三步 发布通知
		NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int notificationId = 001;
		Notification notification = mBuilder.build();
		mNotifyManager.notify(notificationId, notification);
	}

	/**
	 * 创建一个普通的Notification
	 */
	private void createBaseNotificationOne() {
		// 第一步
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				MyNotification.this);
		mBuilder.setSmallIcon(R.drawable.m3);
		mBuilder.setContentTitle("通知标题");
		mBuilder.setContentText("通知内容，晚上来陪我");
		mBuilder.setTicker("收到一条新通知");
		mBuilder.setWhen(System.currentTimeMillis());
		// 第二步 设置通知点击行为
		Intent intent = new Intent(MyNotification.this, MyNotification.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

		PendingIntent pendingIntent = PendingIntent.getActivity(
				MyNotification.this, 0, intent,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mBuilder.setContentIntent(pendingIntent);
		mBuilder.setAutoCancel(true);

		// 第三步 发布通知
		NotificationManager mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		int notificationId = 001;
		Notification notification = mBuilder.build();
		mNotifyManager.notify(notificationId, notification);
	}
}
