package main.java;

import java.util.Set;

abstract class AbstractNode<T> implements INode<T> {
	private T item;
	private Set<IEdge<T>> edges;
}
