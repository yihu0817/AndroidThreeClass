package com.scxh.android.httpimage;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.scxh.android.R;

public class HttpImageBitmapActivity extends Activity {
	private Button mGetBitmapBtn, mGetBitmapAsynckTackBtn;
	private ImageView mImageView, mAysnckTaskImg;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Bitmap bitmap = (Bitmap) msg.obj;
			mImageView.setImageBitmap(bitmap);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_httpimage_layout);

		mGetBitmapBtn = (Button) findViewById(R.id.getHttpBitmapBtn);
		mGetBitmapAsynckTackBtn = (Button) findViewById(R.id.getHttpBitmapAsynctasBtn);
		mImageView = (ImageView) findViewById(R.id.httpimageView);
		mAysnckTaskImg = (ImageView) findViewById(R.id.httpimageViewAsynctask);

		mGetBitmapBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				getBitmapByThread("http://d.hiphotos.baidu.com/image/h%3D360/sign=ba3bf5f839c79f3d90e1e2368aa1cdbc/f636afc379310a55c01f6ef3b54543a9822610f9.jpg");

			}
		});

		mGetBitmapAsynckTackBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				getBitmapByAsyncTask("http://img4.imgtn.bdimg.com/it/u=3584899235,2312422629&fm=23&gp=0.jpg");
			}
		});
	}

	private void getBitmapByThread(final String httpUrls) {
		new Thread() {
			@Override
			public void run() {
				URL url;
				try {
					url = new URL(httpUrls);
					HttpURLConnection connect = (HttpURLConnection) url
							.openConnection();
					InputStream is = connect.getInputStream();
					Bitmap bitmap = BitmapFactory.decodeStream(is);

					Message msg = Message.obtain();
					msg.obj = bitmap;
					mHandler.sendMessage(msg);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	private void getBitmapByAsyncTask(String httpUrl) {
		new AsyncTask<String, Void, Bitmap>() {

			@Override
			protected Bitmap doInBackground(String... params) {
				String httpurl = params[0];
				return DownLoadBitmap(httpurl);
			}

			protected void onPostExecute(Bitmap result) {
				if (result != null) {
					mAysnckTaskImg.setImageBitmap(result);
				} else {
					Toast.makeText(HttpImageBitmapActivity.this, "网络取图片出错!",Toast.LENGTH_SHORT).show();
				}
			};

		}.execute(httpUrl);
	}

	/**
	 * 根据图片址址从网络获取图片
	 * 
	 * @param httpUrl
	 * @return
	 */
	private Bitmap DownLoadBitmap(String httpUrl) {
		URL url;
		Bitmap bitmap = null;
		HttpURLConnection connect = null;
		InputStream is = null;
		try {
			url = new URL(httpUrl);
			connect = (HttpURLConnection) url.openConnection();
			is = connect.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (connect != null) {
					connect.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bitmap;
	}
}
