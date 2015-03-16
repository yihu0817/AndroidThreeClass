package com.scxh.android.asynctask;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.util.Logs;

public class MyAsyncTaskActivity extends Activity implements OnClickListener{
	private Button mStartDownloadBtn;
	private SeekBar mSeekBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aysnctask_layout);
		
		mStartDownloadBtn = (Button)findViewById(R.id.startbutton);
		mSeekBar = (SeekBar)findViewById(R.id.downloadseekBar);
		mSeekBar.setMax(100);
		
		mStartDownloadBtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		MyAsyncTask mMyAsyncTask = new MyAsyncTask();
		mMyAsyncTask.execute("http://www.scxh.download/file1.jpg","http://www.baidu.download/file2.mp3");
		
		int CPU_COUNT = Runtime.getRuntime().availableProcessors();
		Logs.v("tag CPU_COUNT  :"+CPU_COUNT);
		
		Executor exec = new ThreadPoolExecutor(15,  150, 10,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		new MyAsyncTask().executeOnExecutor(exec,""+(++k));
		
		
//		new AsyncTask<Void, Void, Boolean>(){
//
//			@Override
//			protected Boolean doInBackground(Void... params) {
//				//耗时操作
//				return true;
//			}
//			@Override
//			protected void onPostExecute(Boolean result) {
//				super.onPostExecute(result);
//				if(result == true){
//					//成功
//				}else{
//					//失败
//				}
//			}
//		}.execute();
	}
	int k = 0;
	public class MyAsyncTask extends AsyncTask<String, Integer, String>{
		int count = 0;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Toast.makeText(MyAsyncTaskActivity.this, "开始下载"+k, Toast.LENGTH_SHORT).show();
			mStartDownloadBtn.setText("开始下载");
		}
		@Override
		protected String doInBackground(String... params) {
			String k = params[0];
			while (count < 100) {
				count++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				publishProgress(count);
			}
			return "下载成功!"+k;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			super.onProgressUpdate(values);
			int progress = values[0];
			mSeekBar.setProgress(progress);    
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			Toast.makeText(MyAsyncTaskActivity.this, result, Toast.LENGTH_SHORT).show();
			mStartDownloadBtn.setText(result);
		}
		
	}
}
