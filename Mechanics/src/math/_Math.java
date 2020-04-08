package math;

import java.awt.*;

public class _Math {
    static double distance(Point x, Point y){
        double res;
        res = Math.sqrt(Math.pow((x.getX() - y.getX()),2) + Math.pow((x.getY() - y.getY()),2));
        return res;
    }

    public static Point pinion(Point center, double length, int degree) {
        double x = center.getX() + length*Math.cos(Math.toRadians(degree));
        double y = center.getY() + length*Math.sin(Math.toRadians(degree));
        return new Point((int)x,(int)y);
    }

    public static Point getJoint(double lenToTheJoint, double secondLen, Point firstPin, Point secondPin, int side){
        Point joint = new Point();
        double a = lenToTheJoint;
        double b = secondLen;
        double l = _Math.distance(firstPin, secondPin);
        double x1 = firstPin.getX();
        double x2 = secondPin.getX();
        double y1 = firstPin.getY();
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

    public static Point getMainPin(Point firstPin , Point joint, double firstLen, double lenToTheJoint){
        Point mainPin = new Point();
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
