package objects;

import math._Math;

import java.awt.*;
import java.util.ArrayList;

public class ShiftRod implements Machine {
    private Point firstPin, secondPin, joint, mainPin;  // first one is longer
    private int lenToTheJoint, firstLen, secondLen, side;
    private ArrayList<Point> points;
    private Boolean isNan;

    public ShiftRod(int lenToTheJoint, int firstLen, int secondLen, int side) {
        this.lenToTheJoint = lenToTheJoint;
        this.firstLen = firstLen;
        this.secondLen = secondLen;
        this.side = side;
        joint = new Point();
        mainPin = new Point();
        points = new ArrayList<>();
        isNan = false;
    }

    public void setPins(Point firstPin, Point secondPin) {
        this.firstPin = firstPin;
        this.secondPin = secondPin;
    }

    public Point getMainPin() {
        return mainPin;
    }

    public void nextFrame() {
        joint = _Math.getJoint(lenToTheJoint,secondLen,firstPin,secondPin,side);
        isNan = joint == null;
        if (!isNan) mainPin = _Math.getMainPin(firstPin, joint,firstLen,lenToTheJoint);
        else mainPin = null;
        if (mainPin!=null){
            isNan = false;
        }
        else isNan = true;
        if (!isNan) points.add(new Point(mainPin));
    }

    public void nextFrameNew(){
        double x1 = firstPin.getX();
        double x2 = secondPin.getX();
        double y1 = firstPin.getY();
        double y2 = secondPin.getY();
        double a = lenToTheJoint;
        double b = secondLen;
        double x = 0, y = 0;
        // We must find x, y
        // (x-x1)^2 + (y-y1)^2 = a^2
        // (x-x2)^2 + (y-y2)^2 = b^2
        if (y1 == y2){
            // if we subtract they:
            // 2*x(x2-x1) + x1*x1-x2*x2 = a*a - b*b
            x = (a*a-b*b-x1*x1+x2*x2)/2/(x2-x1);
            y = Math.sqrt(a*a - (x-x1)*(x-x1)) + y1;
        }
        else {
            // if we subtract they:
            // 2*x(x2-x1) + 2*y(y2-y1) = a^2 - b^2 + x2^2 - x1^2 + y2^2 - y1^2
            // y = -x*(x2-x1)/(y2-y1) + (a^2 - b^2 + x2^2 - x1^2 + y2^2 - y1^2)/(y2-y1)
            double t1 = -1 * (x2 - x1) / (y2 - y1);
            double t2 = (a * a - b * b + x2 * x2 - x1 * x1 + y2 * y2 - y1 * y1) / (y2 - y1);
            // So y = x*t1 + t2
            // (x-x1)^2 + (y-y1)^2 = a^2 replace y
            // (x-x1)^2 + (x*t1 + t2 - y1)^2 = a^2
            t2 = t2 - y1;
            // (x-x1)^2 + (x*t1 + t2)^2 = a^2
            // x^2 - 2*x1*x + x1^2 + t1^2*x^2 + 2*t1*t2*x + t2^2-a^2 = 0
            double t = t1 * t1 + 1;
            t1 = -2 * x1 + 2 * t1 * t2;
            t2 = x1 * x1 + t2 * t2 - a * a;
            // t*x^2 + t1*x + t2 = 0;
            System.out.println(t + " " + t1 + " " + t2);
            x = (-1 * t1 + side * Math.sqrt(t1 * t1 - 4 * t * t2)) / 2 * t;
            y = -x*(x2-x1)/(y2-y1) + (a*a - b*b + x2*x2 - x1*x1 + y2*y2 - y1*y2)/(y2-y1);
        }
        if (!Double.isNaN(x) && !Double.isNaN(y)) {
            joint.setLocation(x,y);
            isNan = false;
        }
        else isNan = true;
        double xm = x1 + (x - x1) * firstLen / a;
        double ym = y1 + (y - y1) * firstLen / a;
        mainPin.setLocation(xm, ym);
        points.add(new Point(mainPin));
    }

    @Override
    public void paint(Graphics g) {
        if (isNan){
            g.drawString("Nan is found. It is error man!!!", 800,500);
        }
        else {
            g.setColor(Color.DARK_GRAY);
            g.drawLine(firstPin.x, firstPin.y, mainPin.x, mainPin.y);
            g.drawLine(secondPin.x, secondPin.y, joint.x, joint.y);
            g.drawLine(firstPin.x, firstPin.y, joint.x, joint.y);
            g.setColor(Color.RED);

            for (int i = 0; i < points.size(); i++) {
                Point p = points.get(i);
                if (i > 0) {
                    g.drawLine(p.x, p.y, p.x, p.y);
                }
            }
        }
    }
}
