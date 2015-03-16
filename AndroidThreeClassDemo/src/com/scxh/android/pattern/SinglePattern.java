package com.scxh.android.pattern;

import com.scxh.android.util.Logs;

public class SinglePattern {
	private static SinglePattern SINGLE_PATTERN = null;

	public static SinglePattern getInstance() {
		if (SINGLE_PATTERN == null) {
			SINGLE_PATTERN = new SinglePattern();
		}
		return SINGLE_PATTERN;
	}
	
	private SinglePattern() {
		//初始化
	}

	public void doSome(){
		Logs.v("dosome");
	}
}
