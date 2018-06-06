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

	// 返回当前在线的人的好友情况
	public static String getAllOnlineUserId() {
		// 使用迭代器完成，遍历hm的k值(相当于for循环)
		Iterator<String> it = hm.keySet().iterator();
		String res = "";
		while (it.hasNext()) {
			res += it.next().toString() + " ";
		}
		return res;
	}
}
