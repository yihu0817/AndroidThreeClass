package com.scxh.android.ui.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.scxh.android.R;

public class ArrayActivity extends Activity {
	private ListView mListView;
	private CharSequence[] array;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_arraylist_layout);
		mListView = (ListView) findViewById(R.id.arraylist);

		array = (CharSequence[]) getResources().getTextArray(R.array.arraylist);

		ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
				this, R.layout.item_arraytext_view, array);

		// ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
		// this, R.array.arraylist, R.layout.view_arraylist_item);

		mListView.setAdapter(adapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				TextView textView = (TextView) view;
				String text = textView.getText().toString();
				
				Intent intent = new Intent(ArrayActivity.this,
						MessageActivity.class);
				intent.putExtra("MESSAGE", text);

				startActivity(intent);
			}

		});
		
	}
}
