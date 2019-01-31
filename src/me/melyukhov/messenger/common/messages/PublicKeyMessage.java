package me.melyukhov.messenger.common.messages;

import java.security.Key;

public class PublicKeyMessage extends Message<Key> {
	private static final long serialVersionUID = -4694156472041148427L;
	
	public PublicKeyMessage(Key key) {
		super(key);
	}
	
}
