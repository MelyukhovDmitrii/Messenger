package me.melyukhov.messenger.server;

import java.io.IOException;

import me.melyukhov.messenger.common.ClientStruct;
import me.melyukhov.messenger.common.packages.AESKeyPackage;
import me.melyukhov.messenger.common.packages.LastMessagesFromServerPackage;
import me.melyukhov.messenger.common.packages.MessageFromClientPackage;
import me.melyukhov.messenger.common.packages.PublicKeyPackage;
import me.melyukhov.messenger.common.packages.RegistrationPackage;

public class ServerPackageHandler {
	
	public static class InputPackageHandler {

		public static void handle(MessageFromClientPackage pack, ClientStruct client) {
			ServerMessageHandler.InputMessageHandle.handle(pack.getContent(), client);
		}

		public static void handle(PublicKeyPackage pack, ClientStruct client) {
			ServerMessageHandler.InputMessageHandle.handle(pack.getContent(), client);
		}

		public static void handle(RegistrationPackage pack, ClientStruct client) {
			ServerMessageHandler.InputMessageHandle.handle(pack.getContent().userNameMessage, client);
			ServerMessageHandler.InputMessageHandle.handle(pack.getContent().passwordMessage, client);
		}
		
	}
	
	public static class OutputPackageHandler {
		
		public static void handle(LastMessagesFromServerPackage pack, ClientStruct client) {
			try {
				client.getObjectOutputStream().writeObject(pack);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void handle(AESKeyPackage pack, ClientStruct client) {
			try {
				client.getObjectOutputStream().writeObject(pack);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void handle(RegistrationPackage pack, ClientStruct client) {}
	}
	
	public static void resolveTypeAndHandle(Object pack, ClientStruct client){
		if(pack instanceof MessageFromClientPackage) {
			InputPackageHandler.handle((MessageFromClientPackage) pack, client);
		} else if(pack instanceof PublicKeyPackage) {
			InputPackageHandler.handle((PublicKeyPackage) pack, client);
		} else if(pack instanceof RegistrationPackage) {
			InputPackageHandler.handle((RegistrationPackage) pack, client);
		}
	}
}
