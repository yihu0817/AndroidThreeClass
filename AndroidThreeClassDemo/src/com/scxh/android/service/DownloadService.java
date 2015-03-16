package com.scxh.android.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.scxh.android.R;
import com.scxh.android.aidl.IDownloadService;
import com.scxh.android.bean.UserBean;
import com.scxh.android.util.Logs;

public class DownloadService extends Service {
	private ServiceBinder mServiceBinder = new ServiceBinder();
	private MediaPlayer mMediaPlayer;
	private boolean isPause = false;

	@Override
	public IBinder onBind(Intent intent) {
		return mServiceBinder;
	}

	public class ServiceBinder extends IDownloadService.Stub {

		@Override
		public void download(String path) throws RemoteException {
			Logs.v("下载文件  :" + path);
		}

		@Override
		public void playMusic(String path) throws RemoteException {
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

		}

		@Override
		public void pauseMusic() throws RemoteException {
			if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
				mMediaPlayer.pause();
				isPause = true;
			}

		}

		@Override
		public void setUser(UserBean user) throws RemoteException {
			Logs.v("用户名 :"+user.getName());
			
		}

		@Override
		public UserBean getUser() throws RemoteException {
			UserBean user = new UserBean();
			user.setName("android 学习");
			user.setHeadIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
			return user;
		}

		@Override
		public List<String> getList() throws RemoteException {
			ArrayList list = new ArrayList();
			list.add("service");
			list.add("activity");
			return list;
		}

	}
}
