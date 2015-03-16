package com.scxh.android.util;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;

public class ReadAssetsFile {
	private static String text;

	public static String readtxt(Context context, String name) {
		try {
			InputStream is = context.getAssets().open(name);
			int size = is.available();
			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();
			// Convert the buffer into a string.
			text = new String(buffer, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}
}
