package me.melyukhov.messenger.server;

import me.melyukhov.messenger.common.ClientStruct;
import me.melyukhov.messenger.common.messages.AESKeyMessage;
import me.melyukhov.messenger.common.messages.UserNameAndTextMessage;
import me.melyukhov.messenger.common.security.Security;

import java.io.IOException;
import java.net.Socket;
import java.security.Key;
import java.util.List;

public class ThreadServer implements IListener {

	private ClientStruct client;
	
	public ThreadServer(Socket socket) {
		client = new ClientStruct(socket);
		client.setHandler(this);
		security();
		ServerDataContainer.subscribe(this);
		listener();
	}
	
	private void listener() {
		new Thread(()->{
			while(true) {
				try {	
					Object pack = client.getObjectInputStream().readObject();
					ServerPackageHandler.resolveTypeAndHandle(pack, client);
				} catch (Exception e) {
					System.out.println("Client with user name " + client.getUserName() + " was close");
					ServerDataContainer.unSubscribe(this);
					client.close();
					break;
				} 
			}
		}).start();
	}


	private void security(){
		try {
			Object securityPack = client.getObjectInputStream().readObject();
			ServerPackageHandler.resolveTypeAndHandle(securityPack, client);
			Key key = Security.generateKeyAES();
			client.setKeyAES(key);
			ServerMessageHandler.OutputMessageHandle.handle(new AESKeyMessage(key), client);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update(List<UserNameAndTextMessage> newMessages) {
		UserNameAndTextMessage[] userNameAndTextMessages = newMessages.subList(client.getLengthMessages(), newMessages.size())
				.stream().toArray(UserNameAndTextMessage[]::new);;
		ServerMessageHandler.OutputMessageHandle.handle(userNameAndTextMessages, client);
		client.setLengthMessageArray(newMessages.size());
	}
}