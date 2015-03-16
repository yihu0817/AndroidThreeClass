package com.scxh.android.ui.db;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.scxh.android.R;

public class DataBaseActivity extends Activity implements OnClickListener {
	private Button mInsertBtn, mSelectBtn, mDeleteBtn, mUpdateBtn;
	private TextView mShowDataTxt;
	private ListView mListView;

	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mDb;

	private String[]  mArrays;
	
	private MyBaseAdapter mMyBaseAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database_layout);
		
		ContentResolver contentResolver  = getContentResolver();
		
		// 初始化控件
		mInsertBtn = (Button) findViewById(R.id.insertbtn);
		mSelectBtn = (Button) findViewById(R.id.selectbtn);
		mDeleteBtn = (Button) findViewById(R.id.deletebtn);
		mUpdateBtn = (Button) findViewById(R.id.updatebtn);
		mShowDataTxt = (TextView) findViewById(R.id.showdataTextview);
		mListView = (ListView)findViewById(R.id.listview);
		
		mInsertBtn.setOnClickListener(this);
		mSelectBtn.setOnClickListener(this);
		mDeleteBtn.setOnClickListener(this);
		mUpdateBtn.setOnClickListener(this);

		// 初始化数据库操作类
		mDatabaseHelper = DatabaseHelper.getInstance(this);
		mDb = mDatabaseHelper.getReadableDatabase();
