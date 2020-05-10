package window;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import mouseGestures.MakeDraggable;
import objects.Machine;
import objects.ShiftRod;

public class DetailsBoxManager {
    private VBox detailsBox;
    private ListView<String> hierarchy;
    private Boolean supportPinSetter = false, mainPinSetter = false;
    private ShiftRod shiftRod;

    public DetailsBoxManager() {}

    public void setNodes(VBox detailsBox, ListView<String> listView) {
        this.hierarchy = listView;
        this.detailsBox = detailsBox;
    }

    public void selectionChanged(Machine selected){
        if (selected!=null) {
            selected.setToDetailsBox(detailsBox,this);
            hierarchy.getSelectionModel().select(selected.getName());
            if(supportPinSetter){
                shiftRod.setSupportRodsPin(selected);
                supportPinSetter = false;
            }
            if(mainPinSetter){
                shiftRod.setMainRodsPin(selected);
                mainPinSetter = false;
            }
        }
        else {
            detailsBox.getChildren().clear();
            hierarchy.getSelectionModel().clearSelection();
        }
    }

    public void setSupportPinSetter(ShiftRod shiftRod) {
        supportPinSetter = true;
        this.shiftRod = shiftRod;
    }

    public void setMainPinSetter(ShiftRod shiftRod){
        mainPinSetter = true;
        this.shiftRod = shiftRod;
    }

    public void makeRefreshHierarchy() {

    }
}
