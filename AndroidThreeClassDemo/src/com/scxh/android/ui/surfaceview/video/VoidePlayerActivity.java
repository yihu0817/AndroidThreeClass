package com.scxh.android.ui.surfaceview.video;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;

public class VoidePlayerActivity extends Activity implements Callback,
		MediaPlayer.OnPreparedListener, OnBufferingUpdateListener,
		OnCompletionListener {
	private static final String TAG = "MediaPlayer";
	private TextView mToastTxt;
	private MediaPlayer mMediaPlayer;
	private SurfaceView mSurfaceView;
	private SurfaceHolder mSurfaceHolder;
	private static final String strVideoPath = "/mnt/sdcard/voide/test30m.3gp";
	private static final String mVideoHttpUrl = "http://flv2.bn.netease.com/videolib3/1503/06/KHDxR7409/SD/KHDxR7409-mobile.mp4";

	private int mVideoWidth;
	private int mVideoHeight;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vediou_layout);
		if (!checkSDCard()) {
			Toast.makeText(this, "未安装SD存储卡", Toast.LENGTH_SHORT).show();
		}
		mToastTxt = (TextView) findViewById(R.id.myTextView1);
		mSurfaceView = (SurfaceView) findViewById(R.id.mSurfaceView1);
		
		mSurfaceHolder = mSurfaceView.getHolder();
		mSurfaceHolder.addCallback(this);
		
		
//		playerVideoByIntent();
	}

	private void playVideo() {
		mMediaPlayer = new MediaPlayer();
		try {
			mMediaPlayer.setDataSource(mVideoHttpUrl);
		} catch (Exception e) {
			mToastTxt.setText("setDataSource Exception:" + e.toString());
			e.printStackTrace();
		}
		mMediaPlayer.setDisplay(mSurfaceHolder);
		try {
			mMediaPlayer.prepareAsync();
		} catch (Exception e) {
			mToastTxt.setText("prepare Exceeption:" + e.toString());
			e.printStackTrace();
		}

		mMediaPlayer.setOnBufferingUpdateListener(this); //播放时度监听
		mMediaPlayer.setOnCompletionListener(this);//播放完成监听
		mMediaPlayer.setOnPreparedListener(this);//预处理结束监听
		
		mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.i(TAG, "Surface Changed");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i(TAG, "surface Created");
		playVideo();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.i(TAG, "Surface Destroyed");
		super.onDestroy();
		if (mMediaPlayer != null) {
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		Log.v(TAG, "onPrepared called");
		mVideoWidth = mMediaPlayer.getVideoWidth();
		mVideoHeight = mMediaPlayer.getVideoHeight();
		if (mVideoWidth != 0 && mVideoHeight != 0) {
			/* 设置视频的宽度和高度 */
			mSurfaceHolder.setFixedSize(mVideoWidth, mVideoHeight);
			/* 开始播放 */
			mMediaPlayer.start();
			mToastTxt.setText("播放");
		}
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		mToastTxt.setText("播放进度:" + percent);
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		mToastTxt.setText("播放结束");
	}

	private boolean checkSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 调用系统自带的播放器
	 */
	private void playerVideoByIntent(){
		Uri uri = Uri.parse(mVideoHttpUrl);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "video/mp4");
		startActivity(intent);
	}
}
