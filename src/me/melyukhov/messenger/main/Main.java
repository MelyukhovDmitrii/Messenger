package me.melyukhov.messenger.main;

import java.io.IOException;

import me.melyukhov.messenger.client.ClientApp;
import me.melyukhov.messenger.server.Server;

public class Main {
	public static void main(String[] args) throws IOException{
		if(args[0].equals("Client")) {
			new ClientApp();
		} else {
			new Server();
		}
	}
}
