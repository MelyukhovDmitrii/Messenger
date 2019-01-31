package me.melyukhov.messenger.server;

import java.util.List;

import me.melyukhov.messenger.common.messages.UserNameAndTextMessage;

public interface IListener {

	public void update(List<UserNameAndTextMessage> newMessages);
	
}
