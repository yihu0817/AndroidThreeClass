package com.scxh.android.ui.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.scxh.android.R;

public class PopWindowActivity extends Activity {
	private Button popBtn;
	private PopupWindow popwindow;
	private String[] array = new String[] { "张三", "李四", "王二", "麻子" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivity_popwindow_layout);
		popBtn = (Button) findViewById(R.id.popbtn);

		LayoutInflater inflater = LayoutInflater.from(this);
		View v = inflater.inflate(R.layout.popwindow_item_list, null);
		ListView listView = (ListView) v.findViewById(R.id.listpop);
		
		ArrayAdapter<String> adpater = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array);
		listView.setAdapter(adpater);

//		 TextView contentView = new TextView(PopWindowActivity.this);
//		 contentView.setText("弹出内容");

		popwindow = new PopupWindow(v, 200, 500);

		popBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (popwindow.isShowing()) {
					popwindow.dismiss();
				} else {
					popwindow.showAsDropDown(popBtn);
				}
			}
		});
	}
}
