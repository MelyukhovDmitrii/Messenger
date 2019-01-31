package me.melyukhov.messenger.common.messages;

import java.security.Key;

public class AESKeyMessage extends Message<Key> {
	private static final long serialVersionUID = 4419952818881615558L;
	
	public AESKeyMessage(Key key) {
		super(key);
	}
}
