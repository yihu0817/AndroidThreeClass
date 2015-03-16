package com.scxh.android.ui.tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android.R;

public class FragmentSetting extends Fragment {
	private ListView mListView;
	private TextView mTitleTxt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_setting, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mTitleTxt = (TextView) getView().findViewById(R.id.titleTv);
		mListView = (ListView) getView().findViewById(R.id.listView1);
		mTitleTxt.setText("设置");
		CharSequence[] array = (CharSequence[]) getResources().getTextArray(R.array.arraylist);
		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_list_item_1, array);
		mListView.setAdapter(adapter);
		
	}
}
