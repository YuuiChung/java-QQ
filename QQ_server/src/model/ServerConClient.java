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
		System.out.println("�������ڼ���9999�˿ڡ�����");
		try {
			ss = new ServerSocket(9999);
			// һֱ�ȴ�����
			while (true) {
				Socket s = ss.accept();
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				User u = (User) ois.readObject();

				ObjectOutputStream oos = new ObjectOutputStream(s
						.getOutputStream());
				Message m = new Message();
				// �˴��������ݿ�������֤
				if (new SQLHelper().queryExecute(u.getUserId(), u.getPassword())) {
					// ����һ���ɹ���½�İ�
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
