package com.scxh.android.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;
import com.scxh.android.R.drawable;
import com.scxh.android.R.id;
import com.scxh.android.R.layout;
import com.scxh.android.bean.User;
import com.scxh.android.bean.UserBean;
import com.scxh.android.ui.dialog.MyWeixinDialog;

public class EditTextActivity extends Activity {
	private Button mLoginBtn;
	private EditText mUserNameEdit;
	private EditText mPasswordEdit;
	private TextView mBackMessageTxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.view_edittext_layout);

		Log.v("tag", "onCreate >>>>>>");

		mBackMessageTxt = (TextView) findViewById(R.id.backMessageTxt);

		mUserNameEdit = (EditText) findViewById(R.id.userNameEdit);
		mPasswordEdit = (EditText) findViewById(R.id.passwordEdit);
		mLoginBtn = (Button) findViewById(R.id.loginBtn);

		mLoginBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String userName = mUserNameEdit.getText().toString();
				String passWord = mPasswordEdit.getText().toString();

				Log.v("tag", "passWord :" + passWord + " , userName :"
						+ userName);

				if (userName.equals("")) {
					mUserNameEdit.setError("用户名不能为空!");
					return;
				} else if (!userName.equals("xinhua")) {
					mUserNameEdit.setError("用户名输入错误，请重新输入！");
					return;
				}

				if (passWord.equals("")) {
					mPasswordEdit.requestFocus();
					mPasswordEdit.setError("密码不能为空!");

				} else if (!passWord.equals("123456")) {
					mPasswordEdit.requestFocus();
					mPasswordEdit.setError("密码输入错误，请重新输入！");
				} else {
					Toast.makeText(EditTextActivity.this, "登录成功!",
							Toast.LENGTH_SHORT).show();

					String sMessage1 = "日媒：中国完胜日本成为2016年G20峰会主办国";
					String sMessage2 = "韩民间团体称在三八线附近发现地道 韩军方否认";

					Bitmap header1 = BitmapFactory.decodeResource(
							getResources(), R.drawable.mheader1);

					User user1 = new User();
					user1.setId(10);
					user1.setName("张三");
					user1.setPassWord("6454321");
					user1.setHeadIcon(header1);

					User user2 = new User();
					user2.setId(10);
					user2.setName("李四");
					user2.setPassWord("abcd");

					UserBean user3 = new UserBean();
					user3.setName("王二");
					user3.setHeadIcon(header1);

					Intent intent = new Intent(EditTextActivity.this,
							ViewTextActivity.class);

					// =========法一==============================
					Bundle bundle = new Bundle();

					bundle.putString("MESSAGE1", sMessage1);
					bundle.putParcelable("MESSAGEParcelable", user3);

					// =========法二==============================
					intent.putExtras(bundle);
					intent.putExtra("MESSAGE2", sMessage2);
					intent.putExtra("MESSAGESerializable2", user2);

					// startActivity(intent);

					int requestCode = 1;

					startActivityForResult(intent, requestCode);
				}
			}
		});

		mUserNameEdit.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				System.out.println("onTextChanged >>>>>>> " + s.toString()
						+ " start :" + start + "  before :" + before
						+ " count :" + count);
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				System.out.println("beforeTextChanged >>>>>>> " + s.toString()
						+ " start :" + start + " count :" + count);
			}

			@Override
			public void afterTextChanged(Editable s) {
				System.out.println("afterTextChanged >>>>>>> " + s.toString());

			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.v("tag", "onActivityResult >>>>>>");

		Intent intent = data;
		if (intent != null) {

			String backMessage = intent.getStringExtra("BACKEXTRA");

			mBackMessageTxt.setText(backMessage);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			MyWeixinDialog weixinDialog = new MyWeixinDialog(this);
			weixinDialog.show();
		}
		return true;
	}

}
