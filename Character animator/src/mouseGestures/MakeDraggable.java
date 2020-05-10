package mouseGestures;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import objects.DraggableCircle;
import objects.DraggableLine;
import objects.Machine;
import window.DetailsBoxManager;

import java.util.List;

public class MakeDraggable {
    private Machine selected = null;
    private DetailsBoxManager detailsBoxManager;

    public void setDetailsBoxManager(DetailsBoxManager detailsBoxManager) {
        this.detailsBoxManager = detailsBoxManager;
    }

    public void add(Machine machine) {
        for (DraggableCircle c: machine.getDraggableCircles()) new CircleMakeDraggable(c);
        for (DraggableLine l: machine.getDraggableLines()) new LineMakeDraggable(l);
        if (selected!=null) selected.setIsSelected(false);
        selected = machine;
    }

    public Machine getSelected(){
        return selected;
    }

    public void addAll(List<Machine> machines){
        for (Machine machine:machines) { this.add(machine);}
    }

    public void setSelected(Machine machine) {
        if (machine.isSelected()) {
            machine.setIsSelected(false);
            //selected = null;
            detailsBoxManager.selectionChanged(null);
        }
        else {
            selected.setIsSelected(false);
            selected = machine;
            machine.setIsSelected(true);
            detailsBoxManager.selectionChanged(selected);
        }
    }

    public class CircleMakeDraggable {
        DraggableCircle draggableCircle;
        Node draggableNode;
        Machine parentMachine;

        CircleMakeDraggable(DraggableCircle draggableCircle) {
            this.draggableCircle = draggableCircle;
            draggableNode = draggableCircle;
            parentMachine = draggableCircle.getParentMachine();

            this.draggableNode.setOnMousePressed(onMousePressedEventHandler);
            this.draggableNode.setOnMouseDragged(onMouseDraggedEventHandler);
            this.draggableNode.setOnMouseReleased(onMouseReleasedEventHandler);

        }

        private double mouseLayoutXOnPressed;
        private double mouseLayoutYOnPressed;

        private EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
            mouseLayoutXOnPressed = event.getSceneX() - draggableCircle.getCenterX();
            mouseLayoutYOnPressed = event.getSceneY() - draggableCircle.getCenterY();

            if (parentMachine.isSelected()) {
                parentMachine.setIsSelected(false);
                //selected = null;
                detailsBoxManager.selectionChanged(null);
            }
            else {
                selected.setIsSelected(false);
                selected = parentMachine;
                parentMachine.setIsSelected(true);
                detailsBoxManager.selectionChanged(selected);
            }
        };

        private EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
            draggableCircle.setCenterX(event.getSceneX() - mouseLayoutXOnPressed);
            draggableCircle.setCenterY(event.getSceneY() - mouseLayoutYOnPressed);
            draggableCircle.changed();

            draggableNode.getScene().setCursor(Cursor.MOVE);
            selected.setIsSelected(false);
            selected = parentMachine;
            parentMachine.setIsSelected(true);
            detailsBoxManager.selectionChanged(selected);
        };

        private EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> draggableNode.getScene().setCursor(Cursor.DEFAULT);
    }

    public class LineMakeDraggable {
        DraggableLine draggableLine;
        Node draggableNode;
        Machine parentMachine;

        LineMakeDraggable(DraggableLine draggableLine) {
            this.draggableLine = draggableLine;
            draggableNode = draggableLine;
            parentMachine = draggableLine.getParentMachine();

            this.draggableNode.setOnMousePressed(onMousePressedEventHandler);
            this.draggableNode.setOnMouseDragged(onMouseDraggedEventHandler);
            this.draggableNode.setOnMouseReleased(onMouseReleasedEventHandler);

        }

        private double mouseLayoutXOnPressed;
        private double lineLength;

        private EventHandler<MouseEvent> onMousePressedEventHandler = event -> {
            mouseLayoutXOnPressed = event.getSceneX();
            lineLength = draggableLine.getLength();

            if (parentMachine.isSelected()) {
                parentMachine.setIsSelected(false);
                //selected = null;
                detailsBoxManager.selectionChanged(null);
            }
            else {
                selected.setIsSelected(false);
                selected = parentMachine;
                parentMachine.setIsSelected(true);
                detailsBoxManager.selectionChanged(selected);
            }
        };

        private EventHandler<MouseEvent> onMouseDraggedEventHandler = event -> {
            draggableLine.setLength(lineLength+(event.getSceneX()-mouseLayoutXOnPressed)/2);
            draggableNode.getScene().setCursor(Cursor.H_RESIZE);

            draggableLine.changed();
            selected.setIsSelected(false);
            selected = parentMachine;
            parentMachine.setIsSelected(true);
            detailsBoxManager.selectionChanged(selected);
        };

        private EventHandler<MouseEvent> onMouseReleasedEventHandler = event -> draggableNode.getScene().setCursor(Cursor.DEFAULT);
    }

}
