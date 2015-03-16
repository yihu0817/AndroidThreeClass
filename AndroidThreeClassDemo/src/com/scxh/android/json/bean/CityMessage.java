package com.scxh.android.json.bean;

import java.util.List;

public class CityMessage {
	private List<CityBean> info;
	private String resultCode;
	private String resultInfo;

	public List<CityBean> getInfo() {
		return info;
	}

	public void setInfo(List<CityBean> info) {
		this.info = info;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultInfo() {
		return resultInfo;
	}

	public void setResultInfo(String resultInfo) {
		this.resultInfo = resultInfo;
	}
}
