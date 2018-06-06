package thread;

import java.util.HashMap;

import view.QQList;

public class ManageQQList {
	private static HashMap<String, QQList> hm = new HashMap<String, QQList>();

	public static void addQQList(String userId, QQList list) {
		hm.put(userId, list);
	}

	public static QQList getQQList(String userId) {
		return (QQList) hm.get(userId);
	}
}
