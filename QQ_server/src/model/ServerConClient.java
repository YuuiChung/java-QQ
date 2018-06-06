package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import thread.ManageServerConClientThread;
import thread.ServerConClientThread;
import common.Message;
import common.MessageType;
import common.User;
import dao.SQLHelper;

public class ServerConClient {

	private ServerSocket ss;

	public ServerConClient() {
		System.out.println("服务器在监听9999端口。。。");
		try {
			ss = new ServerSocket(9999);
			// 一直等待连接
			while (true) {
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				User u = (User) ois.readObject();

				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				Message m = new Message();
				// 此处代替数据库密码验证
				if (new SQLHelper().queryExecute(u.getUserId(), u.getPassword())) {
					// 返回一个成功登陆的包
					m.setMesstype(MessageType.message_successed);
					oos.writeObject(m);
					ServerConClientThread scct = new ServerConClientThread(s);
					scct.start();
					scct.notifyOthers(u.getUserId());
					ManageServerConClientThread.addServerConClientThread(u
							.getUserId(), scct);
				} else {
					m.setMesstype(MessageType.message_login_fail);
					oos.writeObject(m);
					s.close();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
