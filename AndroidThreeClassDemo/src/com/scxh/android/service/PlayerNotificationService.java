package com.scxh.android.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

import com.scxh.android.util.Constances;
import com.scxh.android.util.Logs;

public class PlayerNotificationService extends Service {
	private static final String MUSIC_PATH = "/mnt/sdcard/music/dzw.mp3";
	private MediaPlayer mMediaPlayer;
	private boolean isPause = false;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String path = intent.getStringExtra("MUSIC_PATH");
		if(path == null || path.equals("")){
			path = MUSIC_PATH;
		}
		Logs.v("onStartCommand path  :"+path);
		playMusic(path);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	public void playMusic(String path) {
		if (mMediaPlayer != null && isPause) {
			isPause = false;
			mMediaPlayer.start();
			return;
		}
		if (mMediaPlayer == null) {
			mMediaPlayer = new MediaPlayer();
		} else {
			mMediaPlayer.reset();
		}

		try {
			mMediaPlayer.setDataSource(path);

			mMediaPlayer.prepare();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		mMediaPlayer.start();

		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				Intent intent = new Intent();
				intent.setAction(Constances.ACTION_STOP);
				sendBroadcast(intent);

			}
		});
	}

	public void pauseMusic() {
		if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
			mMediaPlayer.pause();
			isPause = true;
		}
	}
}
