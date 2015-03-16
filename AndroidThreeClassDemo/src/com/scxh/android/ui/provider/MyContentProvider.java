package com.scxh.android.ui.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import com.scxh.android.ui.db.DataColumn;
import com.scxh.android.ui.db.DatabaseHelper;
/**
 * 第一步: 定义一个类 继承ContentProvider 实现里面接口方法 (采用数据库实现)
 * 第二步: 注册ContentProvider
 * 第三步：使用:getContentResolver()
 *
 * Uri 格式
 * content://com.android.contacts:200/peopel
 * \--------/\----------------------/\--/ \---/
 * scheme          host              port    path
 *            \---------------------/
 *                authority   
 */
public class MyContentProvider extends ContentProvider {
	/**AndroidManifest.xml文件注册时使用, android:authority="com.scxh.android.provider.mycontentprovider"*/
	public final static String AUTHORITY = "com.scxh.android.provider.mycontentprovider";  
	public final static Uri URI_CONTENT = Uri.parse("content://"+AUTHORITY); 
	
	private DatabaseHelper mDatabaseHelper;
	private SQLiteDatabase mSQLiteDatabase;
	@Override
	public boolean onCreate() {
		mDatabaseHelper = DatabaseHelper.getInstance(getContext());
		mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		Cursor cursor = mSQLiteDatabase.query(DataColumn.User.TAB_NAME, projection, selection, selectionArgs, null, null, sortOrder);
		return cursor;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		mSQLiteDatabase.insert(DataColumn.User.TAB_NAME, null, values);
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		mSQLiteDatabase.delete(DataColumn.User.TAB_NAME, selection, selectionArgs);
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,String[] selectionArgs) {
		mSQLiteDatabase.update(DataColumn.User.TAB_NAME, values, selection, selectionArgs);
		return 0;
	}
	@Override
	public String getType(Uri uri) {
		return null;
	}

}
