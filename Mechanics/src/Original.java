import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Original {


    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello My Dear Friend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);

        Painter painter = new Painter();

        Gear bodyGear = new Gear(new Point(200,270), 4, 90, 8);
        Axle bodyAxle = new Axle(new Point(241,276));     //y = 282
        Gear firstLegGear = new Gear(new Point(246,334), 12,270,4);
        Gear secondLegGear = new Gear(new Point(246,334), 12,90,4);
        Axle legAxle = new Axle(new Point(278,334));
        Axle handAxle = new Axle(new Point(200,200));

        Shaft bodyShaft = new Shaft(40,87,37,-1);
        Shaft firstLegShaft = new Shaft(40,76,40,-1);
        Shaft secondLegShaft = new Shaft(40,76,40,-1);
        Shaft body = new Shaft(24,-14,49,-1);
        Shaft firstLeg = new Shaft(34,34,37,1);
        Shaft secondLeg = new Shaft(34,34,37,1);

        ArrayList<Machine> machines = new ArrayList<>(Arrays.asList(
                bodyGear, bodyAxle, bodyShaft,
                firstLegGear, secondLegGear, legAxle, firstLegShaft, secondLegShaft,
                handAxle, body, firstLeg, secondLeg
        ));
        painter.addMachines(machines);


        while (true) {
            try {
                Thread.sleep(40);
            } catch (Exception exc) {
                exc.fillInStackTrace();
            }

            bodyShaft.setPins(bodyGear.getPinion(), bodyAxle.getPinion());
            firstLegShaft.setPins(firstLegGear.getPinion() , legAxle.getPinion());
            secondLegShaft.setPins(secondLegGear.getPinion() , legAxle.getPinion());
            body.setPins(bodyShaft.getMainPin(),handAxle.getPinion());
            firstLeg.setPins(body.getMainPin(),firstLegShaft.getMainPin());
            secondLeg.setPins(body.getMainPin(),secondLegShaft.getMainPin());
            frame.repaint();
            frame.add(painter);
            frame.setVisible(true);
        }
    }
}
