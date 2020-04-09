package paint;

import objects.Machine;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Painter extends JPanel {
    ArrayList<Machine> machines;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3));

        for (Machine machine : machines){
            machine.nextFrame();
            machine.paint(g2);
        }

    }

    public void addMachines(ArrayList<Machine> machines) {
        this.machines = machines;
    }
}
