package com.scxh.android.fragement.communicate.two;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.scxh.android.util.Logs;

public class ReceiverFragment extends Fragment {
	private TextView mMessageTxt;
	private String msg = "接收数据";
	
	/**
	 * 实例化Fragment
	 * @param message
	 */
	public static Fragment newInstance(String message){
		Fragment receiverFragment = new ReceiverFragment();
		Bundle bundle = new Bundle();
		bundle.putString("MESSAGE", message);
		receiverFragment.setArguments(bundle);
		return receiverFragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(android.R.layout.simple_list_item_1,
				container, false);

		Bundle bundle = getArguments();
		if(bundle != null){
			msg = bundle.getString("MESSAGE");
		}
		Logs.v("msg :" + msg);
		
		mMessageTxt = (TextView) v;
		mMessageTxt.setText(msg);
		return v;
	}

}
