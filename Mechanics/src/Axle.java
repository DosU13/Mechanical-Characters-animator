import java.awt.*;

public class Axle implements Machine {
    private Point axle;

    public Axle(Point axle) {
        this.axle = axle;
    }

    public Point getPinion() {
        return axle;
    }

    @Override
    public void nextFrame() {

    }

    @Override
    public void paint(Graphics g) {

    }
}
