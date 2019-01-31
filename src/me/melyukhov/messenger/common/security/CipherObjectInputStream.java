package me.melyukhov.messenger.common.security;

import me.melyukhov.messenger.common.messages.AESKeyMessage;
import me.melyukhov.messenger.common.packages.AESKeyPackage;
import me.melyukhov.messenger.common.packages.PackWithByteArray;
import me.melyukhov.messenger.common.security.Security;
import me.melyukhov.messenger.common.security.SecurityState;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;

import static me.melyukhov.messenger.common.security.SecurityState.DEFAULT;

public class CipherObjectInputStream {

	private SecurityState state = DEFAULT;
	private ObjectInputStream objectInputStream;
	private Key keyAES;
	private Key privateKey;
	
	public CipherObjectInputStream(InputStream inputStream) throws IOException {
		this.objectInputStream = new ObjectInputStream(inputStream);
	}
	
	public void setKeyAES(Key keyAES) {
		this.keyAES = keyAES;
	}
	
	public void setState(SecurityState state) {
		this.state = state;
	}
	
	public SecurityState getState() {
		return state;
	}
	
	public void setPrivateKey(Key privateKey) {
		this.privateKey = privateKey;
	}
	
	private byte[] decode(byte[] input) {
		switch (state) {
		case DEFAULT:
			return input;
		case RSA:
			return Security.decryptMessageWithRSAPrivateKey(input, privateKey);
		case AES:
			return Security.decryptMessageWithAESKey(input, keyAES);
		default:
			return null;
		}
	}
	
	public void close() throws IOException {
		objectInputStream.close();
	}

	public Object readObject() throws IOException, ClassNotFoundException {
		PackWithByteArray pack = (PackWithByteArray) this.objectInputStream.readObject();
		switch (state){
			case DEFAULT:
				ByteArrayInputStream in = new ByteArrayInputStream(decode(pack.getContent()));
				ObjectInputStream is = new ObjectInputStream(in);
				return is.readObject();
			case RSA:
				ByteArrayInputStream in1 = new ByteArrayInputStream(decode(pack.getContent()));
				ObjectInputStream is1 = new ObjectInputStream(in1);
				AESKeyPackage aesKeyPackage = new AESKeyPackage(new AESKeyMessage((Key) is1.readObject()));
				return aesKeyPackage;
			case AES:
				ByteArrayInputStream in2 = new ByteArrayInputStream(decode(pack.getContent()));
				ObjectInputStream is2 = new ObjectInputStream(in2);
				return is2.readObject();
			default:
				return null;
		}
	}
}
