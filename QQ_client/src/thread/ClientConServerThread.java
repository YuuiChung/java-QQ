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
			// ��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try {
				ObjectInputStream ois = new ObjectInputStream(s
						.getInputStream());
				Message ms = (Message) ois.readObject();
				if (ms.getMesstype().equals(MessageType.message_comm)) {
					// �Ѵӷ�������õ���Ϣ����ʾ������ʾ�����������
					QQChat chat = ManageQQChat.getQQChat(ms.getGeter() + " "
							+ ms.getSender());
					chat.showMessage(ms);
				} else if (ms.getMesstype().equals(
						MessageType.message_return_OnlineFriend)) {
					String getter = ms.getGeter();
					// �޸���Ӧ�ĺ����б�
					QQList list = ManageQQList.getQQList(getter);
					// �������ߺ���
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
