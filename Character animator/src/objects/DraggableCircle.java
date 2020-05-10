package objects;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import main.Colors;

public class DraggableCircle extends Circle {
    private Machine parentMachine;
    private Boolean changed = false, isNull = false;
    private Colors colors;

    public DraggableCircle(double centerX, double centerY, double radius, Colors colors) {
        super(centerX, centerY, radius, colors.get());
        this.colors = colors;
    }

    public DraggableCircle() {
        this.setRadius(5);
    }

    public DraggableCircle(Colors colors) {
        this.setRadius(5);
        this.colors = colors;
    }

    public void select(){
        this.setFill(colors.getSelected());
    }

    public void deselect(){
        this.setFill(colors.get());
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

    public boolean isNull(){
        return isNull;
    }

    public void setParentMachine(Machine parentMachine) {
        this.parentMachine = parentMachine;
    }

    public void setLocation(Pair<Double,Double> location){
        if (location!=null) {
            this.setCenterX(location.getKey());
            this.setCenterY(location.getValue());
            isNull = false;
        }
        else {
            isNull = true;
        }
    }
}
