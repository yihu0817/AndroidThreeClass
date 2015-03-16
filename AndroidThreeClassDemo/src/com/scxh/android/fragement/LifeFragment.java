package com.scxh.android.fragement;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scxh.android.R;
import com.scxh.android.util.Logs;

public class LifeFragment extends Fragment {
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Logs.v("onAttach >>>>");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Logs.v("onCreate >>>>");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Logs.v("onCreateView >>>>");
		View v = inflater.inflate(R.layout.activity_radio_layout, container,
				false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		Logs.v("onActivityCreated >>>>");
		super.onActivityCreated(savedInstanceState);
	}
	
	@Override
	public void onStart() {
		Logs.v("onStart >>>>");
		super.onStart();
	}
	@Override
	public void onResume() {
		Logs.v("onResume >>>>");
		super.onResume();
	}
	@Override
	public void onPause() {
		Logs.v("onPause >>>>");
		super.onPause();
	}
	@Override
	public void onStop() {
		Logs.v("onStop >>>>");
		super.onStop();
	}
	@Override
	public void onDestroyView() {
		Logs.v("onDestroyView >>>>");
		super.onDestroyView();
	}
	@Override
	public void onDestroy() {
		Logs.v("onDestroy >>>>");
		super.onDestroy();
	}
	@Override
	public void onDetach() {
		Logs.v("onDetach >>>>");
		super.onDetach();
	}
}
