package thread;

import java.io.ObjectInputStream;
import java.net.Socket;

import view.QQChat;
import view.QQList;

import common.Message;
import common.MessageType;

public class ClientConServerThread extends Thread {
	private Socket s;

	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// 不停的读取从服务器端发来的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message ms = (Message) ois.readObject();
				if (ms.getMesstype().equals(MessageType.message_comm)) {
					// 把从服务器获得的消息，显示到该显示的聊天界面上
					QQChat chat = ManageQQChat.getQQChat(ms.getGeter() + " "
							+ ms.getSender());
					chat.showMessage(ms);
				} else if (ms.getMesstype().equals(
						MessageType.message_return_OnlineFriend)) {
					String getter = ms.getGeter();
					// 修改相应的好友列表
					QQList list = ManageQQList.getQQList(getter);
					// 更新在线好友
					if (list != null) {
						list.updateQQList(ms);
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

}
