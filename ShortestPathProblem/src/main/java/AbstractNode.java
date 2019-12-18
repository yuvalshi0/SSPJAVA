package main.java;

abstract class AbstractNode<T> implements INode<T> {
	private T item;
	
	public AbstractNode(T item) {
		this.item = item;
	}
}
