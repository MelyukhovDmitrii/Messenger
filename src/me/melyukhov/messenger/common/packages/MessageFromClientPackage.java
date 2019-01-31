package me.melyukhov.messenger.common.packages;

import me.melyukhov.messenger.common.messages.TextMessage;

public class MessageFromClientPackage extends Package<TextMessage> {
	private static final long serialVersionUID = 9146511765720612075L;
	
	public MessageFromClientPackage(TextMessage message) {
		super(message);
	}
	
}
