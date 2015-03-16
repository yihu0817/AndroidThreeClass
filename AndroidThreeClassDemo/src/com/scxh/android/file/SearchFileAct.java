package com.scxh.android.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;

public class SearchFileAct extends Activity implements Button.OnClickListener,OnItemClickListener {
	private static final int WHAT_SEARCH_FILE_OVER = 0;
	private static final int WHAT_SEARCH_FILEING = 1;
	private String mInputKeyWord;
	private EditText mSearchKeyEdit;
	private Button mSearchBtn;
	private ListView mListView;
	// ==============播放器控件===================
	private TextView mTitleMusicTxt;
	private TextView mCurrentTimeTxt;
	private TextView mTotalTimeTxt;
	private SeekBar mSeekBar;
	private ImageButton mPlayBtn, mNextBtn, mPrecBtn;
	private MediaPlayer mMediaPlayer = new MediaPlayer();
	private boolean isPlayering = false;
	// ==============播放器控件===================
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			int what = msg.what;
			switch(what){
			case WHAT_SEARCH_FILE_OVER:
				mAdapter.setData(mList);// 添加搜索到的数据源mList,到适配器 并刷新ListView
				getActionBar().setTitle(getString(R.string.search_music_file_end));
				mTitleMusicTxt.setText(getString(R.string.search_music_file_end));
				break;
			case WHAT_SEARCH_FILEING:
				String path = (String) msg.obj;
				mTitleMusicTxt.setText(path);
				break;
			}
			
		};
	};

	private int mCurrentPosition;

	private ArrayList<SongFileBean> mList = new ArrayList<SongFileBean>();
	private MyBaseAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_search_act);
		
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
		mSearchKeyEdit = (EditText) findViewById(R.id.input_KEY_EditText);
		mSearchBtn = (Button) findViewById(R.id.searchBtn);
		mListView = (ListView) findViewById(R.id.resultList);

		mTitleMusicTxt = (TextView) findViewById(R.id.musicTitle);
		mCurrentTimeTxt = (TextView) findViewById(R.id.currentTime);
		mTotalTimeTxt = (TextView) findViewById(R.id.totalTime);
		mSeekBar = (SeekBar) findViewById(R.id.playSeekBar);
		mPlayBtn = (ImageButton) findViewById(R.id.playImageBtn);
		mNextBtn = (ImageButton) findViewById(R.id.nextImageBtn);
		mPrecBtn = (ImageButton) findViewById(R.id.precImageBtn);

		mPlayBtn.setOnClickListener(this);
		mNextBtn.setOnClickListener(this);
		mPrecBtn.setOnClickListener(this);
		mListView.setOnItemClickListener(this);
		mSearchBtn.setOnClickListener(this);

		mAdapter = new MyBaseAdapter(this);
		mListView.setAdapter(mAdapter);

		UpdatePlayerViewThread mPlayerThread = new UpdatePlayerViewThread();
		mPlayerThread.start();
		
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			private int mSec;
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				mMediaPlayer.seekTo(mSec);
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
				mSec = progress;
			}
		});
		
		mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				if(mList.size() == 0)
					return;
				if (++mCurrentPosition > (mList.size() - 1)) {
					mCurrentPosition = 0;
				}
				SongFileBean songs = (SongFileBean) mList.get(mCurrentPosition);
				playMusicView(songs);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		mCurrentPosition = position;

		MyBaseAdapter adapter = (MyBaseAdapter) parent.getAdapter();
		SongFileBean song = (SongFileBean) adapter.getItem(position);

		playMusicView(song);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchBtn:
			onSearchClick();
			break;
		case R.id.playImageBtn:
			if (isPlayering) {
				isPlayering = false;
				mPlayBtn.setImageResource(R.drawable.player_play);

				mMediaPlayer.pause();
			} else {
				isPlayering = true;
				mPlayBtn.setImageResource(R.drawable.player_pause);

				mMediaPlayer.start();
			}

			break;
		case R.id.nextImageBtn:

			if (++mCurrentPosition > (mList.size() - 1)) {
				mCurrentPosition = 0;
			}

			SongFileBean songs = (SongFileBean) mList.get(mCurrentPosition);
			playMusicView(songs);

			break;
		case R.id.precImageBtn:

			if (--mCurrentPosition < 0) {
				mCurrentPosition = mList.size() - 1;
			}
			SongFileBean song = (SongFileBean) mList.get(mCurrentPosition);
			playMusicView(song);
			break;
		}

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(mMediaPlayer != null){
			mMediaPlayer.release();
			mMediaPlayer = null;
		}
	}
	private void playMusicView(SongFileBean songs) {
		getActionBar().setTitle(songs.getFileName());
		mTitleMusicTxt.setText(songs.getFileName());
		isPlayering = true;
		mPlayBtn.setImageResource(R.drawable.player_pause);

		playMusic(songs.getFilePath());
	}

	/**
	 * 播放音乐
	 * 
	 * @param path
	 */
	private void playMusic(String path) {
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

	private void onSearchClick() {
		mInputKeyWord = mSearchKeyEdit.getText().toString();

		if (mInputKeyWord.equals("")) {
			Toast.makeText(this, "如果输入框没有输入点击搜索按钮，提示输入", Toast.LENGTH_SHORT).show();
		} else {
			mList.clear();
			
			File rootFile = Environment.getExternalStorageDirectory();
			
			getActionBar().setTitle(getString(R.string.search_music_file_start));
		
			
			new SearchFileThread(rootFile).start();
			
		}
	}


	class MyBaseAdapter extends BaseAdapter {
		private List<SongFileBean> mList = new ArrayList<SongFileBean>();

		private Context mContext;
		private LayoutInflater inflater;

		public MyBaseAdapter(Context context) {
			mContext = context;
			inflater = LayoutInflater.from(mContext);
		}

		public MyBaseAdapter(Context context, List<SongFileBean> list) {
			mContext = context;
			mList = list;
			inflater = LayoutInflater.from(mContext);
		}

		public void setData(List<SongFileBean> list) {
			mList = list;
			notifyDataSetChanged();
		}

		/**
		 * 数据源记录条数,ListView项数
		 */
		@Override
		public int getCount() {
			return mList.size();
		}

		/**
		 * 获取数据源指定位置(position)的数据项对象
		 */
		@Override
		public Object getItem(int position) {
			return mList.get(position);
		}

		/**
		 * 获取指定位置ID
		 */
		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder;

			if (convertView == null) {
				// ====一级优化==缓存View=======
				convertView = inflater.inflate(R.layout.item_baseadapter_view,
						parent, false);

				// ====二级优化===缓存View对象里的控件=====
				holder = new ViewHolder(); // 因为View setTag方法只能放一个Object,
				holder.titleTxt = (TextView) convertView
						.findViewById(R.id.titletextView);
				holder.infoTxt = (TextView) convertView
						.findViewById(R.id.infotextView);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			// ==========取数据项给View对应控件赋值=============
			SongFileBean msg = (SongFileBean) getItem(position);

			holder.titleTxt.setText(msg.getFileName());
			holder.infoTxt.setText(msg.getFilePath());

			holder.titleTxt.setTextColor(getResources().getColor(
					android.R.color.white));
			holder.infoTxt.setTextColor(getResources().getColor(
					android.R.color.white));
			// ===========================================
			return convertView;
		}

		class ViewHolder {
			ImageView imageView = null;
			TextView titleTxt = null;
			TextView infoTxt = null;
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
				if (mMediaPlayer !=null && isPlayering) {
					mHandler.post(new Runnable() {

						@Override
						public void run() {
							mSeekBar.setMax(mMediaPlayer.getDuration());
							mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());

							int pos = 0;

							pos = mMediaPlayer.getCurrentPosition();
							int min = (pos / 1000) / 60;
							int sec = (pos / 1000) % 60;

							int maxPos = mMediaPlayer.getDuration();
							int maxMin = (maxPos / 1000) / 60;
							int maxSec = (maxPos / 1000) % 60;

							if (maxSec < 10) {
								mTotalTimeTxt.setText("" + maxMin + ":0"+ maxSec);
							} else {
								mTotalTimeTxt.setText("" + maxMin + ":"+ maxSec);
							}

							if (sec < 10) {
								mCurrentTimeTxt.setText("" + min + ":0" + sec);
							} else {
								mCurrentTimeTxt.setText("" + min + ":" + sec);
							}
						}
					});
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	class SearchFileThread extends Thread{
		private File rootFile;
		public SearchFileThread(File file){
			rootFile = file;
		}
		@Override
		public void run() {
			getFileLists(rootFile);
			
			mHandler.sendEmptyMessage(WHAT_SEARCH_FILE_OVER);
		}
	}
	
	/**
	 * 搜索指定文件目录下文件，得到数据源
	 * 
	 * @param files
	 */
	public void getFileLists(File files) {
		File[] fileList = files.listFiles();

		if (null != fileList) {

			for (File file : fileList) {
				// 判断文件是否是目录
				if (file.isDirectory()) {

					getFileLists(file);

				} else { // 文件操作
					try {

						/* 是文件，进行比较，如果文件名称中包含输入搜索Key 就显示 */
						if (file.getName().endsWith(mInputKeyWord)) {

							SongFileBean songFileBean = new SongFileBean();
							songFileBean.setFileName(file.getName());
							songFileBean.setFilePath(file.getPath());

							mList.add(songFileBean);
							
							Message msg = Message.obtain();
							msg.what = WHAT_SEARCH_FILEING;
							msg.obj = file.getPath();
							mHandler.sendMessage(msg);
						}
					} catch (Exception e) {
						Toast.makeText(this, "出错", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
	}

}