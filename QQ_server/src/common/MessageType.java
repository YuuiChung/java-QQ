package common;

public interface MessageType {
	String message_successed = "1";// 表明登陆成功
	String message_login_fail = "2";// 表明登陆失败
	String message_comm = "3";// 普通信息包
	String message_wantget_OnlineFriend = "4";// 要求返回在线列表的包
	String message_return_OnlineFriend = "5";// 返回在线好友列表的包
}
