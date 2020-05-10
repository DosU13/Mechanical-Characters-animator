package objects;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import main.Colors;
import window.DetailsBoxManager;

public class Axle extends Group implements Machine {
    private Boolean isSelected = false;
    private DraggableCircle axle;
    private String name;

    public Axle(int x , int y) {
        axle = new DraggableCircle(x,y,5,Colors.AXLE);
        axle.setParentMachine(this);
        this.getChildren().add(axle);
    }

    @Override
    public Boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
        if (!isSelected) axle.deselect();
        else axle.select();
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
    public DraggableCircle getMainPin(){return axle;}

    @Override
    public void setAnimationSwitch(Boolean animationSwitch) {

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void nextFrame(Label status) {

    }

    @Override
    public void setToDetailsBox(VBox detailsBox, DetailsBoxManager detailsBoxManager){
        detailsBox.getChildren().clear();
        AnchorPane nameAnchorPane = new AnchorPane(), xAnchorPane = new AnchorPane() , yAnchorPane = new AnchorPane();
        Label nameLabel = new Label("name : "), xLabel = new Label("x : ") , yLabel = new Label("y : ");

        TextField nameTextArea = new TextField(name);
        nameTextArea.setLayoutX(50);
        nameTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            this.setName(newValue);
            detailsBoxManager.makeRefreshHierarchy();
        });

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
        nameAnchorPane.getChildren().addAll(nameLabel,nameTextArea);
        xAnchorPane.getChildren().addAll(xLabel,xTextArea);
        yAnchorPane.getChildren().addAll(yLabel,yTextArea);
        detailsBox.getChildren().addAll(nameAnchorPane,xAnchorPane,yAnchorPane);
    }
}
