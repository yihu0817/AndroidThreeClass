package com.scxh.android.fragement.communicate.one;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.scxh.android.R;

public class ListFragments extends Fragment implements OnItemClickListener{
	private ListView mListView;
	private ProgressBar mProgressBar;
	private String[] mLists = new String[] { "android", "java", "ui", "淘宝","html","js","cs","tomcat" };

	private OnHeadlineSelectedListener mOnHeadlineSelectedListener;
	
	public interface OnHeadlineSelectedListener{
		public void onSelectItemClick(String message);
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mOnHeadlineSelectedListener = (OnHeadlineSelectedListener)activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_arraylist_layout,container, false);
		
		mListView = (ListView) view.findViewById(R.id.arraylist);
		mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, mLists);
		mListView.setAdapter(adapter);
		mListView.setEmptyView(mProgressBar);
		
		mListView.setOnItemClickListener(this);
	}
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
		ArrayAdapter<String> adpater = (ArrayAdapter<String>) parent.getAdapter();
		String item = adpater.getItem(position);
		mOnHeadlineSelectedListener.onSelectItemClick(item);
		
	}

}
