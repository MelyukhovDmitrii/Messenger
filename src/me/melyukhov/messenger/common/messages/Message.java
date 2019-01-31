package me.melyukhov.messenger.common.messages;

public class Message<T> implements IMessage<T> {

	private static final long serialVersionUID = -4999395120826362332L;
	
	private T message;
	
	public Message() {
		
	}
	
	public Message(T message) {
		this.message = message;
	}
	
	@Override
	public T getMessage() {
		return message;
	}

	@Override
	public void setMessage(T message) {
		this.message = message;
	}

}
