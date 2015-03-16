package com.scxh.android.ui;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.asynctask.MyAsyncTaskActivity.MyAsyncTask;
import com.scxh.android.util.Constances;
import com.scxh.android.util.Logs;

public class UIGridViewHttpAct extends Activity implements OnItemClickListener {
	private int[] mImages = { R.drawable.m1, R.drawable.item2,
			R.drawable.item3, R.drawable.m1, R.drawable.item2,
			R.drawable.item3, R.drawable.m1, R.drawable.item2, R.drawable.item3 };

	private String[] mStrings = { "美食1", "美食2", "美食3", "美食4", "美食5", "美食6",
			"美食7", "美食8", "美食9" };

	private GridView mGridView;
	private MyGirdViewAdapter mAdapter;
	
	private Bitmap[] bitmapList = new Bitmap[9];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_gridview_layout);

		mGridView = (GridView) findViewById(R.id.gridview1);

		mAdapter = new MyGirdViewAdapter(this);

		mGridView.setAdapter(mAdapter);

		mGridView.setOnItemClickListener(this);
		
		mAdapter.setData(Constances.imageThumbUrls, mStrings);
		
		//获取数据源Bitmap[]
//		new AsyncTask<String, Void, Bitmap[]>() {
//			@Override
//			protected Bitmap[] doInBackground(String... params) {
//				Bitmap[] bitmaps = new Bitmap[9];
//				for (int i = 0; i < 9; i++) {
//					bitmaps[i] = downLoadBitmap(Constances.imageThumbUrls[i]);
//				}
//				return bitmaps;
//			}
//
//			@Override
//			protected void onPostExecute(Bitmap[] result) {
//				super.onPostExecute(result);
//				bitmapList = result;
//				mAdapter.setData(bitmapList, mStrings);
//			}
//		}.execute();
		

	}

	public class MyGirdViewAdapter extends BaseAdapter {
		private String[] imageArrays = new String[] {};
		private String[] stringArrays = new String[] {};
		private Context context;
		private LayoutInflater inflater;
		private Executor exec = new ThreadPoolExecutor(15,  150, 10,TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

		public MyGirdViewAdapter(Context contexts) {
			context = contexts;
			inflater = LayoutInflater.from(context);
		}

		public void setData(String[] images, String[] strings) {
			imageArrays = images;
			stringArrays = strings;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return imageArrays.length;
		}

		@Override
		public Object getItem(int position) {
			return stringArrays[position];
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			final HolderView holderView;
			if (convertView == null) {
				holderView = new HolderView();
				convertView = inflater.inflate(R.layout.item_gridview_layout,null);
				holderView.iconImageView = (ImageView) convertView.findViewById(R.id.grid_imageview);
				holderView.nameTxt = (TextView) convertView.findViewById(R.id.grid_textview);
				convertView.setTag(holderView);
			} else {
				holderView = (HolderView) convertView.getTag();
			}
			holderView.nameTxt.setText(stringArrays[position]);
//			holderView.iconImageView.setImageResource(imageArrays[position]);
//			holderView.iconImageView.setImageBitmap(imageArrays[position]);
			
			new AsyncTask<String, Void, Bitmap>() {
				@Override
				protected Bitmap doInBackground(String... params) {
					String httpUrl = params[0];
					return downLoadBitmap(httpUrl);
				}

				@Override
				protected void onPostExecute(Bitmap bitmap) {
					super.onPostExecute(bitmap);
					holderView.iconImageView.setImageBitmap(bitmap);
				}
			}.execute(imageArrays[position]);
			
			return convertView;
		}

		class HolderView {
			ImageView iconImageView;
			TextView nameTxt;
		}

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String item = (String) parent.getAdapter().getItem(position);
		Toast.makeText(this, item, Toast.LENGTH_SHORT).show();

	}

	/**
	 * 根据图片址址从网络获取图片
	 * 
	 * @param httpUrl
	 * @return
	 */
	private Bitmap downLoadBitmap(String httpUrl) {
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
