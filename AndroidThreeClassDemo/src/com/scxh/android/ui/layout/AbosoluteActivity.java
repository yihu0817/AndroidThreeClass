package com.scxh.android.ui.layout;

import com.scxh.android.R;
import com.scxh.android.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class AbosoluteActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_absolute_layout);
	}
	
	public void onAbosoluteLayoutClick(View v){
		System.out.println("绝对布局方法被点击》》》》》》");
	}
}
