package thread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import common.Message;
import common.MessageType;

public class ServerConClientThread extends Thread {
	private Socket s;

	public ServerConClientThread(Socket s) {
		this.s = s;
	}

	@SuppressWarnings("unchecked")
	public void notifyOthers(String iam) {
		// �õ��������ߵ��û����߳�
		HashMap hm = ManageServerConClientThread.hm;
		Iterator it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setContent(iam);
			m.setMesstype(MessageType.message_return_OnlineFriend);
			// ȡ�������û���id
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageServerConClientThread
								.getServerConClientThread(onLineUserId).s
								.getOutputStream());
				m.setGeter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void run() {
		while (true) {
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message m = (Message) ois.readObject();
				if (m.getMesstype().equals(MessageType.message_comm)) {
					// ���������ת������
					// ȡ�ý����˵�ͨѶ�߳�
					ServerConClientThread scct = ManageServerConClientThread
							.getServerConClientThread(m.getGeter());
					ObjectOutputStream oos = new ObjectOutputStream(scct.s
							.getOutputStream());
					oos.writeObject(m);
				} else if (m.getMesstype().equals(
						MessageType.message_wantget_OnlineFriend)) {
					// ���ڷ������ĺ��Ѹ��ÿͻ��˷���
					String res = ManageServerConClientThread
							.getAllOnlineUserId();
					Message m2 = new Message();
					m2.setMesstype(MessageType.message_return_OnlineFriend);
					m2.setContent(res);
					m2.setGeter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s
							.getOutputStream());
					oos.writeObject(m2);
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