//		mDb = mDatabaseHelper.getWritableDatabase();
		
		mMyBaseAdapter = new MyBaseAdapter(this);
		mListView.setAdapter(mMyBaseAdapter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mDb.close();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.insertbtn:
			insertDBByAndroid();
			ToastMakeText("插入数据成功");
			break;
		case R.id.updatebtn:
			updataDBByAndroid();
			ToastMakeText("修改数据成功");
			break;
		case R.id.deletebtn:
			deleteDBByAndroid();
			ToastMakeText("删除数据成功");
			break;
		case R.id.selectbtn:
			mShowDataTxt.setText("");
			searchCursor();
			break;
		}

	}

	// ================java================
	public void insertDb() {
		String sql = "insert into " + DataColumn.User.TAB_NAME + "(" + DataColumn.User.USER_NAME
				+ "," + DataColumn.User.USER_NAME + ") values ('xinhua','123456')";
		mDb.execSQL(sql);
	}

	public void deleteDB() {
		String sql = "delete from user where username='xinhua'";
		mDb.execSQL(sql);
	}

	public void updateDB() {
		String sql = "update user set username = 'javase' where username = 'android'";
		mDb.execSQL(sql);
	}

	public Cursor findDB() {
		String sql = "select * from user";
//		String[] selectionArgs = new String[] { "java" };

		return mDb.rawQuery(sql, null);
	}

	// =================android===============================
	public void insertDBByAndroid() {
		ContentValues contentValues = new ContentValues(); // 装载插入数据
		contentValues.put(DataColumn.User.USER_NAME, "android");
		contentValues.put(DataColumn.User.PASSWORD, "54321");
		mDb.insert(DataColumn.User.TAB_NAME, null, contentValues);
	}

	public void deleteDBByAndroid() {
		String whereClause = DataColumn.User.USER_NAME + "=?";
		String[] whereArgs = new String[] { "android" };
		mDb.delete(DataColumn.User.TAB_NAME, whereClause, whereArgs);
	}

	public void updataDBByAndroid() {
		ContentValues contentValues = new ContentValues(); // 装载数据
		contentValues.put(DataColumn.User.USER_NAME, "java");

		String whereClause = DataColumn.User.USER_NAME + "=?";
		String[] whereArgs = new String[] { "android" };

		mDb.update(DataColumn.User.TAB_NAME, contentValues, whereClause, whereArgs);

	}

	/**
	 * table：表名称 
	 * colums：列名称数组 
	 * selection：条件子句，相当于where 
	 * selectionArgs：条件语句的参数数组
	 * groupBy：分组 
	 * having：分组条件
	 * orderBy：排序类 
	 * limit：分页查询的限制
	 * Cursor：返回值，相当于结果集ResultSet
	 * 
	 * @return
	 */
	public Cursor findDBByAndroid() {
		Cursor cursor = mDb.query(DataColumn.User.TAB_NAME, null, null, null, null, null,
				null);
		return cursor;
	}

	/**
	 * Cursor类方法: 
	 * getCount() 总记录条数; 
	 * isFirst() 判断是否第一条记录 ; 
	 * isLast(); 判断是否最后一条记录;
	 * moveToFirst() 移动到第一条记录; 
	 * moveToLast()移动到最后一条记录 ; 
	 * move(int;offset)移动到指定的记录; 
	 * moveToNext()移动到下一条记录 ; 
	 * moveToPrevious() 移动到上一条记录;
	 * getColumnIndex(String columnName) 获得指定列索引的int类型值;
	 */
	public void searchCursor() {
		Cursor cursor = findDBByAndroid();
		// =========方法一=============
		
//		int i = 0;
//		while (cursor.moveToNext()) {
//			String id = cursor.getString(cursor
//					.getColumnIndex(DataColumn.User._ID));
//			String userName = cursor.getString(cursor
//					.getColumnIndex(DataColumn.User.USER_NAME));
//			String passWord = cursor.getString(cursor
//					.getColumnIndex(DataColumn.User.PASSWORD));
//			
//			String item = id +"  "+ userName +"  "+ passWord;
//			mArrays[i] = item;
//			
//			i++;
//			
//			mShowDataTxt.append("用户名:" + userName + " 密码 :" + passWord);
//		}
//		cursor.close();

		// ==========方法二================
		if (cursor.moveToFirst()) {
			int count = cursor.getCount();
			ArrayList<UserBean> userLists = new ArrayList<UserBean>();
			for (int i = 0; i < count; i++) {
				String id = cursor.getString(cursor
						.getColumnIndex(DataColumn.User._ID));
				String userName = cursor.getString(cursor
						.getColumnIndex(DataColumn.User.USER_NAME));
				String passWord = cursor.getString(cursor
						.getColumnIndex(DataColumn.User.PASSWORD));
				
				UserBean userBean = new UserBean();
				userBean.setId(id);
				userBean.setUserName(userName);
				userBean.setPassWord(passWord);
				
				userLists.add(userBean);


				cursor.moveToNext();
			}
			cursor.close();
			
			mMyBaseAdapter.setData(userLists);
		}
		
		
	}

	public void ToastMakeText(String content) {
		Toast.makeText(DataBaseActivity.this, content, Toast.LENGTH_SHORT)
				.show();
	}
	
	
	public class MyBaseAdapter extends BaseAdapter{
		private ArrayList<UserBean> mUserLists = new ArrayList<UserBean>();
		private LayoutInflater mInflater;
		public MyBaseAdapter(Context context){
			mInflater = LayoutInflater.from(context);
		}
		public void setData(ArrayList<UserBean> userLists){
			mUserLists = userLists;
			notifyDataSetChanged();
		}
		@Override
		public int getCount() {
			return mUserLists.size();
		}

		@Override
		public Object getItem(int position) {
			return mUserLists.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView contentTxt;
			if(convertView == null){
				convertView = mInflater.inflate(android.R.layout.simple_list_item_1, null);
				contentTxt = (TextView) convertView.findViewById(android.R.id.text1);
				convertView.setTag(contentTxt);
			}else{
				contentTxt = (TextView)convertView.getTag();
			}
		
			UserBean user = (UserBean)getItem(position);
			String item = user.getId()+ " "+ user.getUserName()+ " "+user.getPassWord();
			contentTxt.setText(item);
			
			return convertView;
		}
		
	}
}
