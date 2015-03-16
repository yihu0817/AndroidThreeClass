package com.scxh.android.receiver;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.Handler;
import android.os.Message;

import com.scxh.android.receiver.sms.SMS;

public class SmsContentObserver extends ContentObserver {
	private static final String[] PROJECTION = new String[] { SMS._ID,// 0
			SMS.TYPE,// 1
			SMS.ADDRESS,// 2
			SMS.BODY,// 3
			SMS.DATE,// 4
			SMS.THREAD_ID,// 5
			SMS.READ,// 6
			SMS.PROTOCOL // 7
	};

	public static final int COLUMN_INDEX_ID = 0;
	public static final int COLUMN_INDEX_TYPE = 1;
	public static final int COLUMN_INDEX_PHONE = 2;
	public static final int COLUMN_INDEX_BODY = 3;
	public static final int COLUMN_INDEX_PROTOCOL = 7;
	private ContentResolver mResolver;
	private Handler mHandler;
	public SmsContentObserver(Handler handler, ContentResolver resolver) {
		super(handler);
		mResolver = resolver;
		mHandler = handler;
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
		Cursor cursor = mResolver.query(SMS.CONTENT_URI, PROJECTION, null, null, null);
		
		if(cursor != null){
			while(cursor.moveToNext()){
				String phone = cursor.getString(COLUMN_INDEX_PHONE);
				if(phone.equals("13894676764")){
					String body = cursor.getString(COLUMN_INDEX_BODY);
					
					Message msg = Message.obtain();
					msg.obj = body;
					mHandler.sendMessage(msg);
				}
			}
		}
		
	}
}
