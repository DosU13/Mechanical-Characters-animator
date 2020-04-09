package helpers;


import java.awt.geom.Point2D;

public class MathHelper {
    static double distance(Point2D.Double x, Point2D.Double y){
        double res = 0;
        try {res = Math.sqrt(Math.pow((x.getX() - y.getX()),2) + Math.pow((x.getY() - y.getY()),2));}
        catch (NullPointerException e){
            e.fillInStackTrace();
        }
        return res;
    }

    public static Point2D.Double pinion(Point2D.Double center, double length, int degree) {
        double x = center.getX() + length*Math.cos(Math.toRadians(degree));
        double y = center.getY() + length*Math.sin(Math.toRadians(degree));
        return new Point2D.Double((int)x,(int)y);
    }

    public static Point2D.Double getJoint(double lenToTheJoint, double secondLen,
                                          Point2D.Double firstPin, Point2D.Double secondPin, int side){
        Point2D.Double joint = new Point2D.Double();
        double a = lenToTheJoint;
        double b = secondLen;
        double l = MathHelper.distance(firstPin, secondPin);
        double x1 = 0 , y1 = 0;
        try {
            x1 = firstPin.getX();
            y1 = firstPin.getY();
        }
        catch (NullPointerException e){ e.fillInStackTrace();}
        double x2 = secondPin.getX();
        double y2 = secondPin.getY();
        double h = b * b - Math.pow(((l * l + b * b - a * a) / 2 / l), 2);
        h = Math.sqrt(h);
        double disFromFirstToH = Math.sqrt(a * a - h * h);
        double xh = x1 + disFromFirstToH * (x2 - x1) / l;
        double yh = y1 + disFromFirstToH * (y2 - y1) / l;
        double t = -1 * (y1 - yh) / (x1 - xh);
        double t2 = side * Math.sqrt(h * h / (t * t + 1));
        double yj = t2 + yh;
        double xj = xh + t * t2;
        if (side==1){
            double t3 = -1*t2;
            double xj2 = xh + t*t3;
            if (xj2 < xj){
                xj = xj2;
                yj = t3 + yh;
            }
        }
        if (!Double.isNaN(xj) && !Double.isNaN(yj)) {
            joint.setLocation(xj, yj);
            return joint;
        }
        else return null;
    }

    public static Point2D.Double getMainPin(Point2D.Double firstPin , Point2D.Double joint, double firstLen, double lenToTheJoint){
        Point2D.Double mainPin = new Point2D.Double();
        double x1 = firstPin.getX();
        double y1 = firstPin.getY();
        double xj = joint.getX();
        double yj = joint.getY();
        double xm = x1 + (xj - x1) * firstLen / lenToTheJoint;
        double ym = y1 + (yj - y1) * firstLen / lenToTheJoint;
        if (!Double.isNaN(xm) && !Double.isNaN(ym)) {
            mainPin.setLocation(xm, ym);
            return mainPin;
        }
        else return null;
    }

}
