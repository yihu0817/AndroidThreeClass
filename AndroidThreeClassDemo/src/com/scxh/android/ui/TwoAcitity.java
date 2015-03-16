package com.scxh.android.ui;

import com.scxh.android.R;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TwoAcitity extends Activity {
	private Button mButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intent_layout);
		mButton = (Button) findViewById(R.id.liulqBtn);
		mButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(TwoAcitity.this,IntentActivity.class));
			}
		});
	}
}
