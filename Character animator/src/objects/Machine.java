package objects;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedHashMap;

public interface Machine{
    void nextFrame();

    void setToDetailsBox(VBox detailsBox);

    Boolean isSelected();

    void setIsSelected(Boolean isSelected);

    DraggableCircle[] getDraggableCircles();

    DraggableLine[] getDraggableLines();
}
