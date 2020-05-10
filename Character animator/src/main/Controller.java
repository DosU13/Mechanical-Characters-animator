package main;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import window.Hierarchy;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Controller {

    @FXML
    private ToggleButton animation;

    @FXML
    private VBox masterBox;

    private void addMachine(Machine machine){
        machine.setName(String.valueOf(count));
        count++;
        machines.add(machine);
        editorPane.getChildren().add((Node) machine);
        makeDraggable.add(machine);
        refreshHierarchy();
    }

    @FXML
    void addAxle(ActionEvent event) {
        Axle axle= new Axle(300,300);
        addMachine(axle);
    }

    @FXML
    void addGear(ActionEvent event) {
        Gear gear = new Gear(300,300,100,0,5);
        addMachine(gear);
    }

    @FXML
    void addShiftRod(ActionEvent event) {
        ShiftRod shiftRod = new ShiftRod(200,200,200,1);
        addMachine(shiftRod);
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
    private int count = 1;

    private EditorPane editorPane = new EditorPane();
    private MakeDraggable makeDraggable = new MakeDraggable();
    private DetailsBoxManager detailsBoxManager = new DetailsBoxManager(){
        @Override
        public void makeRefreshHierarchy(){
            refreshHierarchy();
        }
    };
    private static List<Machine> machines = new ArrayList<>();
    private ObservableList<String> machineObservableList = FXCollections.observableArrayList();
    @FXML
    private void initialize(){
//        hierarchy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
//                for (int i = 0; i < machines.size(); i++) {
//                    Machine m = machines.get(i);
//                    if (newValue.equals(m.getName())) {
//                        makeDraggable.setSelected(m);
//                    }
//                    break;
//                }
//            }
//        });


        detailsBoxManager.setNodes(detailsBox,hierarchy);
        makeDraggable.setDetailsBoxManager(detailsBoxManager);
        editorAnchor.getChildren().add(editorPane);
        Axle axle = new Axle(333,333);
        Axle axle1 = new Axle(444,444);
        Gear gear = new Gear(666,666,200,0,5);
        ShiftRod shiftRod = new ShiftRod(300,300,200,1);
        axle.setName("axle");
        axle1.setName("axle1");
        gear.setName("gear");
        shiftRod.setName("shiftRod");
        shiftRod.setSupportRodsPin(axle1);
        shiftRod.setMainRodsPin(gear);
        machines.addAll(Arrays.asList(axle,axle1,gear,shiftRod));
        for (Machine m:machines) editorPane.getChildren().add((Node)m);
        makeDraggable.addAll(machines);

        refreshHierarchy();
        MyTimer timer = new MyTimer();
        timer.start();
    }

    private void refreshHierarchy(){
        machineObservableList.clear();
        for(Machine m:machines){
            machineObservableList.add(m.getName());
        }
        hierarchy.setItems(machineObservableList);
        hierarchy.getSelectionModel().select(makeDraggable.getSelected().getName());
    }

    public void newBoard(ActionEvent actionEvent) {
    }

    public void about(ActionEvent actionEvent) {

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
