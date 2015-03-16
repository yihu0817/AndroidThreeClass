package com.scxh.android.service;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.service.PlayerService.IPlayerSerivice;
import com.scxh.android.util.Constances;

public class PlayerUIActivity extends Activity implements
		Button.OnClickListener {
	private static final String MUSIC_PATH = "/mnt/sdcard/music/dzw.mp3";
	private TextView mCurrentTimeTxt;
	private TextView mTotalTimeTxt;
	private SeekBar mSeekBar;
	private ImageButton mPlayBtn, mNextBtn, mPrecBtn;
	private boolean isPlayering = false;
	private IPlayerSerivice mIPlayerSerivice;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			int pos = msg.arg1; // mMediaPlayer.getCurrentPostion()当前时间
			int maxPos = msg.arg2;// mMediaPlayer.getDuration(); 总时间

			mSeekBar.setMax(maxPos);
			mSeekBar.setProgress(pos);

			int min = (pos / 1000) / 60;
			int sec = (pos / 1000) % 60;

			int maxMin = (maxPos / 1000) / 60;
			int maxSec = (maxPos / 1000) % 60;

			if (maxSec < 10) {
				mTotalTimeTxt.setText("" + maxMin + ":0" + maxSec);
			} else {
				mTotalTimeTxt.setText("" + maxMin + ":" + maxSec);
			}

			if (sec < 10) {
				mCurrentTimeTxt.setText("" + min + ":0" + sec);
			} else {
				mCurrentTimeTxt.setText("" + min + ":" + sec);
			}

		};
	};

	public ServiceConnection mPlayerConnectService = new ServiceConnection() {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mIPlayerSerivice = (IPlayerSerivice) service;

			mIPlayerSerivice.setHandler(mHandler);
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			mIPlayerSerivice = null;
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_ui_act);

		mCurrentTimeTxt = (TextView) findViewById(R.id.currentTime);
		mTotalTimeTxt = (TextView) findViewById(R.id.totalTime);
		mSeekBar = (SeekBar) findViewById(R.id.playSeekBar);
		mPlayBtn = (ImageButton) findViewById(R.id.playImageBtn);
		mNextBtn = (ImageButton) findViewById(R.id.nextImageBtn);
		mPrecBtn = (ImageButton) findViewById(R.id.precImageBtn);

		mPlayBtn.setOnClickListener(this);
		mNextBtn.setOnClickListener(this);
		mPrecBtn.setOnClickListener(this);

		Intent intent = new Intent(this, PlayerService.class);
		startService(intent);
		bindService(intent, mPlayerConnectService, BIND_AUTO_CREATE);

	}

	@Override
	protected void onResume() {
		super.onResume();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Constances.ACTION_PLAYER);
		registerReceiver(mPlayBroadcastReceiver, filter);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.playImageBtn:
			if (isPlayering) {
				isPlayering = false;
				mPlayBtn.setImageResource(R.drawable.player_play);
				mIPlayerSerivice.pauseMusic();

				Intent intent = new Intent();
				intent.setAction(Constances.ACTION_STOP);
				sendBroadcast(intent);
			} else {
				isPlayering = true;
				mPlayBtn.setImageResource(R.drawable.player_pause);
				mIPlayerSerivice.playMusic(MUSIC_PATH);

				Intent intent = new Intent();
				intent.setAction(Constances.ACTION_PLAYER);
				sendBroadcast(intent);
			}

			break;
		}

	}

	public BroadcastReceiver mPlayBroadcastReceiver = new BroadcastReceiver() {
		public void onReceive(android.content.Context context, Intent intent) {
			Toast.makeText(context, "动态广播 消息已收到 播放音乐开始", Toast.LENGTH_SHORT)
					.show();
		};
	};

	@Override
	protected void onPause() {
		super.onPause();
		if (mPlayBroadcastReceiver != null) {
			unregisterReceiver(mPlayBroadcastReceiver);
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPlayerConnectService != null) {
			unbindService(mPlayerConnectService);
		}

	}

}