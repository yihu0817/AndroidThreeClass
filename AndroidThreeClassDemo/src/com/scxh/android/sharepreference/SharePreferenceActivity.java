package com.scxh.android.sharepreference;

import java.io.File;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.scxh.android.R;

public class SharePreferenceActivity extends Activity implements
		OnClickListener {
	private final static String SHARE_PREFERENCE_NAME = "com.scxh.android.SHAREPREFENCE";
	private final static String MESSAGE = "MESSAGE";
	private Button mSaveBtn, mGetDataBtn;
	private TextView mShowTxt;

	private String mShowMessage = "Android SharePreference 数据存储学习";
	private SharePreferenceHelper mSharePreferenceHelper;

	private SharedPreferences mSharedPreferences;
	private FileOpenHelper mFileOpenHelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharepreference_layout);

		mSaveBtn = (Button) findViewById(R.id.save_button);
		mGetDataBtn = (Button) findViewById(R.id.get_button);
		mShowTxt = (TextView) findViewById(R.id.show_textview);

		mSaveBtn.setOnClickListener(this);
		mGetDataBtn.setOnClickListener(this);

		//实例化 SharedPreferences
		mSharedPreferences = getSharedPreferences(SHARE_PREFERENCE_NAME,
				Context.MODE_PRIVATE);
		
		
//		mSharePreferenceHelper = SharePreferenceHelper.getInstance(this);
		
		mFileOpenHelper = FileOpenHelper.getInsance();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.save_button:
			//保存数据到SharedPreferences
//			SharedPreferences.Editor editor = mSharedPreferences.edit();
//			editor.putString(MESSAGE, mShowMessage);
//			editor.commit();
			
			File file = new File(getFilesDir().getPath(),"xinha");
			
			mFileOpenHelper.writeFile(file.getPath(), "android 存储学习");
			
//			UserBean user = new UserBean();
//			user.setUserName("xinhua");
//			user.setPassWord("123454");
//			mSharePreferenceHelper.saveSharePreference(user);
			
			break;
		case R.id.get_button:
			//获取数据从SharedPreferences并显示
//			String message = mSharedPreferences.getString(MESSAGE, "");
//			mShowTxt.setText(message);
			
//			UserBean users = mSharePreferenceHelper.getSharePreference();
			
			String message = mFileOpenHelper.readFile(getFilesDir().getPath(), "xinha").toString();
			mShowTxt.setText(message);
			break;
		}
	}
}
