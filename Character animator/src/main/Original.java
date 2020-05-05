//package main;
//
//import objects.Axle;
//import objects.Gear;
//import objects.Machine;
//import objects.ShiftRod;
//
//import javax.swing.*;
//import java.awt.geom.Point2D;
//import java.util.ArrayList;
//import java.util.Arrays;
//
//public class Original {
//
//
//    public static void main(String[] args) {
//
//        JFrame frame = new JFrame("Hello My Dear Friend");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        frame.setUndecorated(false);
//        frame.setVisible(true);
//
//        Painter painter = new Painter();
//
//        Gear bodyGear = new Gear(new Point2D.Double(200,270), 4, 90, 8);
//        Axle bodyAxle = new Axle(new Point2D.Double(241,276));     //y = 282
//        Gear firstLegGear = new Gear(new Point2D.Double(246,334), 12,270,4);
//        Gear secondLegGear = new Gear(new Point2D.Double(246,334), 12,90,4);
//        Axle legAxle = new Axle(new Point2D.Double(278,334));
//        Axle handAxle = new Axle(new Point2D.Double(200,200));
//
//        ShiftRod bodyShiftRod = new ShiftRod(40,87,37,-1);
//        ShiftRod firstLegShiftRod = new ShiftRod(40,76,40,-1);
//        ShiftRod secondLegShiftRod = new ShiftRod(40,76,40,-1);
//        ShiftRod body = new ShiftRod(24,-14,49,-1);
//        ShiftRod firstLeg = new ShiftRod(34,34,37,1);
//        ShiftRod secondLeg = new ShiftRod(34,34,37,1);
//
//        ArrayList<Machine> machines = new ArrayList<>(Arrays.asList(
//                bodyGear, bodyAxle, bodyShiftRod,
//                firstLegGear, secondLegGear, legAxle, firstLegShiftRod, secondLegShiftRod,
//                handAxle, body, firstLeg, secondLeg
//        ));
//        painter.addMachines(machines);
//
//
//        while (true) {
//            try {
//                Thread.sleep(40);
//            } catch (Exception exc) {
//                exc.fillInStackTrace();
//            }
//
//            bodyShiftRod.setPins(bodyGear.getPinion(), bodyAxle.getPinion());
//            firstLegShiftRod.setPins(firstLegGear.getPinion() , legAxle.getPinion());
//            secondLegShiftRod.setPins(secondLegGear.getPinion() , legAxle.getPinion());
//            body.setPins(bodyShiftRod.getMainPin(),handAxle.getPinion());
//            firstLeg.setPins(body.getMainPin(), firstLegShiftRod.getMainPin());
//            secondLeg.setPins(body.getMainPin(), secondLegShiftRod.getMainPin());
//            frame.repaint();
//            frame.add(painter);
//            frame.setVisible(true);
//        }
//    }
//}
