package com.scxh.android.mp3;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Environment;

public class PlayManager {
	private String[] mPlayStatus = { "end", "top", "play" }; // 播放状态
	private List<String> mSongs = new ArrayList<String>(); //音乐名
	private HashMap<String, String> mSongMaps = new HashMap<String, String>(); //音乐路径   key:musicName; values:/sdcard/music/xx.mp3
	private MediaPlayer mMediaPlayer = null; // 播放工具类，系统类
	private int mPlayPostition = 0; // 当前播放文件的位置，可用于暂停和继续播放的
	private int mCurrentPosition = -1; // 当前播放的是第几个文件
	
	private static PlayManager sPlayManager = null; // 单例
	public static PlayManager getInstance() {
		if (sPlayManager == null) {
			sPlayManager = new PlayManager();
		}
		return sPlayManager;
	}

	private PlayManager() {
		File rootFile = Environment.getExternalStorageDirectory();
		String path = rootFile.getAbsolutePath();
		findNewSongs(path);
	}

	public void findNewSongs(String mediaPath) {
		File home = new File(mediaPath);
		if (home.listFiles() == null) {
			return;
		}
		if (home.listFiles().length > 0) {
			for (File file : home.listFiles()) {
				if (file.isDirectory()) {
					findNewSongs(file.getAbsolutePath() + "/");
				} else {
					if (file.getName().endsWith("mp3")) {
						mSongs.add(file.getName());
						mSongMaps.put(file.getName(), file.getAbsolutePath());
					}
				}
			}
		}
	}

	public String startPlayVideo() throws Exception { // 开始播放
		mCurrentPosition = 0; // 因为currentFilePosition的初始值是-1，所以此处强制赋值为0，即播放第一个音频文件，返回播放状态
		return playMusic();
	}

	private String playMusic() throws Exception {
		if (mCurrentPosition >= mSongs.size()) { // 首先判断当前播放的文件是否超多了列表
			return mPlayStatus[0]; // 返回到低了，你也可以直接写成currentFilePosition=0，这样就能循环播放列表了
		}
		if (mCurrentPosition < 0) {
			return mPlayStatus[1]; // 返回到顶了，你也可以写成currentFilePosition=fileArray.length，这样反过来循环播放列表
		}
		releaseMedia(); // 每次开始播放列表时，都要将mediaPlayer释放掉，这样一边准备下一首或者上一首
		
		mMediaPlayer = new MediaPlayer();
		mMediaPlayer.setDataSource(mSongMaps.get(mSongs.get(mCurrentPosition))); // 设置播放文件的路径

		mMediaPlayer.prepare(); // 准备
		mMediaPlayer.start(); // 开始播放
		
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				try {
					playNextMusic(); // 在每次播放完成之后都用播放下一首

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return mPlayStatus[2]; // 返回正在播放
	}

	public String playNextMusic() throws Exception {
		mCurrentPosition++; // 播放下一首时当前播放文件加1
		return playMusic();
	}

	public String playBackMusic() throws Exception {
		mCurrentPosition--; // 减1，播放上一首
		return playMusic();
	}

	public void pausePlayMusic() { // 暂停
		if (mMediaPlayer != null) {
			mPlayPostition = mMediaPlayer.getCurrentPosition(); // 得到当前播放的位置
			mMediaPlayer.pause();
		}
	}

	public void continuePlay() { // 继续
		if (mMediaPlayer != null) {
			mMediaPlayer.seekTo(mPlayPostition); // 从记录的位置开始播放
			mMediaPlayer.start();
		}
	}

	public void stopPlay() {
		if (mMediaPlayer != null) { // 停止播放器
			mMediaPlayer.stop();
		}
	}

	public void releaseMedia() { // 这里是释放mediaPlayer播放对象
		if (mMediaPlayer != null) {
			try {
				mMediaPlayer.release();
				mMediaPlayer = null;
				System.gc();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getDuration(){
		
		return mMediaPlayer.getDuration();
	}
	
	public int getCurrentPosition(){
		return mMediaPlayer.getCurrentPosition();
	}

}
