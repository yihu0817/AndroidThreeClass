package com.scxh.android.ui;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import com.scxh.android.util.AsyncImageLoader;
import com.scxh.android.util.Logs;

public class BitmapGridViewHttpAct extends Activity implements
		OnItemClickListener {
	private GridView mGridView;

	public final static String[] imageThumbUrls = new String[] {
			"http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383291_8239.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383290_9329.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383290_1042.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383275_3977.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383265_8550.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383264_3954.jpg",
			"http://img.my.csdn.net/uploads/201407/26/1406383264_4787.jpg", };

	private int[] mImages = { R.drawable.m1, R.drawable.item2,
			R.drawable.item3, R.drawable.m1, R.drawable.item2,
			R.drawable.item3, R.drawable.m1, R.drawable.item2, R.drawable.item3 };

	private String[] mStrings = { "美食1", "美食2", "美食3", "美食4", "美食5", "美食6",
			"美食7", "美食8", "美食9" };
	private MyGirdViewAdapter mAdapter;

	private AsyncImageLoader mAsyncImageLoader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_gridview_layout);
		mAsyncImageLoader = new AsyncImageLoader();

		mGridView = (GridView) findViewById(R.id.gridview1);

		mAdapter = new MyGirdViewAdapter(this);

		mGridView.setAdapter(mAdapter);

		mAdapter.setData(imageThumbUrls, mStrings);

		mGridView.setOnItemClickListener(this);
	}

	public class MyGirdViewAdapter extends BaseAdapter {
		private String[] imageArrays = new String[] {};
		private String[] stringArrays = new String[] {};
		private Context context;
		private LayoutInflater inflater;
		public Handler mHandler = new Handler();

		ImageView iconImageView;
		TextView nameTxt;

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
			HolderView holderView;
			if (convertView == null) {
				holderView = new HolderView();
				convertView = inflater.inflate(R.layout.item_gridview_layout,
						null);
				holderView.iconImageView = (ImageView) convertView
						.findViewById(R.id.grid_imageview);
				holderView.nameTxt = (TextView) convertView
						.findViewById(R.id.grid_textview);
				convertView.setTag(holderView);
			} else {
				holderView = (HolderView) convertView.getTag();
			}
			holderView.nameTxt.setText(stringArrays[position]);

			loadBitmap(imageArrays[position], holderView.iconImageView);

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
	 * 通常类似 ListView 与 GridView 等视图组件在使用上面演示的AsyncTask 方法时会同时带来另外一个问题。
	 * 为了更有效的处理内存，那些视图的子组件会在用户滑动屏幕时被循环使用。如果每一个子视图都触发一个AsyncTask ，
	 * 那么就无法确保当前视图在结束task时，分配的视图已经进入循环队列中给另外一个子视图进行重用。 而且, 无法确保所有的异步任务能够按顺序执行完毕。
	 * 
	 * @param imageUrl
	 * @param imageView
	 */
	public void loadBitmap(String imageUrl, ImageView imageView) {

		if (cancelPotentialWork(imageUrl, imageView)) {
			BitmapWorkerTask task = new BitmapWorkerTask(imageView);

			AsyncDrawable asyncDrawable = new AsyncDrawable(
					getResources(),
					BitmapFactory.decodeResource(getResources(), R.drawable.m1),
					task);
			imageView.setImageDrawable(asyncDrawable);

			task.execute(imageUrl);
		}

	}

	
	public static boolean cancelPotentialWork(String imageUrl, ImageView imageView) {
	    final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

	    if (bitmapWorkerTask != null) {
	        final String bitmapData = bitmapWorkerTask.data;
	        if (!bitmapData.equals(imageUrl)) {
	            // Cancel previous task
	            bitmapWorkerTask.cancel(true);
	        } else {
	            // The same work is already in progress
	            return false;
	        }
	    }
	    // No task associated with the ImageView, or an existing task was cancelled
	    return true;
	}
	
	private static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
		   if (imageView != null) {
		       final Drawable drawable = imageView.getDrawable();
		       if (drawable instanceof AsyncDrawable) {
		           final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
		           return asyncDrawable.getBitmapWorkerTask();
		       }
		    }
		    return null;
	}
	/**
	 * 创建一个专用的Drawable的子类来储存返回工作任务的引用。在这种情况下，当任务完成时BitmapDrawable会被使用
	 *
	 */
	static class AsyncDrawable extends BitmapDrawable {
	    private final WeakReference bitmapWorkerTaskReference;

	    public AsyncDrawable(Resources res, Bitmap bitmap,BitmapWorkerTask bitmapWorkerTask) {
	        super(res, bitmap);
	        bitmapWorkerTaskReference = new WeakReference(bitmapWorkerTask);
	    }

	    public BitmapWorkerTask getBitmapWorkerTask() {
	        return (BitmapWorkerTask) bitmapWorkerTaskReference.get();
	    }
	}
	
	class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
		private final WeakReference imageViewReference;
		private String data = "";

		public BitmapWorkerTask(ImageView imageView) {
			// Use a WeakReference to ensure the ImageView can be garbage collected
			imageViewReference = new WeakReference(imageView);
		}

		// Decode image in background.
		@Override
		protected Bitmap doInBackground(String... params) {
			data = params[0];
			return decodeSampledBitmapFromStream(getResources(), data, 50, 50);
		}

		// Once complete, see if ImageView is still around and set bitmap.
		@Override
		protected void onPostExecute(Bitmap bitmap) {
			if (isCancelled()) {
				bitmap = null;
			}

			if (imageViewReference != null && bitmap != null) {
				final ImageView imageView = (ImageView) imageViewReference.get();
				final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);
				if (this == bitmapWorkerTask && imageView != null) {
					imageView.setImageBitmap(bitmap);
				}
			}
		}
	}
	public static Bitmap decodeSampledBitmapFromResource(Resources res,
			int resId, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth,
				reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}

	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		Logs.v("calculateInSampleSize  height :" + height + " width :" + width);
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {

			final int halfHeight = height / 2;
			final int halfWidth = width / 2;

			// Calculate the largest inSampleSize value that is a power of 2 and
			// keeps both
			// height and width larger than the requested height and width.
			while ((halfHeight / inSampleSize) > reqHeight
					&& (halfWidth / inSampleSize) > reqWidth) {
				inSampleSize *= 2;
			}
		}

		return inSampleSize;
	}

	public static Bitmap decodeSampledBitmapFromStream(Resources res,
			String imageUrl, int reqWidth, int reqHeight) {

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;

		HttpURLConnection con = null;
		InputStream is = null;
		try {
			URL url = new URL(imageUrl);
			con = (HttpURLConnection) url.openConnection();
			is = con.getInputStream();
			BitmapFactory.decodeStream(is, null, options);

			// Calculate inSampleSize
			options.inSampleSize = calculateInSampleSize(options, reqWidth,reqHeight);
			Logs.v("options.inSampleSize  :" + options.inSampleSize);
			// Decode bitmap with inSampleSize set
			options.inJustDecodeBounds = false;

			con.disconnect();
			is.close();

			con = (HttpURLConnection) url.openConnection();
			is = con.getInputStream();

			return BitmapFactory.decodeStream(is, null, options);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
					is = null;
				}
				if (con != null) {
					con.disconnect();
					con = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
