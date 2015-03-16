package com.scxh.android.ui.spiners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.scxh.android.R;

public class MyArrayAdapter extends BaseAdapter {
	private String[] array;
	private LayoutInflater mInfalter;

	public MyArrayAdapter(Context context,String[] marray) {
		mInfalter = LayoutInflater.from(context);
		array = marray;
	}

	@Override
	public int getCount() {
		return array.length;
	}

	@Override
	public Object getItem(int position) {
		return array[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView txt;
		if (convertView == null) {
			convertView = mInfalter.inflate(R.layout.item_arraytext_view,
					null);
			txt = (TextView) convertView.findViewById(R.id.msgtextview);
			convertView.setTag(txt);
		} else {
			txt = (TextView) convertView.getTag();
		}

		String msg = (String) getItem(position);
		txt.setText(msg);
		return convertView;
	}

}