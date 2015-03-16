package com.scxh.android.ui;

import java.lang.reflect.Method;

import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.bean.User;
import com.scxh.android.bean.UserBean;

public class ViewTextActivity extends Activity {
	private String msg = "新浪网新闻中心是新浪网最重要的频道之一,24小时滚动报道国内、国际及社会新闻。";
	private Button mConfirmBtn;
	private TextView textView, textView1, user1Txt, user2Txt;
	private ImageView headerImg;
	private CustomGifView mCustomGifView;
	private Movie mMovie;
	private long mMovieStart = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_textview_layout);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		textView = (TextView) findViewById(R.id.newstxt);
		textView1 = (TextView) findViewById(R.id.newsTwotxt);
		user1Txt = (TextView) findViewById(R.id.user1Txt);
		user2Txt = (TextView) findViewById(R.id.user2Txt);
		headerImg = (ImageView) findViewById(R.id.header1Img);
		mConfirmBtn = (Button) findViewById(R.id.confirm_btn);

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();

		Log.v("tag", "bundle :" + bundle);
		if (bundle == null) {
			textView.setText(getString(R.string.text_bundle));
		} else {

			String message = bundle.getString("MESSAGE1");
			UserBean user3 = (UserBean) bundle
					.getParcelable("MESSAGEParcelable");

			Bitmap headerBitmap = (Bitmap) user3.getHeadIcon();

			User user2 = (User) intent
					.getSerializableExtra("MESSAGESerializable2");
			String message2 = intent.getStringExtra("MESSAGE2");

			textView.setText(message);
			textView1.setText(message2);
			headerImg.setImageBitmap(headerBitmap);

			user1Txt.setText(user3.getName());
			user2Txt.setText(user2.getName());
		}

		mConfirmBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String backStr = "我是回传值!";
				Intent intent = getIntent();
				intent.putExtra("BACKEXTRA", backStr);

				int resultCode = 1;
				setResult(resultCode, intent);

				finish();

			}
		});

		mCustomGifView = new CustomGifView(this);

		addContentView(mCustomGifView, new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_options, menu);
		MenuItem item = menu.findItem(R.id.edit);
//		android.view.ActionProvider sprovider = item.getActionProvider();
//		sprovider.setShareIntent(getShareIntent());
		
		return true;
	}
	/**
	 * 隐式调用
	 * @return
	 */
	private Intent getShareIntent(){
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_SEND);
		intent.setType("image/*");
		return intent;
	}
	
	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
			if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
				try {
					Method m = menu.getClass().getDeclaredMethod(
							"setOptionalIconsVisible", Boolean.TYPE);
					m.setAccessible(true);
					m.invoke(menu, true);
				} catch (Exception e) {
				}
			}
		}
		return super.onMenuOpened(featureId, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_action:
			Toast.makeText(this, "新建", Toast.LENGTH_SHORT).show();
			return true;
		case R.id.quit:
			Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
			finish();
			return true;
		case android.R.id.home:
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
				TaskStackBuilder.create(this)
						.addNextIntentWithParentStack(upIntent)
						.startActivities();
			} else {
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;
		}
		return false;
	}

	class CustomGifView extends View {
		public CustomGifView(Context context) {
			super(context);
			mMovie = Movie.decodeStream(getResources().openRawResource(
					R.drawable.m));
		}

		public void onDraw(Canvas canvas) {
			long now = android.os.SystemClock.uptimeMillis();

			if (mMovieStart == 0) { // first time
				mMovieStart = now;
			}
			if (mMovie != null) {

				int dur = mMovie.duration();
				if (dur == 0) {
					dur = 1000;
				}
				int relTime = (int) ((now - mMovieStart) % dur);
				mMovie.setTime(relTime);
				mMovie.draw(canvas, 0, 0);
				invalidate();
			}
		}
	}
}
