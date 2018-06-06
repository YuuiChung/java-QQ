package model;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import thread.ClientConServerThread;
import thread.ManageClientConServerThread;
import common.Message;
import common.MessageType;
import common.User;

public class ClientConServer {
	public Socket s;

	// 发送第一次请求
	public boolean sendLoginInfoToServer(Object o) {
		boolean b = false;
		try {
			s = new Socket("127.0.0.1", 9999);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			Message ms = (Message) ois.readObject();
			// 这里就是验证用户登录的地方
			if (ms.getMesstype().equals(MessageType.message_successed)) {
				b = true;
				// 就创建一个该QQ号和服务器端保持通讯连接的线程
				ClientConServerThread ccst = new ClientConServerThread(s);
				ccst.start();
				ManageClientConServerThread.addClientConServerThread(((User) o)
						.getUserId(), ccst);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return b;
	}
}
