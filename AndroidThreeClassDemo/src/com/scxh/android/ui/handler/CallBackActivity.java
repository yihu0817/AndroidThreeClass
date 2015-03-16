package com.scxh.android.ui.handler;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.scxh.android.R;
import com.scxh.android.ui.handler.MyButtonView.OnCallBackInterface;

public class CallBackActivity extends Activity {
	private TextView mShowTxt;
	private MyButtonView mGetDataBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_callback_text_layout);
		mShowTxt = (TextView) findViewById(R.id.textviewhandler);
		mGetDataBtn = (MyButtonView) findViewById(R.id.getDatabtn);

		mGetDataBtn.setOnCallBackInterface(new OnCallBackInterface() {

			@Override
			public void execute(String data) {
				mShowTxt.setText(data);
			}
		});
		
		mGetDataBtn.registerOnClickListeners();
	}

}
