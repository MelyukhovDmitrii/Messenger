package me.melyukhov.messenger.common.messages;

import java.io.Serializable;

public class UserNameAndTextMessage implements Serializable{

	private static final long serialVersionUID = 2702506755374723589L;
	public UserNameMessage userName;
	public TextMessage textMessage;
	
	public UserNameAndTextMessage(UserNameMessage userNameMessage, TextMessage textMessage) {
		this.userName = userNameMessage;
		this.textMessage = textMessage;
	}
}
