package me.melyukhov.messenger.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import me.melyukhov.messenger.common.messages.UserNameAndTextMessage;
import me.melyukhov.messenger.common.messages.UserNameMessage;

public class ServerDataContainer {
	
	private static List<UserNameAndTextMessage> messages = new CopyOnWriteArrayList<UserNameAndTextMessage>();
	private static List<IListener> listeners = new ArrayList<IListener>();
	
	public static void subscribe(IListener listener) {
		listeners.add(listener);
		notify(listener);
	}
	
	public static void unSubscribe(IListener listener) {
		listeners.remove(listener);
	}
	
	public static void add(UserNameAndTextMessage userNameAndTextMessage) {
		messages.add(userNameAndTextMessage);

		for(IListener listener: listeners) {
			notify(listener);
		}
	}
	
	private static void notify(IListener listener) {
		listener.update(messages);
	}
}
