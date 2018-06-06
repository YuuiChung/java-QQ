package common;

import java.io.Serializable;//序列化

public class User implements Serializable {
	//通过io在网络上传递对象需要进行序列化Serializable
	/**
	 * 
	 */
	private static final long serialVersionUID = 6669390600237258622L;
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
