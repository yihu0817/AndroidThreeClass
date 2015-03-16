package com.scxh.android.pattern;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.scxh.android.R;

public class MyButton {
	public Context mContext;

	public MyButton(Context context) {
		mContext = context;
	}

	public interface MyOnClickListener {
		public void onclick(Drawable drawable);
	}

	public MyOnClickListener listener;

	public void setOnclickListener(MyOnClickListener l) {
		listener = l;
	}

	public void getDrawableByNet() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Drawable drawable = mContext.getResources().getDrawable(R.drawable.common_btn_green);
				listener.onclick(drawable);
			}
		}).start();

	}
}
