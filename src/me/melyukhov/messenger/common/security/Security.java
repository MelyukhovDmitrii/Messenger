package me.melyukhov.messenger.common.security;

import java.security.*;

import javax.crypto.*;

public class Security {
	
	public static Key generateKeyAES() {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(256);
			Key key = kgen.generateKey();
			key.getEncoded();
			System.out.println(key);
			return kgen.generateKey();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static KeyPair generatePairKeysRSA() {
		try {
			KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
			pairgen.initialize(2048);
			KeyPair keyPair = pairgen.generateKeyPair();
			return keyPair;
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] encryptMessageWithAESKey(byte[] message, Key keyAES) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, keyAES);
			byte[] encryptMessage = cipher.doFinal(message);
			return encryptMessage;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] encryptMessageWithRSAPublicKey(byte[] message, Key publicKey) {
		Cipher cipher;
		byte[] encryptMessage = null;
		try {
			cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			encryptMessage = cipher.doFinal(message);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		return encryptMessage;
	}
	
	public static byte[] decryptMessageWithAESKey(byte[] message, Key keyAES) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, keyAES);
			byte[] decryptMessage = cipher.doFinal(message);
			return decryptMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] decryptMessageWithRSAPrivateKey(byte[] message, Key privateKey) {
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] decryptMessage = cipher.doFinal(message);
			return decryptMessage;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
}