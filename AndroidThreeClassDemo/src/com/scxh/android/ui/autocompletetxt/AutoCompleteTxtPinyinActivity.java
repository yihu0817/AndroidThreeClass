package com.scxh.android.ui.autocompletetxt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.scxh.android.R;

public class AutoCompleteTxtPinyinActivity extends Activity {
	private AutoCompleteTextView mAutoCompleteTextView;
	static final String[] COUNTRIES = new String[] {"huang", "华北区", "华南区", "华东区", "北京",
        "哈尔滨", "天津", "贵州", "广州","内蒙古锡林郭勒草原","内蒙"};//这个数组里面只要全是汉字，就能完美适配，数量也没有限制
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocompletetxt_layout);

		mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autotxt);
		
		PinYinAdapter<String> adapter = new PinYinAdapter<String>(this, 
                    android.R.layout.simple_dropdown_item_1line, COUNTRIES);
		
		mAutoCompleteTextView.setAdapter(adapter);
	}
}
