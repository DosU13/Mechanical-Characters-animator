package window;

import javafx.scene.layout.VBox;
import mouseGestures.MakeDraggable;
import objects.Machine;

public class DetailsBoxManager {
    private VBox detailsBox;

    public DetailsBoxManager() {}

    public void setDetailsBox(VBox detailsBox) {
        this.detailsBox = detailsBox;
    }

    public void selectionChanged(Machine selected){
        if (selected!=null)  selected.setToDetailsBox(detailsBox);
        else detailsBox.getChildren().clear();
    }
}
