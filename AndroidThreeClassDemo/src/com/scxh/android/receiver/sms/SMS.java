/**
 * @author: zhous
 * @version: v1.0
 * @time: 2011-5-12
 */
package com.scxh.android.receiver.sms;

import android.net.Uri;
import android.provider.BaseColumns;

public interface SMS extends BaseColumns {
	public static final Uri CONTENT_URI = Uri.parse("content://sms");

	public static final String FILTER = "!imichat";

	public static final String TYPE = "type";

	public static final String THREAD_ID = "thread_id";

	public static final String ADDRESS = "address";

	public static final String PERSON_ID = "person";

	public static final String DATE = "date";

	public static final String READ = "read";

	public static final String BODY = "body";

	public static final String PROTOCOL = "protocol";

}
