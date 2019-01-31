package me.melyukhov.messenger.common;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.security.Key;

import me.melyukhov.messenger.common.security.CipherObjectInputStream;
import me.melyukhov.messenger.common.security.CipherObjectOutputStream;
import me.melyukhov.messenger.common.security.SecurityState;

public class ClientStruct {

	public static final Charset CHARSET = Charset.forName("utf-8");
	
	private SecurityState state = SecurityState.DEFAULT;
	
	private Socket clientSocket;
	private String userName;
	private int lengthMessages;
	
	private CipherObjectInputStream objectInputStream;
	private CipherObjectOutputStream objectOutputStream;
	
	private Key publicKey;
	private Key privateKey;
	private Key keyAES;
	
	private Object handler;
	
	public ClientStruct(Socket clientSocket) {
		lengthMessages = 0;
		this.clientSocket = clientSocket;
		try {
			objectOutputStream = new CipherObjectOutputStream(this.clientSocket.getOutputStream());
			objectOutputStream.flush();
			objectInputStream = new CipherObjectInputStream(this.clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("HandShake! " + clientSocket.toString());
	}
	
	public void close() {
		try {
			this.clientSocket.close();
			this.objectInputStream.close();
			this.objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setLengthMessageArray(int length) {
		this.lengthMessages = length;
	}
	
	public void lengthPlusOne() {
		lengthMessages++;
	}
	
	public void setState(SecurityState state) {
		this.state = state;
		this.objectOutputStream.setState(state);
		this.objectInputStream.setState(state);
	}
	
	public SecurityState getState() {
		return state;
	}
	
	public int getLengthMessages() {
		return lengthMessages;
	}
	
	public CipherObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}
	
	public CipherObjectOutputStream getObjectOutputStream() {
		return objectOutputStream;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setKeyAES(Key keyAES) {
		this.keyAES = keyAES;
		objectInputStream.setKeyAES(keyAES);
		objectOutputStream.setKeyAES(keyAES);
	}
	
	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
		objectOutputStream.setPublicKey(publicKey);
	}
	
	public void setPrivateKey(Key privateKey) {
		this.privateKey = privateKey;
		objectInputStream.setPrivateKey(privateKey);
	}
	
	public Key getKeyAES() {
		return keyAES;
	}
	
	public Key getPrivateKey() {
		return privateKey;
	}
	
	public void setHandler(Object handler) {
		this.handler = handler;
	}
	
	public Object getHandler() {
		return handler;
	}
	
	public Key getPublicKey() {
		return publicKey;
	}
	
	public Socket getClientSocket() {
		return clientSocket;
	}
	
	public void setClientSocket(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}
}
