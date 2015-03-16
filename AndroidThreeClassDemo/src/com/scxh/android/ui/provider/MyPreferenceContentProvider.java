package com.scxh.android.ui.provider;

import java.util.Iterator;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;

import com.scxh.android.ui.db.DataColumn;

public class MyPreferenceContentProvider extends ContentProvider {
	public static final String AUTHORITY = "com.warmtel.android.mypreferencecontentprovider";
	public static final Uri URI_CONTENT = Uri.parse("content://" + AUTHORITY);
	private static final String PREFERENCE_NAME = "com.scxh.android.MyPreferenceNAME";
	private SharedPreferences mSharedPreferences;
	
	@Override
	public boolean onCreate() {
		mSharedPreferences = getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		
		String name = mSharedPreferences.getString(DataColumn.User.USER_NAME, "");
		String password = mSharedPreferences.getString(DataColumn.User.PASSWORD, "");
		String id = "1";
		
		String[] columnValues = new String[]{id,name,password};
		
		MatrixCursor mc = new MatrixCursor(projection);
		mc.addRow(columnValues);

		return mc;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Editor edit = mSharedPreferences.edit();
	
		Iterator iter = values.keySet().iterator(); 
		
		while (iter.hasNext()) { 
		    Object key = iter.next(); 
		    Object value = values.get(String.valueOf(key)); 
		    
		    if(value instanceof String){
		    	edit.putString(String.valueOf(key), String.valueOf(value));
		    }else if(value instanceof Integer){
		    	edit.putInt(String.valueOf(key), (Integer)value);
		    }else if(value instanceof Boolean){
		    	edit.putBoolean(String.valueOf(key), (Boolean)value);
		    }else if(value instanceof Long){
		    	edit.putLong(String.valueOf(key), (Long)value);
		    }else{
		    	new Throwable("插入数据出错!");
		    }
		} 
		
		edit.commit();
		
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}
	
	@Override
	public String getType(Uri uri) {
		return null;
	}

}
