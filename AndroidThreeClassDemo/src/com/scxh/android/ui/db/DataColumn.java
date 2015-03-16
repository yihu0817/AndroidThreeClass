package com.scxh.android.ui.db;

import android.provider.BaseColumns;

public class DataColumn {
	public class User implements BaseColumns {
		/** 用户表名 */
		public final static String TAB_NAME = "user";
		/** 用户名 username字段 */
		public final static String USER_NAME = "username";
		/** 密码 password字段 */
		public final static String PASSWORD = "password";
		/** 性别 sex字段 */
		public final static String SEX = "sex";
	}

	public class Message implements BaseColumns {
		/** 用户表名 */
		public final static String TAB_NAME = "message";

		/** 用户名 username字段 */
		public final static String NAME = "username";
	}
}
