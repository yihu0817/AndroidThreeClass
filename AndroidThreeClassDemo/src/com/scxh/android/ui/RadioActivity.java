package com.scxh.android.ui;

import com.scxh.android.R;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.Toast;

public class RadioActivity extends Activity {
	private String mCity;
	private String mAihao;
	private Button mSubmitBtn;
	private CheckBox mYuleCheckBox,mjunshiCheckBox,mtoutiaoCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radio_layout);

		mSubmitBtn = (Button) findViewById(R.id.submitBtn);
		mYuleCheckBox = (CheckBox) findViewById(R.id.yuelecheckBox);
		mjunshiCheckBox = (CheckBox) findViewById(R.id.junshicheckBox);
		mtoutiaoCheckBox = (CheckBox) findViewById(R.id.toutiaocheckBox);
		mYuleCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(RadioActivity.this, "mYuleCheckBox isChecked :"+isChecked, Toast.LENGTH_SHORT).show();
			}
		});
		mtoutiaoCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(RadioActivity.this, "mtoutiaoCheckBox isChecked :"+isChecked, Toast.LENGTH_SHORT).show();
			}
		});
		
		mjunshiCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Toast.makeText(RadioActivity.this, "mjunshiCheckBox isChecked :"+isChecked, Toast.LENGTH_SHORT).show();
			}
		});

		mSubmitBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(RadioActivity.this,
						"City :" + mCity + " mAihao :" + mAihao,
						Toast.LENGTH_SHORT).show();

			}
		});
	}

	public void onRadioButonClick(View v) {
		RadioButton button = (RadioButton) v;
		String text = button.getText().toString();
		mAihao = text;
	}

	public void onRadioCityButonClick(View v) {
		RadioButton button = (RadioButton) v;
		String text = button.getText().toString();
		mCity = text;
	}
}
