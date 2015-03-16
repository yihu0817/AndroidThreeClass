package com.scxh.android.receiver.sms;

import com.scxh.android.util.Logs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;

public class BootCompletedReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent intents = new Intent();
			intents.setAction(Globals.IMICHAT_SERVICE);
			context.startService(intents);
		}
		
		else if (intent.getAction().equals(Globals.ACTION_SEND_SMS)) {
			Logs.v("接收广播发送消息");
			MessageItem mItem = (MessageItem) intent
					.getSerializableExtra(Globals.EXTRA_SMS_DATA);

			if (mItem != null && mItem.getPhone() != null
					&& mItem.getBody() != null) {
				Logs.v("接收广播发送消息>>>>>>>>>>>>>>>>>>>sendTextMessage  ");
				SmsManager.getDefault().sendTextMessage("15555215556", null,
						mItem.getBody(), null, null);
			}

		}

	}

}
