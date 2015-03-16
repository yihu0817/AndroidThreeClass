package com.scxh.android.json;

import java.io.Serializable;

public class UpdateDao implements Serializable {
	private int resultCode;
	private String resultInfo;
	private Version info;

	public Version getInfo() {
		return info;
	}

	public void setInfo(Version info) {
		this.info = info;
	}


	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
}
