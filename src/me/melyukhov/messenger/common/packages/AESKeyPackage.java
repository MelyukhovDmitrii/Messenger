package me.melyukhov.messenger.common.packages;

import me.melyukhov.messenger.common.messages.AESKeyMessage;

public class AESKeyPackage extends Package<AESKeyMessage> {
	private static final long serialVersionUID = 7603725213363284871L;
	
	public AESKeyPackage(AESKeyMessage aesKeyMessage) {
		super(aesKeyMessage);
	}
	
}
