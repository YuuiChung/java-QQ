package model;

public class LoginCheck {

	public boolean checkUser(Object o) {
		return new ClientConServer().sendLoginInfoToServer(o);
	}
}
