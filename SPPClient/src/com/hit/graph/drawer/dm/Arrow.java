package com.hit.graph.drawer.dm;

import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

public enum Arrow {;

  public static void draw (final Graphics2D gfx, final Point2D.Double start, final Point2D.Double end, final Stroke lineStroke, final Stroke arrowStroke, final float arrowSize, final int radius) {

    final double startx = start.getX();
    final double starty = start.getY();
    gfx.setStroke(arrowStroke);
    final double deltax = startx - end.getX();
    final double result;
    if (deltax == 0.0d) {
      result = Math.PI / 2;
    }
    else {
      result = Math.atan((starty - end.getY()) / deltax) + (startx < end.getX() ? Math.PI : 0);
    }

    final double angle = result;
    final double arrowStartx = end.getX() + radius*Math.cos(angle);
    final double arrowStarty = end.getY() + radius*Math.sin(angle);
    final double arrowAngle = Math.PI / 5.0d;

    final double x1 = arrowSize * Math.cos(angle - arrowAngle);
    final double y1 = arrowSize * Math.sin(angle - arrowAngle);
    final double x2 = arrowSize * Math.cos(angle + arrowAngle);
    final double y2 = arrowSize * Math.sin(angle + arrowAngle);

    final double cx = (arrowSize / 2.0f) * Math.cos(angle);
    final double cy = (arrowSize / 2.0f) * Math.sin(angle);
    
    final GeneralPath polygon = new GeneralPath();
    polygon.moveTo(arrowStartx, arrowStarty);
    polygon.lineTo(arrowStartx + x1, arrowStarty + y1);
    polygon.lineTo(arrowStartx + x2, arrowStarty + y2);
    polygon.closePath();
    gfx.fill(polygon);

    gfx.setStroke(lineStroke);
    gfx.drawLine((int) startx, (int) starty, (int) (end.getX() + cx), (int) (end.getY() + cy));
  }

}