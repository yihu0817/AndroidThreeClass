package com.scxh.android.ui.bar;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.util.Logs;

public class ProgressBarActivity extends Activity {
	private ProgressBar mProgressBar;
	private SeekBar mSeekBar;
	private Button mDownLoadBtn;
	private Handler mHandler = new Handler();
	private ProgressDialog mProgressDialog;
//	private ButtonRectangle mButtonRectangle;
	private boolean isFlage = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progressbar_layout);

		mProgressBar = (ProgressBar) findViewById(R.id.progressbar1);
		mDownLoadBtn = (Button) findViewById(R.id.downloadBtn);
		mSeekBar = (SeekBar)findViewById(R.id.seekbar1);
//		mButtonRectangle= (ButtonRectangle) findViewById(R.id.button);
		mDownLoadBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				isFlage = true;
//				mProgressDialog.show();
				new DownLoadThread().start();

			}
		});

		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setIndeterminate(false);
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		mProgressDialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				isFlage = false;
				mProgressDialog.dismiss();
			}
		});
		
		
		mSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Logs.v("onStopTrackingTouch");
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Logs.v("onStartTrackingTouch");
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Logs.v("onProgressChanged"+progress);
			}
		});
		
		
//		mButtonRectangle.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				isFlage = true;
//				DownLoadThread downLoadThread = new DownLoadThread();
//				downLoadThread.start();
//				
//			}
//		});

	}

	public class DownLoadThread extends Thread {
		int count = 0;

		@Override
		public void run() {

			while (count < 100 && isFlage) {
				count++;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				mHandler.post(new Runnable() {
					@Override
					public void run() {
						mSeekBar.setProgress(count);    
					}
				});

			}
			if (count == 100) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						Toast.makeText(ProgressBarActivity.this, "下载完成！",Toast.LENGTH_SHORT).show();

					}
				});
			}

		}
	}
}
