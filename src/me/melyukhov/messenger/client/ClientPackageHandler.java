package me.melyukhov.messenger.client;

import java.io.IOException;
import java.util.Arrays;

import me.melyukhov.messenger.common.packages.AESKeyPackage;
import me.melyukhov.messenger.common.packages.LastMessagesFromServerPackage;
import me.melyukhov.messenger.common.packages.MessageFromClientPackage;
import me.melyukhov.messenger.common.packages.PrivateKeyPackage;
import me.melyukhov.messenger.common.packages.PublicKeyPackage;
import me.melyukhov.messenger.common.packages.RegistrationPackage;
import me.melyukhov.messenger.common.ClientStruct;

public class ClientPackageHandler {

	private static class InputPackageHandler {
		
		private static void handle(LastMessagesFromServerPackage pack, ClientStruct client) {
			Arrays.stream(pack.getContent()).forEach((p)->{
				ClientMessageHandler.InputHandle.handle(p, client);
			});
		}
		
		private static void handle(RegistrationPackage pack, ClientStruct client) {}
		private static void handle(PublicKeyPackage pack, ClientStruct client) {}
		private static void handle(PrivateKeyPackage pack, ClientStruct client) {}
		
		private static void handle(AESKeyPackage pack, ClientStruct client) {
			ClientMessageHandler.InputHandle.handle(pack.getContent(), client);
		}
		
		private static void handle(MessageFromClientPackage pack, ClientStruct client) {}
	}
	
	public static class OutputPackageHandle {
		
		public static void handle(MessageFromClientPackage pack, ClientStruct client) {
			try {
				client.getObjectOutputStream().writeObject(pack);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void handle(RegistrationPackage pack, ClientStruct client) {
			try {
				client.getObjectOutputStream().writeObject(pack);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public static void handle(PublicKeyPackage pack, ClientStruct client) {
			try {
				client.getObjectOutputStream().writeObject(pack);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public static void handle(PrivateKeyPackage pack, ClientStruct client) {
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
	}
	
	public static void resolveTypeAndHandle(Object pack, ClientStruct client){
		if(pack instanceof LastMessagesFromServerPackage) {
			InputPackageHandler.handle((LastMessagesFromServerPackage) pack, client);
		} else if(pack instanceof AESKeyPackage) {
			InputPackageHandler.handle((AESKeyPackage) pack, client);
		} else if(pack instanceof PrivateKeyPackage) {
			InputPackageHandler.handle((PrivateKeyPackage) pack, client);
		} else if(pack instanceof PublicKeyPackage) {
			InputPackageHandler.handle((PublicKeyPackage) pack, client);
		} else if(pack instanceof RegistrationPackage) {
			InputPackageHandler.handle((RegistrationPackage) pack, client);
		} else if(pack instanceof MessageFromClientPackage) {
			InputPackageHandler.handle((MessageFromClientPackage) pack, client);
		}
	}
	
}
