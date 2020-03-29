import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TestMain {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello My Dear Friend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);

        Painter painter = new Painter();

        Gear bodyGear = new Gear(new Point(100,300), 100, 0, 8);
        Axle bodyAxle = new Axle(new Point(300,300));

        Shaft bodyShaft = new Shaft(360,360,173,-1);

        ArrayList<Machine> machines = new ArrayList<>(Arrays.asList(
                bodyGear, bodyAxle, bodyShaft
        ));
        painter.addMachines(machines);


        while (true) {
            try {
                Thread.sleep(40);
            } catch (Exception exc) {
                exc.fillInStackTrace();
            }

            bodyShaft.setPins(bodyGear.getPinion(), bodyAxle.getPinion());
            frame.repaint();
            frame.add(painter);
            frame.setVisible(true);
        }
    }
}
