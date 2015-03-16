package com.scxh.android.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.scxh.android.util.Constances;

public class PlayerService extends Service {
	private MediaPlayer mMediaPlayer;
	private Handler mHandler;
	private PlayerSerivceiBinder mPlayerSerivceiBinder = new PlayerSerivceiBinder();
	/**
	 * 实现服务与组件间通讯， 
	 * 第一步:定义通讯接口 ,
	 * 第二步:定义一个类继续Binder实现接口,
	 */
	public interface IPlayerSerivice {
		// 播放音乐
		public void playMusic(String path);

		public void pauseMusic();
		
		public MediaPlayer instanceMediaPlayer();
		
		public void setHandler(Handler handler);
	}
	private boolean isPause = false;
	class PlayerSerivceiBinder extends Binder implements IPlayerSerivice {

		@Override
		public void playMusic(String path) {
			if(mMediaPlayer != null && isPause){
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

		@Override
		public void pauseMusic() {
			if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
				isPause = true;
			}
		}

		@Override
		public MediaPlayer instanceMediaPlayer() {
			if(mMediaPlayer == null){
				mMediaPlayer = new MediaPlayer();
			}
			return mMediaPlayer;
		}

		@Override
		public void setHandler(Handler handler) {
			mHandler = handler;
			
		}

	}
	
	/**
	 * 
	 * 播放器界面更新
	 */
	class UpdatePlayerViewThread extends Thread {
		@Override
		public void run() {
			while (true) {
				if (mMediaPlayer !=null && mMediaPlayer.isPlaying()) {
					Message msg = Message.obtain();
					msg.arg1 = mMediaPlayer.getCurrentPosition();
					msg.arg2 = mMediaPlayer.getDuration();
					
					mHandler.sendMessageDelayed(msg,1000);
					
				}
			}
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
	
		new UpdatePlayerViewThread().start();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mPlayerSerivceiBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
		super.onDestroy();
	}
}
