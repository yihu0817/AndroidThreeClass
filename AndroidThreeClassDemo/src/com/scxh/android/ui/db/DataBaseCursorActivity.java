package com.scxh.android.ui.db;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.scxh.android.R;

public class DataBaseCursorActivity extends ListActivity {
	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mDb;
	private SimpleCursorAdapter mSimpleCursorAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 初始化数据库操作类
		mDatabaseHelper = DatabaseHelper.getInstance(this);
		mDb = mDatabaseHelper.getWritableDatabase();
		
		Cursor cursor = mDb.query(DataColumn.User.TAB_NAME, null, null, null,
				null, null, null);
		
//		String[] from = new String[]{DataColumn.User.USER_NAME};
//		int[] to = new int[]{android.R.id.text1};
//		mSimpleCursorAdapter = new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,from,to);
		
		MyCursorAdapter cursorAdapter = new MyCursorAdapter(this,cursor);
		
		setListAdapter(cursorAdapter);
	}
	
	public class MyCursorAdapter extends CursorAdapter{
		private LayoutInflater mInflater;
		public MyCursorAdapter(Context context, Cursor c) {
			super(context, c);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			/**
			 * public View inflate(int resourceId, ViewGroup root, boolean attachToRoot)
			 * 
			 * <1>如果设置了ViewGroup root参数，且attachToRoot设置为false的话,
			 * 则会从root中得到由layout_width和layout_height组成的LayoutParams,
			 * 就会对我们加载的视图View设置该LayoutParams。
			 * 
			 * <2>如果设置了ViewGroup root参数，且attachToRoot设置为true的话，
			 * 则将我们加载的视图做为子视图添加到root视图中。
			 * 
			 * <3>如果我们ViewGroup root设置为空的话，就直接返回我们创建的视图；
			 */
			
			View v = mInflater.inflate(R.layout.item_simpleadapter_view, null);
//			View v = mInflater.inflate(R.layout.item_simpleadapter_view, parent,true);
//			View v = mInflater.inflate(R.layout.item_simpleadapter_view, parent,false);
			return v;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			String userName = cursor.getString(cursor.getColumnIndex(DataColumn.User.USER_NAME));
			String passWord = cursor.getString(cursor.getColumnIndex(DataColumn.User.PASSWORD));
			String id =  cursor.getString(cursor.getColumnIndex(DataColumn.User._ID));
			
			TextView nameTxt = (TextView) view.findViewById(R.id.titletextView);
			TextView passwordTxt = (TextView) view.findViewById(R.id.infotextView);
			
			nameTxt.setText(userName);
			passwordTxt.setText(passWord);
		}
		
	}

}
