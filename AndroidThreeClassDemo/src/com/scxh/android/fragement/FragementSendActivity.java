package com.scxh.android.fragement;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.scxh.android.R;

public class FragementSendActivity extends Activity {
	private EditText mMessageEdit;
	private Button mSendBtn;
	ReceiverFragment receiverFragment;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_fragment_send_layout);

		mMessageEdit = (EditText) findViewById(R.id.sendEdit);
		mSendBtn = (Button) findViewById(R.id.sendButton);

		mSendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String message = mMessageEdit.getText().toString();
				
				receiverFragment = (ReceiverFragment) ReceiverFragment.newInstance(message);
				//setArguments()方法给该Fragment对象提供构建参数。它只。在Fragment对象被绑定到它Activity对象之前被调用，
				//也就是说在构建该Fragment对象之后，应该立即调用。该方法提供的参数会在Fragment对象销毁和创建期间被保留。
				
				getFragmentManager().beginTransaction().add(R.id.linerlayout, receiverFragment).commit();
			}
		});

	}
}
