package thread;

import java.util.HashMap;

public class ManageClientConServerThread {

	private static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();

	// �Ѵ����õ�ClientConServerThread���뵽hm��
	public static void addClientConServerThread(String userId,
			ClientConServerThread ccst) {
		hm.put(userId, ccst);
	}

	// ͨ��userIdȡ�ø��߳�
	public static ClientConServerThread getClientConServerThread(String userId) {
		return (ClientConServerThread) hm.get(userId);
	}
}
