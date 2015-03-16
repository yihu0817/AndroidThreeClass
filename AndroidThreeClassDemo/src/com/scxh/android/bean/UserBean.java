package com.scxh.android.bean;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;


public class UserBean implements Parcelable {
	public String name;
	public Bitmap headIcon;
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		headIcon.writeToParcel(dest, 0);
	}

	public static final Parcelable.Creator<UserBean> CREATOR = new Parcelable.Creator<UserBean>() {

		@Override
		public UserBean createFromParcel(Parcel source) {
			UserBean user =  new UserBean();
			user.name = source.readString();
			user.headIcon = Bitmap.CREATOR.createFromParcel(source);
			return user;
		}

		@Override
		public UserBean[] newArray(int size) {
			return new UserBean[size];
		}
	};
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Bitmap getHeadIcon() {
		return headIcon;
	}

	public void setHeadIcon(Bitmap headIcon) {
		this.headIcon = headIcon;
	}
}
