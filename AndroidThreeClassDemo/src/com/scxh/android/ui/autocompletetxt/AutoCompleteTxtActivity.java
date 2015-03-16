package com.scxh.android.ui.autocompletetxt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.scxh.android.R;

public class AutoCompleteTxtActivity extends Activity {
	private AutoCompleteTextView mAutoCompleteTextView;
	private String[] array = new String[] { "abc@163.com", "abd@gmail.com",
			"viktor@qq.com", "voker@sina.com" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_autocompletetxt_layout);

		mAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autotxt);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, array);

		mAutoCompleteTextView.setAdapter(adapter);

	}

	public void spannableStringBuilder() {
		// SpannableStringBuilder ssb = new SpannableStringBuilder( "Here's " );
		// Bitmap smiley = BitmapFactory.decodeResource( getResources(),
		// R.drawable.ic_search_clear_in_dealmap );
		// ssb.setSpan( new ImageSpan( smiley ), ssb.length()-1, ssb.length(),
		// Spannable.SPAN_INCLUSIVE_INCLUSIVE );
		// mAutoCompleteTextView.setText( ssb, BufferType.SPANNABLE );
		//
		// ssb.setSpan(new ClickableSpan() {
		//
		// @Override
		// public void onClick(View widget) {
		// Toast.makeText(AutoCompleteTxtActivity.this, "hello",
		// Toast.LENGTH_SHORT).show();
		// }
		// }, ssb.length()-1, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
	}
}
