package me.melyukhov.messenger.client;

import me.melyukhov.messenger.common.security.SecurityState;
import me.melyukhov.messenger.common.messages.*;
import me.melyukhov.messenger.common.packages.MessageFromClientPackage;
import me.melyukhov.messenger.common.packages.PublicKeyPackage;
import me.melyukhov.messenger.common.packages.RegistrationPackage;
import me.melyukhov.messenger.common.ClientStruct;

public class ClientMessageHandler {
	
	public static class OutputHandle {

		public static void handle(PublicKeyMessage message, ClientStruct client) {
			PublicKeyPackage publicKeyPackage = new PublicKeyPackage(message);
			ClientPackageHandler.OutputPackageHandle.handle(publicKeyPackage, client);
			client.setState(SecurityState.RSA);
		}

		public static void handle(TextMessage message, ClientStruct client) {
			MessageFromClientPackage messageFromClientPackage = new MessageFromClientPackage(message);
			ClientPackageHandler.OutputPackageHandle.handle(messageFromClientPackage, client);
		}
		
		public static void handle(UserNameMessage message, ClientStruct client) {
			ClientPackageHandler.OutputPackageHandle.handle(new RegistrationPackage(new UserNameAndPass(message)), client);
		}

		public static void handle(PasswordMessage message, ClientStruct client) {}
		
		public static void handle(UserNameAndPass userNameAndPass, ClientStruct client) {
			RegistrationPackage registrationPackage = new RegistrationPackage(userNameAndPass);
			ClientPackageHandler.OutputPackageHandle.handle(registrationPackage, client);
		}

	}
	
	public static class InputHandle {
		
		public static void handle(AESKeyMessage message, ClientStruct client) {
			client.setKeyAES(message.getMessage());
			client.setState(SecurityState.AES);
		}
		
		public static void handle(TextMessage message, ClientStruct client) {

		}

		public static void handle(UserNameAndTextMessage message, ClientStruct client) {
			DataContainer.getInstance().add(message);
		}
		
		public static void handle(UserNameMessage message, ClientStruct client) {
			ClientApp.window.sendMessageToWindow(message.getMessage() + " : ");
		}

		public static void handle(PasswordMessage message, ClientStruct client) {}		
	}
	
}
