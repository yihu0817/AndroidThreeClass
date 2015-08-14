package com.scxh.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.scxh.android.UIMainDemoActivity.MyBaseAdapter;
import com.scxh.android.bean.Model;
import com.scxh.android.ui.layout.AbosoluteActivity;
import com.scxh.android.ui.layout.CodeLayoutActivity;
import com.scxh.android.ui.layout.FramLayoutActivity;
import com.scxh.android.ui.layout.LinearLayoutActivity;
import com.scxh.android.ui.layout.RelativeLayoutActivity;
import com.scxh.android.ui.layout.TableLayoutActivity;

public class LayoutActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setListAdapter(new SimpleAdapter(this, getData(),
				android.R.layout.simple_list_item_1, new String[] { "title" },
				new int[] { android.R.id.text1 }));
	}

	protected List<Map<String, Object>> getData() {
		List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();
		addItem(myData, "线型布局", new Intent(this, LinearLayoutActivity.class));
		addItem(myData, "相对布局RelativeLayout", new Intent(this,
				RelativeLayoutActivity.class));
		addItem(myData, "单帧布局FramLayout", new Intent(this,
				FramLayoutActivity.class));
		addItem(myData, "绝对布局AbosoluteLayout", new Intent(this,
				AbosoluteActivity.class));
		addItem(myData, "表格布局TableLayout", new Intent(this,
				TableLayoutActivity.class));
		addItem(myData, "代码布局", new Intent(this, CodeLayoutActivity.class));
		return myData;
	}

	protected void addItem(List<Map<String, Object>> data, String name,
			Intent intent) {
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("title", name);
		temp.put("intent", intent);
		data.add(temp);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Map<String, Object> object =  (Map<String, Object>) l.getAdapter().getItem(position);

		Intent intent =  (Intent) object.get("intent");
		startActivity(intent);
	}
}
