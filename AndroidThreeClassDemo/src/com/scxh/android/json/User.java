package com.scxh.android.json;


//  {user:{"id":100,"userName":"admin","password":"123456","email":"admin@xinhua.com"},"message":"你好json"}
public class User {
	private int id;
	private String userName;
	private String passWord;
	private String email;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
