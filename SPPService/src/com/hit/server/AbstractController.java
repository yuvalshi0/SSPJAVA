package com.hit.server;

abstract class AbstractController<T> {
	public void setUp() {
		createServices();
	};
	
	public abstract void createServices();
	
}
