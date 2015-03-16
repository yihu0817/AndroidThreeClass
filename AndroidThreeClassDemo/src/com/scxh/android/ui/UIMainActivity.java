package com.scxh.android.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SimpleAdapter;

import com.scxh.android.R;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;
import com.scxh.android.ui.layout.AbosoluteActivity;
import com.scxh.android.ui.layout.CodeLayoutActivity;
import com.scxh.android.ui.layout.FramLayoutActivity;
import com.scxh.android.ui.layout.LinearLayoutActivity;
import com.scxh.android.ui.layout.RelativeLayoutActivity;
import com.scxh.android.ui.layout.TableLayoutActivity;
import com.scxh.android.ui.listview.ArrayActivity;
import com.scxh.android.ui.listview.MyBaseAdapterActivity;
import com.scxh.android.ui.listview.SimpleActivity;
import com.scxh.android.ui.spiners.SpinnersActivity;

public class UIMainActivity extends Activity {
	private Button mEditTextBtn;
	private Button mLifeBtn;
	private Button mRadioBtn, mStateBtn,mIntentBtn,mSimpleBtn;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_main_layout);

		mContext = this;

		mEditTextBtn = (Button) findViewById(R.id.editTextoneBtn);
		mLifeBtn = (Button) findViewById(R.id.lifeBtn);
		mRadioBtn = (Button) findViewById(R.id.radioBtn);
		mStateBtn = (Button) findViewById(R.id.stateBtn);
		mIntentBtn = (Button) findViewById(R.id.intentBtn);
		mSimpleBtn = (Button) findViewById(R.id.simpeAdapterBtn);
		mRadioBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UIMainActivity.this,
						RadioActivity.class);
				startActivity(intent);
			}
		});

		mEditTextBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(UIMainActivity.this,
						EditTextActivity.class));
			}
		});

		mLifeBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(mContext, LifeActivity.class);
				startActivity(intent);

				UIMainActivity.this.finish();
			}
		});

		mStateBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				ComponentName component = new ComponentName(UIMainActivity.this, StateActivty.class);
				
				Intent intent = new Intent();
				
				intent.setAction("scxh.intent.action.GOTO");
//				intent.setComponent(component);

				startActivity(intent);
			}
		});
		
		mIntentBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setComponent(new ComponentName(UIMainActivity.this, IntentActivity.class));
				startActivity(intent);
			}
		});
		
		mSimpleBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UIMainActivity.this,SimpleActivity.class);
				startActivity(intent);
				
			}
		});
	}

	public void onLinearLayoutClick(View v) {
		startActivity(new Intent(this, LinearLayoutActivity.class));
	}

	public void onRelativieLayoutClick(View v) {
		startActivity(new Intent(this, RelativeLayoutActivity.class));
	}

	public void onFramLayoutClick(View v) {
		startActivity(new Intent(this, FramLayoutActivity.class));
	}

	public void onAbosoluteLayoutClick(View v) {
		startActivity(new Intent(this, AbosoluteActivity.class));
	}

	public void onTableLayoutClick(View v) {
		startActivity(new Intent(this, TableLayoutActivity.class));
	}

	public void onCodeLayoutClick(View v) {
		startActivity(new Intent(this, CodeLayoutActivity.class));
	}

	public void onTextViewClick(View v) {
		startActivity(new Intent(this, ViewTextActivity.class));
	}

	public void onActionListnerClick(View v) {
		startActivity(new Intent(this, ActionListnerActivity.class));
	}

	public void onAdapterClick(View v){
		startActivity(new Intent(this,ArrayActivity.class));
	}
	
	public void onmyBaseAdapterClick(View v){
		startActivity(new Intent(this,MyBaseAdapterActivity.class));
	}
	
	public void onSpinnerAdapterClick(View v){
		startActivity(new Intent(this,SpinnersActivity.class));
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		System.out.println(" UIMainActivity onStart >>>>>>>>>>>");
	}

	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("UIMainActivity onResume >>>>>>>>>>>");
	}

	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("UIMainActivity onPause >>>>>>>>>>>");
	}

	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("UIMainActivity onStop >>>>>>>>>>>");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("UIMainActivity onDestroy >>>>>>>>>>>");
	}
}
