package main;

import objects.Axle;
import objects.Gear;
import objects.Machine;
import objects.ShiftRod;
import paint.Painter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static  final  int x = 3;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hello My Dear Friend");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(false);
        frame.setVisible(true);

        Painter painter = new Painter();

        Gear bodyGear = new Gear(new Point(200,200+x*70), 4*x, 90, 8);
        Axle bodyAxle = new Axle(new Point(200+x*41,200+x*76));     //y = 282
        Gear firstLegGear = new Gear(new Point(200+x*46,200+x*134), 12*x,270,4);
        Gear secondLegGear = new Gear(new Point(200+x*46,200+x*134), 12*x,90,4);
        Axle legAxle = new Axle(new Point(200+x*78,200+x*134));
        Axle handAxle = new Axle(new Point(200,200));

        ShiftRod bodyShiftRod = new ShiftRod(x*40,x*87,x*37,-1);
        ShiftRod firstLegShiftRod = new ShiftRod(x*40,x*76,x*40,-1);
        ShiftRod secondLegShiftRod = new ShiftRod(x*40,x*76,x*40,-1);
        ShiftRod body = new ShiftRod(x*24,-14*x,x*49,-1);
        ShiftRod firstLeg = new ShiftRod(x*34,x*34,x*37,1);
        ShiftRod secondLeg = new ShiftRod(x*34,x*34,x*37,1);

        ArrayList<Machine> machines = new ArrayList<>(Arrays.asList(
                bodyGear, bodyAxle, bodyShiftRod,
                firstLegGear, secondLegGear, legAxle, firstLegShiftRod, secondLegShiftRod,
                handAxle, body, firstLeg, secondLeg
        ));
        painter.addMachines(machines);


        while (true) {
            try {
                Thread.sleep(40);
            } catch (Exception exc) {
                exc.fillInStackTrace();
            }

            bodyShiftRod.setPins(bodyGear.getPinion(), bodyAxle.getPinion());
            firstLegShiftRod.setPins(firstLegGear.getPinion() , legAxle.getPinion());
            secondLegShiftRod.setPins(secondLegGear.getPinion() , legAxle.getPinion());
            body.setPins(bodyShiftRod.getMainPin(),handAxle.getPinion());
            firstLeg.setPins(body.getMainPin(), firstLegShiftRod.getMainPin());
            secondLeg.setPins(body.getMainPin(), secondLegShiftRod.getMainPin());
            frame.repaint();
            frame.add(painter);
            frame.setVisible(true);
        }
    }
}
