package com.hit.graph.drawer.dm;
public class TwoWayEdge {
	private Edge edge1;
	private Edge edge2;
	int source;
	int destination;
	

	public TwoWayEdge(Edge edge1, Edge edge2, int source, int destination) {
		super();
		this.edge1 = edge1;
		this.edge2 = edge2;
		this.source = source;
		this.destination = destination;
	}

	public Edge getEdge1() {
		return edge1;
	}

	public Edge getEdge2() {
		return edge2;
	}

	public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}
	
	
}
