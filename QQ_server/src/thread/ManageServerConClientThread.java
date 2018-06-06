package thread;

import java.util.HashMap;
import java.util.Iterator;

public class ManageServerConClientThread {

	public static HashMap<String, ServerConClientThread> hm = new HashMap<String, ServerConClientThread>();

	public static void addServerConClientThread(String userId,
			ServerConClientThread scct) {
		hm.put(userId, scct);
	}

	public static ServerConClientThread getServerConClientThread(String userId) {
		return (ServerConClientThread) hm.get(userId);
	}

	// ���ص�ǰ���ߵ��˵ĺ������
	public static String getAllOnlineUserId() {
		// ʹ�õ�������ɣ�����hm��kֵ(�൱��forѭ��)
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}
