package me.melyukhov.messenger.common.messages;

import java.io.Serializable;

public class UserNameAndPass implements Serializable{

	private static final long serialVersionUID = 3124335138968102592L;
	
	public UserNameMessage userNameMessage;
	public PasswordMessage passwordMessage;
	
	public UserNameAndPass() {
		
	}
	
	public UserNameAndPass(UserNameMessage userNameMessage) {
		this.userNameMessage = userNameMessage;
	}
	
	public UserNameAndPass(UserNameMessage userNameMessage, PasswordMessage passwordMessage) {
		this.userNameMessage = userNameMessage;
		this.passwordMessage = passwordMessage;
	}
	
	public UserNameAndPass(PasswordMessage passwordMessage) {
		this.passwordMessage = passwordMessage;
	}
}
