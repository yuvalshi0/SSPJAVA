package com.hit.drawer;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.hit.algorithm.IntegerWeightedEdge;
import com.hit.algorithm.IntegerWeightedGraph;
import com.hit.graph.AbstractWeightedEdge;


public class JGraphPanel extends JPanel {

    private IntegerWeightedGraph graph;
    private HashMap<Integer,Vertex> nodeMap;
    private ArrayList<Edge> edgeList;
    private ArrayList<TwoWayEdge> twoWayEdgeList;
    private int radius = 15;
    private final int width;
    private final int height;
    private boolean paint = false;
    int margin = 20;
	private LinkedList<Integer> path;

    public JGraphPanel(int width, int height) {	
        this.height = height - margin;
        this.width = width - margin;	
    	setBackground(Color.WHITE);
        setPreferredSize(new Dimension(width, height));      
    }
    
    public void drawGraph(IntegerWeightedGraph graph) {
    	this.graph = graph;
    	this.paint = true;
    	nodeMap = new HashMap<>();
        edgeList = new ArrayList<>();
        twoWayEdgeList = new ArrayList<>();
    	if(graph.getNodes() != null) drawNodes();
    	if(graph.getEdges() != null) drawEdges();
    }
    
    public void clearAndDrawGraph(IntegerWeightedGraph graph) {
    	clearGraph();
    	drawGraph(graph);
    }
    
    public void clearGraph() {
    	this.paint = false;
    	this.graph = null;
    	repaint();
    }
    
    public void clearPath() {
    	if(path != null && graph != null) {
            for(int i = 0; i < edgeList.size(); i++) {
            	edgeList.get(i).setColor(Color.BLACK);
            }
            
            for(int i = 0; i < twoWayEdgeList.size(); i++) {
            	twoWayEdgeList.get(i).getEdge1().setColor(Color.BLACK);
            	twoWayEdgeList.get(i).getEdge2().setColor(Color.BLACK);
            }
            
            for(int v : nodeMap.keySet()) {
            	nodeMap.get(v).setColor(Color.BLACK);
            }    
    	repaint();
    	}
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(paint == true) {
        for(int i = 0; i < edgeList.size(); i++) {
        	edgeList.get(i).draw(g,radius);
        }
        
        for(int i = 0; i < twoWayEdgeList.size(); i++) {
        	twoWayEdgeList.get(i).getEdge1().draw(g,radius);
        	twoWayEdgeList.get(i).getEdge2().draw(g,radius);
        }
        
        for(int v : nodeMap.keySet()) {
        	nodeMap.get(v).paint(g);
        }  
        }
    }
    
    private void drawNodes() {
    	int size = graph.getNodes().size();
    	int intervalX = (int) (this.width/Math.sqrt(size+1));
    	int intervalY = (int) (this.height/Math.sqrt(size));
    	int nodesInLine = (int) Math.floor(Math.sqrt(size));
    	int counter = 0;
    	int offsetX = margin;
    	int offestY = margin;
    	for(int node : graph.getNodes()) {
    		if(counter > nodesInLine) {			
    			offestY += intervalY;
    			offsetX = margin+1;
    			counter = 0;
    		} 
    		addCircle(offsetX , offestY, radius, node);
    		offsetX += intervalX;
    		counter++;
    	}
    }
    
    private void drawEdges() {
   	 for(AbstractWeightedEdge<Integer, Integer> edge : graph.getEdges()) {
   		 int source = edge.getSource();
   		 int destination = edge.getDest();
   		 int weight = edge.getWeight();
   		 
   		 int sourceX = nodeMap.get(source).getXCenter();
   		 int sourceY = nodeMap.get(source).getYCenter();
   		 
   		 int destX = nodeMap.get(destination).getXCenter();
   		 int destY = nodeMap.get(destination).getYCenter();
   	   	 if(!checkifInverted(edge)) {
   	   		 edgeList.add(new Edge(sourceX,sourceY,destX,destY,weight,source,destination,Color.black));
   	   	 } else {
   	   		 if(!checkIfTwoWayExists(edge))
   	   			 twoWayEdgeList.add(
   	   	   				 new TwoWayEdge(
   	   	   						 new Edge(sourceX+7,sourceY+7,destX+7,destY+7,weight,source,destination,Color.black),
   	   	   						 new Edge(destX-7,destY-7,sourceX-7,sourceY-7,getInvertedEdge(edge).getWeight(),destination,source,Color.black), 						
   	   	   						 source,
   	   	   						 destination));
   	   		 }   	   		
   	   	 }   		
   	 }
    
    
    private boolean checkifInverted(AbstractWeightedEdge<Integer, Integer> edgeToCheck) {
    	for(AbstractWeightedEdge<Integer, Integer> edge : graph.getEdges()) {
    		if((edge.getSource() == edgeToCheck.getDest()) && (edge.getDest() == edgeToCheck.getSource())) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private boolean checkIfTwoWayExists(AbstractWeightedEdge<Integer, Integer> edgeToCheck) {
    	for(TwoWayEdge x : twoWayEdgeList) {
	   			 if((x.getSource() == edgeToCheck.getSource() && x.getDestination() ==  edgeToCheck.getDest()) ||
	   				x.getSource() == edgeToCheck.getDest() && x.getDestination() == edgeToCheck.getSource()) {
	   				 return true;
	   			 }
	   			
    }
    	return false;
    }
    
    private AbstractWeightedEdge<Integer, Integer> getInvertedEdge(AbstractWeightedEdge<Integer, Integer> edgeToCheck) {
    	for(AbstractWeightedEdge<Integer, Integer> edge : graph.getEdges()) {
    		if((edge.getSource() == edgeToCheck.getDest()) && (edge.getDest() == edgeToCheck.getSource())) {
    			return edge;
    		}
    	}
    	return null;
    }

    public void addCircle(int x, int y, int radius, int label) {
        Vertex v = new Vertex(x,y,radius,label,Color.black);
    	nodeMap.put(label, v);
        repaint();
    }
    
    public void drawPath(LinkedList<Integer> path, Color color) {
    	if(this.graph != null) {
    		this.path = path;
    		for(int i = 0; i < path.size()-1; i++) {
    		int v = path.get(i);
    		nodeMap.get(v).setColor(color);
    		Edge edge = searchForEdge(path.get(i), path.get(i+1));	
    		edge.setColor(color);
    		
    	}
    	nodeMap.get(path.getLast()).setColor(color);
    	repaint();
    	}
    }
    	//TODO: ADD ERROR CHECK
    private Edge searchForEdge(int source, int destination) {
    	for(Edge edge : edgeList) {
    		if(isAMatch(edge, source, destination)) {
    			return edge;
    		}	
    	}
    	for(TwoWayEdge twoWayedge : twoWayEdgeList) {
    		if(isAMatch(twoWayedge.getEdge1(), source, destination)) {
    			return twoWayedge.getEdge1();
    		}
    		if(isAMatch(twoWayedge.getEdge2(), source, destination)) {
    			return twoWayedge.getEdge2();
    		}
    	}
    	return null;
    }
    
    public boolean isAMatch(Edge edge ,int source, int destination) {
    	if((edge.getSource() == source) && (edge.getDestination() == destination)) {
    		return true;
		}
    	return false;
    }
  
}