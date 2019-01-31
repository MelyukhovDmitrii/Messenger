package me.melyukhov.messenger.common.packages;

public class Package<T> implements IPackage<T> {

	private static final long serialVersionUID = 4485236224262882109L;
	
	private T content;
	
	public Package() {
		
	}
	
	public Package(T content) {
		this.content = content;
	}
	
	@Override
	public T getContent() {
		return content;
	}

	@Override
	public void setContent(T content) {
		this.content = content;
	}
}
