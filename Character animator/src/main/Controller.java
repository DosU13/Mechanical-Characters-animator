package main;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import mouseGestures.MakeDraggable;
import objects.Axle;
import objects.Gear;
import window.DetailsBoxManager;
import window.EditorPane;

public class Controller {

    @FXML
    private VBox masterBox;

    @FXML
    private AnchorPane editorAnchor;

    @FXML
    private VBox detailsBox;

    @FXML
    private Font x3;

    @FXML
    private Color x4;

    private EditorPane editorPane = new EditorPane();

    private MakeDraggable makeDraggable = new MakeDraggable();
    private DetailsBoxManager detailsBoxManager = new DetailsBoxManager();
    @FXML
    private void initialize(){
        detailsBoxManager.setDetailsBox(detailsBox);
        makeDraggable.setDetailsBoxManager(detailsBoxManager);
        editorAnchor.getChildren().add(editorPane);
        Axle axle = new Axle(333,333);
        Axle axle1 = new Axle(444,444);
        Gear gear = new Gear(666,666,200,0,5);
        editorPane.getChildren().addAll(axle,axle1);
        editorPane.getChildren().add(gear);
        makeDraggable.addAll(axle,axle1);
        makeDraggable.add(gear);

        MyTimer timer = new MyTimer();
        timer.setGear(gear);
        timer.start();
    }

    private class MyTimer extends AnimationTimer{
        Gear gear;

        public void setGear(Gear gear) {
            this.gear = gear;
        }

        @Override
        public void handle(long l) {
            gear.nextFrame();
        }
    }
}
