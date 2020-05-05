package objects;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Pair;
import main.Colors;

public class DraggableCircle extends Circle {
    private Machine parentMachine;
    private Boolean changed = false;
    private Colors colors;

    public DraggableCircle(double centerX, double centerY, double radius, Colors colors) {
        super(centerX, centerY, radius, colors.get());
        this.colors = colors;
    }

    public void select(){
        this.setFill(colors.get());
    }

    public void deselect(){
        this.setFill(colors.getSelected());
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

    public void setLocation(Pair<Double,Double> location){
        this.setCenterX(location.getKey());
        this.setCenterY(location.getValue());
    }
}
