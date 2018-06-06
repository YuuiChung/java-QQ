package common;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3890803966011208287L;
	private String sender;
	private String geter;
	private String content;
	private String time;
	private String Messtype;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGeter() {
		return geter;
	}

	public void setGeter(String geter) {
		this.geter = geter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMesstype() {
		return Messtype;
	}

	public void setMesstype(String messtype) {
		Messtype = messtype;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
