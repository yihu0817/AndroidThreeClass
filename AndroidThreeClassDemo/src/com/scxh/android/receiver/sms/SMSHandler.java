/**
 * @author: zhous
 * @version: v1.0
 * @time: 2011-5-12
 */
package com.scxh.android.receiver.sms;

import com.scxh.android.util.Logs;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSHandler extends Handler {
	public static final String TAG = "SMSObserver";

	private Context mContext;

	public SMSHandler(Context context) {
		super();
		this.mContext = context;
	}

	public void handleMessage(Message message) {
		Logs.v("handleMessage >>>>>>>>>>");
		MessageItem item = (MessageItem) message.obj;
		sendBroadcast(item);
		
		SmsManager.getDefault().sendTextMessage("5556", null,
				item.getBody(), null, null);
		
//		 delete(item);
		// send("119","短信测试!");
	}

	private void sendBroadcast(MessageItem item) {
		Logs.v("sendBroadcast >>>>>>>>>>");
		Intent intent = new Intent(Globals.ACTION_SEND_SMS);
		intent.putExtra(Globals.EXTRA_SMS_DATA, item);
		mContext.sendBroadcast(intent);
	}

	// delete the sms
	private void delete(MessageItem item) {
		Uri uri = ContentUris.withAppendedId(SMS.CONTENT_URI, item.getId());
		mContext.getContentResolver().delete(uri, null, null);
	}

	// 发送信息
	private void send(String phone, String message) {

		PendingIntent pi = PendingIntent.getActivity(mContext, 0, new Intent(
				mContext, SmsMessage.class), 0);

		SmsManager sms = SmsManager.getDefault();

		sms.sendTextMessage(phone, null, message, pi, null);

	}

	/**
	 * 本文主要介绍PendingIntent的作用和举例以及和Intent的区别，本文中代码见AndroidDemo@GoogleCode。
	 * 1、PendingIntent作用 根据字面意思就知道是延迟的intent，主要用来在某个事件完成后执行特定的Action。
	 * PendingIntent包含了Intent及Context，所以就算Intent所属程序结束，PendingIntent依然有效，可以在其他程序中使用。 常用在通知栏及短信发送系统中。
	 * PendingIntent一般作为参数传给某个实例，在该实例完成某个操作后自动执行PendingIntent上的Action，也可以通过PendingIntent的send函数手动执行
	 * ，并可以在send函数中设置OnFinished表示send成功后执行的动作。
	 */
	private final static String SEND_ACTION = "send";
	private final static String DELIVERED_ACTION = "delivered";

	private void sendSms(String receiver, String text) {
		SmsManager s = SmsManager.getDefault();
		PendingIntent sentPI = PendingIntent.getBroadcast(mContext, 0,
				new Intent(SEND_ACTION), PendingIntent.FLAG_CANCEL_CURRENT);
		PendingIntent deliveredPI = PendingIntent
				.getBroadcast(mContext, 0, new Intent(DELIVERED_ACTION),
						PendingIntent.FLAG_CANCEL_CURRENT);
		// 发送完成
		mContext.registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(context, "Send Success!", Toast.LENGTH_SHORT)
							.show();
					break;
				case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
					Toast.makeText(context,
							"Send Failed because generic failure cause.",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NO_SERVICE:
					Toast.makeText(
							context,
							"Send Failed because service is currently unavailable.",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_NULL_PDU:
					Toast.makeText(context,
							"Send Failed because no pdu provided.",
							Toast.LENGTH_SHORT).show();
					break;
				case SmsManager.RESULT_ERROR_RADIO_OFF:
					Toast.makeText(
							context,
							"Send Failed because radio was explicitly turned off.",
							Toast.LENGTH_SHORT).show();
					break;
				default:
					Toast.makeText(context, "Send Failed.", Toast.LENGTH_SHORT)
							.show();
					break;
				}
			}
		}, new IntentFilter(SEND_ACTION));

		// 对方接受完成
		mContext.registerReceiver(new BroadcastReceiver() {

			@Override
			public void onReceive(Context context, Intent intent) {
				switch (getResultCode()) {
				case Activity.RESULT_OK:
					Toast.makeText(context, "Delivered Success!",
							Toast.LENGTH_SHORT).show();
					break;
				default:
					Toast.makeText(context, "Delivered Failed!",
							Toast.LENGTH_SHORT).show();
					break;
				}
			}
		}, new IntentFilter(DELIVERED_ACTION));

		// 发送短信，sentPI和deliveredPI将分别在短信发送成功和对方接受成功时被广播
		s.sendTextMessage(receiver, null, text, sentPI, deliveredPI);
	}
}
