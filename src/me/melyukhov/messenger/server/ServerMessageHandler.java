package me.melyukhov.messenger.server;

import me.melyukhov.messenger.common.ClientStruct;
import me.melyukhov.messenger.common.security.SecurityState;
import me.melyukhov.messenger.common.messages.*;
import me.melyukhov.messenger.common.packages.AESKeyPackage;
import me.melyukhov.messenger.common.packages.LastMessagesFromServerPackage;

public class ServerMessageHandler {

	public static class InputMessageHandle {

		public static void handle(PublicKeyMessage message, ClientStruct client) {
			client.setPublicKey(message.getMessage());
		}

		public static void handle(TextMessage message, ClientStruct client) {
			ServerDataContainer.add(new UserNameAndTextMessage(new UserNameMessage(client.getUserName()), message));
		}

		public static void handle(UserNameMessage message, ClientStruct client) {
			client.setUserName(message.getMessage()); 
		}
		
		public static void handle(PasswordMessage message, ClientStruct client) {}
	}
	
	public static class OutputMessageHandle {
		
		public static void handle(UserNameAndTextMessage[] userNameAndTextMessage, ClientStruct client) {
			LastMessagesFromServerPackage lastMessagesFromServerPackage = new LastMessagesFromServerPackage(userNameAndTextMessage);
			ServerPackageHandler.OutputPackageHandler.handle(lastMessagesFromServerPackage, client);
		}
		
		public static void handle(AESKeyMessage message, ClientStruct client) {
			client.setState(SecurityState.RSA);
			AESKeyPackage aesKeyPackage = new AESKeyPackage(message);
			ServerPackageHandler.OutputPackageHandler.handle(aesKeyPackage, client);
			client.setState(SecurityState.AES);
		}
		
		public static void handle(PasswordMessage message, ClientStruct client) {}
		public static void handle(PrivateKeyMessage message, ClientStruct client) {}
		public static void handle(PublicKeyMessage message, ClientStruct client) {}
		public static void handle(TextMessage message, ClientStruct client) {}
		public static void handle(UserNameMessage message, ClientStruct client) {}
	}
}