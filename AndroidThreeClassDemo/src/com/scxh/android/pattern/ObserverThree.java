package com.scxh.android.pattern;

import android.util.Log;

public class ObserverThree implements Observer {

	@Override
	public void update(String blog) {
		Log.v("TAG", "ObserverThree :" + blog);

	}
}
