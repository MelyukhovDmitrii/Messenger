package me.melyukhov.messenger.common.security;

import me.melyukhov.messenger.common.packages.AESKeyPackage;
import me.melyukhov.messenger.common.packages.PackWithByteArray;
import me.melyukhov.messenger.common.security.Security;
import me.melyukhov.messenger.common.security.SecurityState;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.Key;

import static me.melyukhov.messenger.common.security.SecurityState.DEFAULT;

public class CipherObjectOutputStream {
	
	private SecurityState state = DEFAULT;
	private ObjectOutputStream objectOutputStream;
	private Key keyAES;
	private Key publicKey;
	
	public CipherObjectOutputStream(OutputStream outputStream) throws IOException {
		this.objectOutputStream = new ObjectOutputStream(outputStream);
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
	
	public void setPublicKey(Key publicKey) {
		this.publicKey = publicKey;
	}
	
	private byte[] encode(byte[] input) {
		switch (state) {
		case DEFAULT:
			return input;
		case RSA:
			return Security.encryptMessageWithRSAPublicKey(input, publicKey);
		case AES:
			return Security.encryptMessageWithAESKey(input, keyAES);
		default:
			return null;
		}
	}
	
	public void flush() throws IOException {
		objectOutputStream.flush();
	}
	
	private byte[] serialize(Object obj) throws IOException {
	    ByteArrayOutputStream out = new ByteArrayOutputStream();
	    ObjectOutputStream os = new ObjectOutputStream(out);
	    os.writeObject(obj);
	    return out.toByteArray();
	}
	
	public void close() throws IOException {
		objectOutputStream.close();
	}
	
	public void writeObject(Object object) throws IOException {
		switch (state) {
			case DEFAULT:
				PackWithByteArray defaultPackage = new PackWithByteArray(encode(serialize(object)));
				objectOutputStream.writeObject(defaultPackage);
				break;
			case RSA:
				AESKeyPackage aesKeyPackage = (AESKeyPackage) object;
				PackWithByteArray packWithByteArrayKey = new PackWithByteArray(encode(serialize((aesKeyPackage.getContent().getMessage()))));
				objectOutputStream.writeObject(packWithByteArrayKey);
				break;
			case AES:
				PackWithByteArray encryptWihAESPackage = new PackWithByteArray(encode(serialize(object)));
				objectOutputStream.writeObject(encryptWihAESPackage);
				break;
			default:
				return;
		}

	}
	
}
