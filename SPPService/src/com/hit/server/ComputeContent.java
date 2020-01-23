package com.hit.server;
import java.util.UUID;

public class ComputeContent {
	public final UUID id;
	public int source;
	public int destination;
	public String algorithm;
	
	public ComputeContent(UUID id, int source, int destination, String algorithm) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.algorithm = algorithm;
	}
	
	
}
