package com.scxh.android.fragement.subclassfragment;

import com.scxh.android.util.Logs;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyListFragment extends ListFragment {
	private String[] mList = new String[] { "张三", "李四", "王二", "麻子" };

	public ShowDialogListFragmentLisner mShowDialogListFragmentLisner;

	public interface ShowDialogListFragmentLisner {
		public void onShow(int postion, String item);
	}

	public static MyListFragment newInstance() {
		MyListFragment frag = new MyListFragment();
		return frag;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mShowDialogListFragmentLisner = (ShowDialogListFragmentLisner) activity;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// ListView listView = (ListView) getView();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, mList);
		// listView.setAdapter(adapter);
		setListAdapter(adapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		String item = (String) l.getAdapter().getItem(position);

		mShowDialogListFragmentLisner.onShow(position, item);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(data!=null){
			Logs.v(">>> :"+data.getStringExtra("name"));
		}
	}

}
