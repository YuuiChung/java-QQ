package thread;

import java.util.HashMap;
import view.QQChat;

public class ManageQQChat {

	private static HashMap<String, QQChat> hm = new HashMap<String, QQChat>();

	public static void addQQChat(String userIdAndFriendId, QQChat chat) {
		hm.put(userIdAndFriendId, chat);
	}

	public static QQChat getQQChat(String userIdAndFriendId) {
		return (QQChat) hm.get(userIdAndFriendId);
	}
}
