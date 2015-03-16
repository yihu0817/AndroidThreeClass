package com.scxh.android.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.scxh.android.R;

public class MyWeixinDialog extends AlertDialog {
	private Activity mContext;
	public MyWeixinDialog(Activity context) {
		super(context);
		mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.exit_dialog);
		Button confirm = (Button) findViewById(R.id.exitBtn0);
		Button cancel = (Button) findViewById(R.id.exitBtn1);
		
		confirm.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mContext.finish();
				
			}
		});
		
		cancel.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dismiss();
				
			}
		});
	}
}
