package com.hit.drawer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.Point2D;

public class Edge {
	        public int getSource() {
		return source;
	}

	public int getDestination() {
		return destination;
	}

			private final int x;
	        private final int y;
	        private final int endX;
	        private final int endY;
	        private final int label;
	        private final int source;
	        private final int destination;
	        private Color color;
	        
	        public Edge(int x, int y, int x2, int y2, int label, int source, int destination,Color color) {
	            super();
	            this.x = x;
	            this.y = y;
	            this.endX = x2;
	            this.endY = y2;
	            this.label = label;
	            this.source = source;
	            this.destination = destination;
	            this.color = color;
	        }
	        
	        public void setColor(Color color) {
	        	this.color = color;
	        }
	        
	        public void draw(Graphics g,int radius) {
	        	Graphics2D g2 = (Graphics2D) g;
	        	 g2.setRenderingHint(
	     		        RenderingHints.KEY_ANTIALIASING,
	     		        RenderingHints.VALUE_ANTIALIAS_ON);
	        	Stroke x2 = new BasicStroke(1);   
	        	g2.setPaint(color);
	        	Arrow.draw(g2, new Point2D.Double(x, y), new Point2D.Double(endX, endY), x2, x2,10f,radius);
	        	drawLabel(g);
	        }
	        
	        public void drawLabel(Graphics g) {
	        	Graphics2D g2 = (Graphics2D) g;
	        	new WeightLabel((x+endX)/2-5,(y+endY)/2-5, 15, label,color).draw(g2);
	        }
	    }
