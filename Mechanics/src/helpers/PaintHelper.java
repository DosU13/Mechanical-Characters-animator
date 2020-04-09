package helpers;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class PaintHelper {
    public static void drawLine(Graphics2D graphics2D, Point2D.Double firstPoint, Point2D.Double secondPoint){
        graphics2D.draw(new Line2D.Double(firstPoint,secondPoint));
    }

    public static void fillCircle(Graphics2D graphics2D, Point2D.Double center, double radius){
        graphics2D.fill(new Ellipse2D.Double(center.getX()-radius,center.getY()-radius,2*radius,2*radius));
    }
}
