package com.scxh.android.ui.spiners;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.scxh.android.R;

public class SpinnersActivity extends Activity {
	private Spinner mSpinner1, mSpinner2, mSpinner3;
	private String[] array = new String[] { "张三", "李四", "王二", "麻子" };
	private String[] array1 = new String[] { "张三1", "李四1", "王二1", "麻子1" };
	private String[] array2 = new String[] { "张三2", "李四2", "王二2", "麻子2" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivity_spiners_layout);
		//第一步 实例化 Spinners
		mSpinner1 = (Spinner) findViewById(R.id.planets_spinner1);
		mSpinner2 = (Spinner) findViewById(R.id.planets_spinner2);
		mSpinner3 = (Spinner) findViewById(R.id.planets_spinner3);

		//第二步 实例化适配器ArrayAdapter
		ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array1);
		ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array2);

		MyArrayAdapter mMyArrayAdapter = new MyArrayAdapter(this,array);

		
		//第三步 绑定适配器
		mSpinner1.setAdapter(mMyArrayAdapter);
		mSpinner2.setAdapter(adapter1);
		mSpinner3.setAdapter(adapter2);
		
		mSpinner1.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				MyArrayAdapter adapter = (MyArrayAdapter) parent.getAdapter();
				String item = (String) adapter.getItem(position);
				Toast.makeText(SpinnersActivity.this, item, Toast.LENGTH_SHORT).show();
				Log.v("TAG", "onItemSelected >> :position "+position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.v("TAG", "onNothingSelected >> ");
			}
		});
		
		mSpinner2.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
				String item = adapter.getItem(position);
				Toast.makeText(SpinnersActivity.this, item, Toast.LENGTH_SHORT).show();
				Log.v("TAG", "onItemSelected >> :position "+position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.v("TAG", "onNothingSelected >> ");
			}
		});
		
		mSpinner3.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				ArrayAdapter<String> adapter = (ArrayAdapter<String>) parent.getAdapter();
				String item = adapter.getItem(position);
				Toast.makeText(SpinnersActivity.this, item, Toast.LENGTH_SHORT).show();
				Log.v("TAG", "onItemSelected >> :position "+position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				Log.v("TAG", "onNothingSelected >> ");
			}
		});

	}

}
