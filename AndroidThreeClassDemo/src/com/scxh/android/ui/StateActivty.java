package com.scxh.android.ui;

import com.scxh.android.util.Constances;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class StateActivty extends Activity {
	TextView textView;
	String stateValue = "状态保存前的值";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v(Constances.tag, "StateActivty onCreate >>>>>>");
		
		
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		textView = new TextView(this);
		textView.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		textView.setText(stateValue);
		textView.setTextSize(24);
		textView.setGravity(Gravity.CENTER);

		linearLayout.addView(textView);

		setContentView(linearLayout);

	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.v(Constances.tag, "StateActivty onSaveInstanceState >>>>>>");
		outState.putString("stateKey", "状态保存后的值");
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.v(Constances.tag, "StateActivty onRestoreInstanceState >>>>>>");
		if(savedInstanceState !=null){
			String stateValue = savedInstanceState.getString("stateKey");
			textView.setText(stateValue);
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.v(Constances.tag, "StateActivty onDestroy >>>>>>");
	}
}
