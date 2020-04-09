package objects;


import helpers.MathHelper;
import helpers.PaintHelper;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Gear implements Machine {
    private Point2D.Double center, pinion;
    private int speedInDegree, degree;
    double length;

    public Gear(Point2D.Double center, int length, int degree, int speedInDegree) {
        this.center = center;
        this.speedInDegree = speedInDegree;
        this.length = length;
        this.degree = degree;
        nextFrame();
    }

    public Point2D.Double getPinion() {
        return pinion;
    }

    @Override
    public void nextFrame() {
        degree += speedInDegree;
        if (degree > 360){
            degree -= 360;
        }
        pinion = MathHelper.pinion(center, length, degree);
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.DARK_GRAY);
        PaintHelper.drawLine(g, center, pinion);

        g.setColor(new Color(170,169,173));
        double radius = length + 10;
        PaintHelper.fillCircle(g,center,radius);

        radius = 5;
        g.setColor(new Color(70,71,62));
        PaintHelper.fillCircle(g,center,radius);
        PaintHelper.fillCircle(g,pinion, radius);
    }
}
