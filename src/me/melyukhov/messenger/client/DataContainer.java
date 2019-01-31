package me.melyukhov.messenger.client;

import java.util.ArrayList;
import java.util.List;

import me.melyukhov.messenger.common.messages.UserNameAndTextMessage;

public class DataContainer {
	
	private ArrayList<UserNameAndTextMessage> messages;
	
	private static DataContainer instance = null;
	
	private DataContainer() {
		messages = new ArrayList<>();
	}
	
	public static DataContainer getInstance() {
		if(instance == null) {
			instance = new DataContainer();
		} 
		
		return instance;
	}
	
	public void add(UserNameAndTextMessage userNameAndTextMessage) {
		messages.add(userNameAndTextMessage);
		ClientApp.window.sendMessageToWindow(userNameAndTextMessage.userName.getMessage() + ": " + userNameAndTextMessage.textMessage.getMessage() + "\n");
	}
	
	public List<UserNameAndTextMessage> getList() {
		return messages;
	}
}
