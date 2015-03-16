package com.scxh.android.fragement;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFramgets extends Fragment {
	TextView mMessageTxt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(android.R.layout.simple_list_item_1,
				container, false);
		mMessageTxt = (TextView)view;
		mMessageTxt.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
		mMessageTxt.setText("内容");
		return view;
	}
	
	public void setMessageToTextView(String message){
		mMessageTxt.setText(message);
	}
}
