package com.scxh.android.sharepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.scxh.android.ui.db.UserBean;

public class SharePreferenceHelper {
	private final static String SHARE_PREFERENCE_NAME = "com.scxh.android.SHAREPREFENCE";
	private final static String USER_NAME = "USER_NAME";
	private final static String PASSWORD = "PASSWORD";
	private final static String ID = "ID";
	private Context mContext;
	private SharedPreferences mSharedPreferences;
	private static SharePreferenceHelper SHARE_PREFERENCE_HELPER = null;

	public static SharePreferenceHelper getInstance(Context context) {
		if (SHARE_PREFERENCE_HELPER == null) {
			SHARE_PREFERENCE_HELPER = new SharePreferenceHelper(context);
		}
		return SHARE_PREFERENCE_HELPER;
	}

	private SharePreferenceHelper(Context context) {
		mContext = context;
		mSharedPreferences = mContext.getSharedPreferences(
				SHARE_PREFERENCE_NAME, Context.MODE_PRIVATE);
	}

	/**
	 * 保存数据到SharePreference
	 * 
	 * @param user
	 */
	public void saveSharePreference(UserBean user) {
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.putString(USER_NAME, user.getUserName());
		editor.putString(PASSWORD, user.getPassWord());
		editor.putString(ID, user.getId());
		editor.commit();
	}
	
	/**
	 * 从SharePreference获取数据
	 * 
	 * @return
	 */
	public UserBean getSharePreference() {
		String userName = mSharedPreferences.getString(USER_NAME, "");
		String passWord = mSharedPreferences.getString(PASSWORD, "");
		String id = mSharedPreferences.getString(ID, "");
		UserBean user = new UserBean();
		user.setId(id);
		user.setPassWord(passWord);
		user.setUserName(userName);
		return user;

	}
	/**
	 * 清空数据
	 */
	public void clear(){
		SharedPreferences.Editor editor = mSharedPreferences.edit();
		editor.clear();
		editor.commit();
	}

}
