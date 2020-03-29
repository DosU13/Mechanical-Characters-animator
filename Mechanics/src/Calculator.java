import java.awt.*;

class Calculator{
    static double distance(Point x, Point y){
        double res;
        res = Math.sqrt(Math.pow((x.getX() - y.getX()),2) + Math.pow((x.getY() - y.getY()),2));
        return res;
    }

    static Point pinion(Point center, double length, int degree) {
        double x = center.getX() + length*Math.cos(Math.toRadians(degree));
        double y = center.getY() + length*Math.sin(Math.toRadians(degree));
        return new Point((int)x,(int)y);
    }
}
