package me.melyukhov.messenger.server;

import java.io.IOException;
import java.net.ServerSocket;

public class HandShaker {
	
	private ServerSocket serverSocket;

	public HandShaker(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("Server up!");
		handShake();
	}
	
	private void handShake() throws IOException {
		new Thread(()->{
			while (true) {
				try {
					new ThreadServer(serverSocket.accept());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();;
	}
}
