package objects;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public interface Machine{
    void nextFrame(Label status);

    void setToDetailsBox(VBox detailsBox);

    Boolean isSelected();
    void setIsSelected(Boolean isSelected);

    DraggableCircle[] getDraggableCircles();

    DraggableLine[] getDraggableLines();

    DraggableCircle getMainPin();

    void setAnimationSwitch(Boolean animationSwitch);

    String getName();
    void setName(String name);
}
