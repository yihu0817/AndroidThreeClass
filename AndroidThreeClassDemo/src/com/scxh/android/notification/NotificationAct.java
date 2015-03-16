package com.scxh.android.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import com.scxh.android.R;

public class NotificationAct extends Activity {
	NotificationManager mNotifyManager;
	NotificationCompat.Builder mBuilder;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_notification_layout);
	}

	public void onNotificationClick(View v) {
		notifcationProgress();
	}

	public void onIndeterminNotificationClick(View v) {
		notifcationIndeterminProgress();
	}

	
	private void notifcationProgress() {
		final int id = 11;
		mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("图片下载").setContentText("下载进行中...")
				.setSmallIcon(R.drawable.m1);
		// Start a lengthy operation in a background thread
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				// Do the "lengthy" operation 20 times
				for (incr = 0; incr <= 100; incr += 5) {
					// Sets the progress indicator to a max value, the
					// current completion percentage, and "determinate"
					// state
					mBuilder.setProgress(100, incr, false);
					// Displays the progress bar for the first time.
					mNotifyManager.notify(id, mBuilder.build());
					// Sleeps the thread, simulating an operation
					// that takes time
					try {
						// Sleep for 5 seconds
						Thread.sleep(1 * 1000);
					} catch (InterruptedException e) {
						Log.d("TAG", "sleep failure");
					}
				}
				// When the loop is finished, updates the notification
				mBuilder.setContentText("下载完成");
				// Removes the progress bar
				mBuilder.setProgress(0, 0, false);
				mNotifyManager.notify(id, mBuilder.build());
			}
		}
		// Starts the thread by calling the run() method in its Runnable
		).start();
	}

	private void notifcationIndeterminProgress() {
		final int id = 11;
		mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		mBuilder = new NotificationCompat.Builder(this);
		mBuilder.setContentTitle("图片下载").setContentText("下载进行中...")
				.setSmallIcon(R.drawable.m1);
		// Start a lengthy operation in a background thread
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				// Do the "lengthy" operation 20 times
				for (incr = 0; incr <= 100; incr += 5) {
					// Sets the progress indicator to a max value, the
					// current completion percentage, and "determinate"
					// state
					mBuilder.setProgress(0, 0, true);
					// Displays the progress bar for the first time.
					mNotifyManager.notify(id, mBuilder.build());
					// Sleeps the thread, simulating an operation
					// that takes time
					try {
						// Sleep for 5 seconds
						Thread.sleep(1 * 1000);
					} catch (InterruptedException e) {
						Log.d("TAG", "sleep failure");
					}
				}
				// When the loop is finished, updates the notification
				mBuilder.setContentText("下载完成");
				// Removes the progress bar
				mBuilder.setProgress(0, 0, false);
				mNotifyManager.notify(id, mBuilder.build());
			}
		}
		// Starts the thread by calling the run() method in its Runnable
		).start();
	}
	
}
