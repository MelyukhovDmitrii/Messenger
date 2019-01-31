package me.melyukhov.messenger.common.packages;

import me.melyukhov.messenger.common.messages.UserNameAndPass;

public class RegistrationPackage extends Package<UserNameAndPass>{

	private static final long serialVersionUID = 1401637668546447365L;
	
	public RegistrationPackage(UserNameAndPass userNameAndPass) {
		super(userNameAndPass);
	}
	
	public RegistrationPackage() {
		
	}
}
