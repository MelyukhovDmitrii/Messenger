package me.melyukhov.messenger.client;

import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

public class ClientApp {

	public static Window window = null;
	
	public ClientApp(){
		try {
			window = new Window();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
	}
	
}
