package me.melyukhov.messenger.server;

import java.io.IOException;

public class Server {

	int port = 8112;
	
	public Server() throws IOException {
		new HandShaker(port);
	}
	
}
