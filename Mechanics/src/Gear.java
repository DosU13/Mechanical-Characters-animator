import java.awt.*;

public class Gear implements Machine{
    private Point center, pinion;
    private int speedInDegree, degree;
    double length;

    public Gear(Point center, int length, int degree, int speedInDegree) {
        this.center = center;
        this.speedInDegree = speedInDegree;
        this.length = length;
        this.degree = degree;
    }

    public Point getPinion() {
        return pinion;
    }

    @Override
    public void nextFrame() {
        degree += speedInDegree;
        if (degree > 360){
            degree -= 360;
        }
        pinion = Calculator.pinion(center, length, degree);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        g.drawLine(center.x,center.y,pinion.x,pinion.y);
    }
}
