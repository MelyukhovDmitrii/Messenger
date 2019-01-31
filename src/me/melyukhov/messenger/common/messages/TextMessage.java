package me.melyukhov.messenger.common.messages;

public class TextMessage extends Message<String> {
	private static final long serialVersionUID = -140202060383527126L;
	
	public TextMessage() {
	}
	
	public TextMessage(String text) {
		super(text);
	}
}
