package main;

import objects.Axle;
import objects.Gear;
import objects.Machine;
import objects.ShiftRod;
import paint.Painter;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;

public class TestMain {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello My Dear Friend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);

        Painter painter = new Painter();

        Gear bodyGear = new Gear(new Point2D.Double(100,300), 100, 0, 8);
        Axle bodyAxle = new Axle(new Point2D.Double(300,300));

        ShiftRod bodyShiftRod = new ShiftRod(360,360,173,-1);

        ArrayList<Machine> machines = new ArrayList<>(Arrays.asList(
                bodyGear, bodyAxle, bodyShiftRod
        ));
        painter.addMachines(machines);


        while (true) {
            try {
                Thread.sleep(40);
            } catch (Exception exc) {
                exc.fillInStackTrace();
            }

            bodyShiftRod.setPins(bodyGear.getPinion(), bodyAxle.getPinion());
            frame.repaint();
            frame.add(painter);
            frame.setVisible(true);
        }
    }
}
