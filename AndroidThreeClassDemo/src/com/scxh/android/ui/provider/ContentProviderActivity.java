package com.scxh.android.ui.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.scxh.android.R;
import com.scxh.android.ui.db.DataColumn;
import com.scxh.android.util.Logs;

public class ContentProviderActivity extends Activity implements
		OnClickListener {
	private Button mInsertBtn, mSelectBtn, mDeleteBtn, mUpdateBtn,mContactsBtn;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_layout);

		mInsertBtn = (Button) findViewById(R.id.insertbtn);
		mSelectBtn = (Button) findViewById(R.id.selectbtn);
		mDeleteBtn = (Button) findViewById(R.id.deletebtn);
		mUpdateBtn = (Button) findViewById(R.id.updatebtn);
		mContactsBtn = (Button) findViewById(R.id.getContactsbtn);
		mListView = (ListView) findViewById(R.id.listview);

		mInsertBtn.setOnClickListener(this);
		mSelectBtn.setOnClickListener(this);
		mDeleteBtn.setOnClickListener(this);
		mUpdateBtn.setOnClickListener(this);
		mContactsBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.insertbtn:
			ContentValues values = new ContentValues(); // 装载插入数据
			values.put(DataColumn.User.USER_NAME, "contentProvider学习");
			values.put(DataColumn.User.PASSWORD, "abcde");

//			getContentResolver().insert(MyContentProvider.URI_CONTENT, values);

			getContentResolver().insert(MyPreferenceContentProvider.URI_CONTENT, values);
			
			break;
		case R.id.updatebtn:
			ContentValues contentValues = new ContentValues(); // 装载数据
			contentValues.put(DataColumn.User.PASSWORD, "12345");
			contentValues.put(DataColumn.User.USER_NAME, "数据存储Provider");

			String whereClause = DataColumn.User.PASSWORD + "=?";
			String[] whereArgs = new String[] { "abcd" };

			getContentResolver().update(MyContentProvider.URI_CONTENT,
					contentValues, whereClause, whereArgs);

			break;
		case R.id.deletebtn:
			String where = DataColumn.User.PASSWORD + "=?";
			String[] selectionArgs = new String[] { "abcd" };

			getContentResolver().delete(MyContentProvider.URI_CONTENT, where,
					selectionArgs);

			break;
		case R.id.selectbtn:
//			Cursor cursor = getContentResolver().query(
//					MyContentProvider.URI_CONTENT, null, null, null, null);
			
			String[] projection = new String[]{DataColumn.User._ID,DataColumn.User.USER_NAME,DataColumn.User.PASSWORD};
			
			Cursor cursor = getContentResolver().query(
					MyPreferenceContentProvider.URI_CONTENT, projection, null, null, null);

			if (cursor.moveToFirst()) {
				int count = cursor.getCount();
				for (int i = 0; i < count; i++) {
					String userName = cursor.getString(cursor.getColumnIndex(DataColumn.User.USER_NAME));
					String passWord = cursor.getString(cursor.getColumnIndex(DataColumn.User.PASSWORD));

					Logs.v("userName :" + userName + " passWord :" + passWord);

					cursor.moveToNext();
				}
				cursor.close();
			}

			break;
		case R.id.getContactsbtn:
			getContactsMessage();
			break;
		}
	}

	public void getContactsMessage() {
		Uri uri = Contacts.CONTENT_URI;
		String[] projection = new String[] { Contacts._ID,Contacts.DISPLAY_NAME };
		int[] to = new int[] { R.id.titletextView, R.id.infotextView};
		
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		
		
		SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
				R.layout.item_simpleadapter_view, cursor, projection,
				to);
		
		mListView.setAdapter(adapter);

	}

}
