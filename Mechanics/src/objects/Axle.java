package objects;


import java.awt.*;
import java.awt.geom.Point2D;

public class Axle implements Machine {
    private Point2D.Double axle;

    public Axle(Point2D.Double axle) {
        this.axle = axle;
    }

    public Point2D.Double getPinion() {
        return axle;
    }

    @Override
    public void nextFrame() {

    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        double radius = 5;
        g.setColor(new Color(70,71,62));
        helpers.PaintHelper.fillCircle(g,axle,radius);
    }
}
