package main;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mouseGestures.MakeDraggable;
import objects.Axle;
import objects.Gear;
import objects.Machine;
import objects.ShiftRod;
import window.DetailsBoxManager;
import window.EditorPane;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Controller {

    @FXML
    private ToggleButton animation;

    @FXML
    private VBox masterBox;

    @FXML
    void addAxle(ActionEvent event) {

    }

    @FXML
    void addGear(ActionEvent event) {

    }

    @FXML
    void addShiftRod(ActionEvent event) {

    }

    @FXML
    void animationOnAction(ActionEvent event) {
        for (Machine m:machines){
            m.setAnimationSwitch(animation.isSelected());
        }
    }
    @FXML
    private ListView<String> hierarchy;

    @FXML
    private AnchorPane editorAnchor;

    @FXML
    private VBox detailsBox;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    @FXML
    private Label rightStatus;

    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    int height = Toolkit.getDefaultToolkit().getScreenSize().height;

    private EditorPane editorPane = new EditorPane();
    private MakeDraggable makeDraggable = new MakeDraggable();
    private DetailsBoxManager detailsBoxManager = new DetailsBoxManager();
    private List<Machine> machines = new ArrayList<>();
    private ObservableList<String> machineObservableList = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        detailsBoxManager.setDetailsBox(detailsBox);
        makeDraggable.setDetailsBoxManager(detailsBoxManager);
        editorAnchor.getChildren().add(editorPane);
        Axle axle = new Axle(333,333);
        Axle axle1 = new Axle(444,444);
        Gear gear = new Gear(666,666,200,0,5);
        ShiftRod shiftRod = new ShiftRod(300,300,200,1);
        shiftRod.setSupportRodsPin(axle1);
        shiftRod.setMainRodsPin(gear);
        machines.addAll(Arrays.asList(axle,axle1,gear,shiftRod));
        for (Machine m:machines) editorPane.getChildren().add((Node)m);
        makeDraggable.addAll(machines);


        for(Machine m:machines){
            machineObservableList.add(m.toString());
        }
        hierarchy.setItems(machineObservableList);
        MyTimer timer = new MyTimer();
        timer.start();
    }

    private class MyTimer extends AnimationTimer{
        @Override
        public void handle(long l) {
            for (Machine m:machines) {
                m.nextFrame(rightStatus);
            }
        }
    }
}
