package com.scxh.android.ui.db;

import com.scxh.android.util.Logs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteOpenHelper是SQLiteDatabase的一个帮助类，用来管理数据库的创建和版本的更新。一般是建立一个类继承它，
 * 并实现它的onCreate和onUpgrade方法。
 * 
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	private static final String DB_NAME = "mydata.db"; // 数据库名称
	private static final int version = 6; // 数据库版本

	private static DatabaseHelper DBHELPER = null;
	public static DatabaseHelper getInstance(Context context){
		if(DBHELPER == null){
			DBHELPER = new DatabaseHelper(context);
		}
		return DBHELPER;
	}
	private DatabaseHelper(Context context) {
		super(context, DB_NAME, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Logs.v("DatabaseHelper onCreate 创建数据库表user >>>");
		
		String sqls  =  "create table " + DataColumn.User.TAB_NAME 
				+ "("
				+ DataColumn.User._ID+" INTEGER PRIMARY KEY AUTOINCREMENT ," 
				+ DataColumn.User.USER_NAME + " varchar(20) not null , " 
				+ DataColumn.User.PASSWORD + " varchar(60) not null "
				+ ");";
		
		db.execSQL(sqls);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// 数据库升级
		Logs.v("DatabaseHelper onUpgrade oldVersion :"+oldVersion+ " newVersion :"+newVersion);
	
		if(newVersion < 5){
			String sql = "alter table "+DataColumn.User.TAB_NAME+" add sex int(10)";
			db.execSQL(sql);
		}
	}

}