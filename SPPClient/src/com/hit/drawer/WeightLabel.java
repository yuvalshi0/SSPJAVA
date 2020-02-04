package com.hit.drawer;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

public class WeightLabel {
	int x, y, width, height;
    int size;
    int label;
    Color color;
   
    public WeightLabel(int x, int y,int radius,int label, Color color) {
        this.x = x;
        this.y = y;
        this.label = label;
        this.size = radius;
        this.color = color;
    }
    
    public void setColor(Color color) {
    	this.color = color;
    }

    public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public int getXCenter() {
		return x+size/2;
	}

	public int getYCenter() {
		return y+size/2;
	}
		
	public void draw(Graphics g) {
    	 Graphics2D g2 = (Graphics2D) g;
    	 g2.setRenderingHint(
    		        RenderingHints.KEY_ANTIALIASING,
    		        RenderingHints.VALUE_ANTIALIAS_ON);
    	 g2.setPaint(Color.white);
    	 g2.fill(new Rectangle2D.Double(x, y, size, size));  
    	 g2.setPaint(color);
    	 g2.setStroke(new BasicStroke(1.1f,BasicStroke.CAP_SQUARE,BasicStroke.JOIN_ROUND));
    	 drawCenteredString(g2);
    	 g2.draw(new Rectangle2D.Double(x, y, size, size));

    }
    
    public void drawCenteredString(Graphics g) {
    	Font font = new Font("Arial", Font.BOLD, 12);
        FontMetrics metrics = g.getFontMetrics(font);
        int x1 = x + (size - metrics.stringWidth(String.valueOf(label))) / 2;
        int y1 = y + ((size - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(String.valueOf(label), x1, y1);
    }
}
