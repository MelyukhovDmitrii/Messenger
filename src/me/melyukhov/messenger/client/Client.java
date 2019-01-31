package me.melyukhov.messenger.client;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;

import me.melyukhov.messenger.common.messages.PublicKeyMessage;
import me.melyukhov.messenger.common.messages.TextMessage;
import me.melyukhov.messenger.common.messages.UserNameMessage;
import me.melyukhov.messenger.common.security.Security;
import me.melyukhov.messenger.common.ClientStruct;

public class Client {

	private static String HOST = "localhost";	
	private static int PORT = 8112;
	private static ClientStruct client;
	
	public Client() {
		Socket clientSocket = null;
		while (clientSocket == null) {
			try {
				clientSocket = new Socket(HOST, PORT);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		client = new ClientStruct(clientSocket);
		client.setHandler(this);
		System.out.println("Client up!");
		security();
		listen();
	}

	public String getUserName(){
		return client.getUserName();
	}
	
	public void registration(String userName) {
		client.setUserName(userName);
		ClientMessageHandler.OutputHandle.handle(new UserNameMessage(userName), client);
	}
	
	private void security() {
		KeyPair keyPair = Security.generatePairKeysRSA();
		client.setPrivateKey(keyPair.getPrivate());
		client.setPublicKey(keyPair.getPublic());
		ClientMessageHandler.OutputHandle.handle(new PublicKeyMessage(keyPair.getPublic()), client);
	}
	
	public void sendStringMessage(String string) {
		ClientMessageHandler.OutputHandle.handle(new TextMessage(string), client);
	}
	
	private void listen() {
		new Thread(()->{
			while(true) {
				try {
					Object receivedPackage = client.getObjectInputStream().readObject();	
					ClientPackageHandler.resolveTypeAndHandle(receivedPackage, client);
				} catch (Exception e) {
					System.out.println("Server is not responding");
					break;
				}
			}
		}).start();
	}
}