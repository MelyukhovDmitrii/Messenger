package me.melyukhov.messenger.common.packages;

import me.melyukhov.messenger.common.messages.UserNameAndTextMessage;

public class LastMessagesFromServerPackage extends Package<UserNameAndTextMessage[]> {
	private static final long serialVersionUID = 7670037555111595158L;
	
	public LastMessagesFromServerPackage(UserNameAndTextMessage[] userNameAndTextMessage) {
		super(userNameAndTextMessage);
	}
	
	public LastMessagesFromServerPackage() {
		
	}
	
}
