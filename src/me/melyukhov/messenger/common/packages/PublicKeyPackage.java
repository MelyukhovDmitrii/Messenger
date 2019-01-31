package me.melyukhov.messenger.common.packages;

import me.melyukhov.messenger.common.messages.PublicKeyMessage;

public class PublicKeyPackage extends Package<PublicKeyMessage>{
	private static final long serialVersionUID = 7851380486506186971L;	
	
	public PublicKeyPackage(PublicKeyMessage publicKeyMessage) {
		super(publicKeyMessage);
	}
}
