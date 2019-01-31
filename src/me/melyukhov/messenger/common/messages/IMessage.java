package me.melyukhov.messenger.common.messages;

import java.io.Serializable;

public interface IMessage<T> extends Serializable {
	public T getMessage();
	public void setMessage(T message);	
}
