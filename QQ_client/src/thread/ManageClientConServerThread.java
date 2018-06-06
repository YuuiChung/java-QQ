package thread;

import java.util.HashMap;

public class ManageClientConServerThread {

	private static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();

	// 把创建好的ClientConServerThread放入到hm中
	public static void addClientConServerThread(String userId,
			ClientConServerThread ccst) {
		hm.put(userId, ccst);
	}

	// 通过userId取得该线程
	public static ClientConServerThread getClientConServerThread(String userId) {
		return (ClientConServerThread) hm.get(userId);
	}
}
