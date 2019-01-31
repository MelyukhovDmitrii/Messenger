package me.melyukhov.messenger.common.messages;

public class UserNameMessage extends Message<String> {
	private static final long serialVersionUID = -1849941074803728109L;
	
	public UserNameMessage(String username) {
		this.setMessage(username);
	}
}
