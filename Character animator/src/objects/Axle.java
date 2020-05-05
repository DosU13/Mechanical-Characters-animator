package objects;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import main.Colors;

public class Axle extends Group implements Machine {
    private Boolean isSelected = false;
    private DraggableCircle axle;

    public Axle(int x , int y) {
        axle = new DraggableCircle(x,y,5,Colors.AXLE);
        axle.setParentMachine(this);
        this.getChildren().add(axle);
    }

    public double getCenterX(){
        return axle.getCenterX();
    }

    public double getCenterY(){
        return axle.getCenterY();
    }

    @Override
    public Boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
        if (!isSelected) axle.select();
        else axle.deselect();
    }

    @Override
    public DraggableCircle[] getDraggableCircles() {
        return new DraggableCircle[]{axle};
    }

    @Override
    public DraggableLine[] getDraggableLines() {
        return new DraggableLine[]{};
    }

    @Override
    public void nextFrame() {

    }

    @Override
    public void setToDetailsBox(VBox detailsBox){
        detailsBox.getChildren().clear();
        AnchorPane xAnchorPane = new AnchorPane() , yAnchorPane = new AnchorPane();
        Label xLabel = new Label("x : ") , yLabel = new Label("y : ");

        TextArea xTextArea = new TextArea();
        xTextArea.setLayoutX(50);
        xTextArea.setText(String.valueOf(axle.getCenterX()));
        xTextArea.textProperty().addListener((observable , oldValue, newValue) -> {
            double d = 0.0;
            try {d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {  e.fillInStackTrace();}
            axle.setCenterX(d);});
        TextArea yTextArea = new TextArea();
        yTextArea.setLayoutX(50);
        yTextArea.setText(String.valueOf(axle.getCenterY()));
        yTextArea.textProperty().addListener((observable,oldValue,newValue) -> {
            double d = 0.0;
            try {d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {  e.fillInStackTrace();}
            axle.setCenterY(d);});
        xAnchorPane.getChildren().addAll(xLabel,xTextArea);
        yAnchorPane.getChildren().addAll(yLabel,yTextArea);
        detailsBox.getChildren().addAll(xAnchorPane,yAnchorPane);
    }
}
