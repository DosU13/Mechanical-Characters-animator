package objects;

import helper.MathHelper;
import javafx.scene.shape.Line;
import main.Colors;

public class DraggableLine extends Line {
    private Boolean changed = false;
    private Machine parentMachine;
    private double length;
    private Colors colors;

    public DraggableLine(DraggableCircle start, DraggableCircle end, Colors colors) {
        super(start.getCenterX(),start.getCenterY(),end.getCenterX(),end.getCenterY());
        this.colors = colors;
        this.setStroke(colors.get());
        length = MathHelper.distance(start,end);
        this.setStrokeWidth(5);
    }

    public void select(){
        this.setStroke(colors.getSelected());
    }

    public void deselect(){
        this.setStroke(colors.get());
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Machine getParentMachine() {
        return parentMachine;
    }

    public void changed(){changed = true;}

    public Boolean isChanged() {
        Boolean thisChanged = changed;
        changed = false;
        return thisChanged;
    }

    public void setParentMachine(Machine parentMachine) {
        this.parentMachine = parentMachine;
    }

    public void setLocation(DraggableCircle center, DraggableCircle pinionCircle) {
        this.setStartX(center.getCenterX());
        this.setStartY(center.getCenterY());
        this.setEndX(pinionCircle.getCenterX());
        this.setEndY(pinionCircle.getCenterY());
    }
}
