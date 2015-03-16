package com.scxh.android.ui.listview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.scxh.android.R;

public class MessageActivity extends Activity {
	private TextView messageTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messagetext_layout);
		
		messageTxt = (TextView) findViewById(R.id.messagetxt);
		
		messageTxt.setText(getIntent().getStringExtra("MESSAGE"));
	}
}
