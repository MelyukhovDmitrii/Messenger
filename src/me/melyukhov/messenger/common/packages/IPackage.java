package me.melyukhov.messenger.common.packages;

import java.io.Serializable;

public interface IPackage<T> extends Serializable {
	public T getContent();
	public void setContent(T content);
}