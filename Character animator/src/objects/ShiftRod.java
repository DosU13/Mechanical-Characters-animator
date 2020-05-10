package objects;

import helper.MathHelper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import main.Colors;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ShiftRod extends Group implements Machine {
    private DraggableCircle supportRodsPin = new DraggableCircle(), mainRodsPin = new DraggableCircle(),
            jointPin = new DraggableCircle(Colors.AXLE), mainPin = new DraggableCircle(Colors.AXLE);
    private DraggableLine supportRod, mainRodSupportPart, mainRodMainPart;
    private int side;
    private ArrayList<Point2D.Double> points;
    private Boolean isNan, isSelected = false;
    private String name;

    public ShiftRod(int smallRodLen, int beforeJointRodLen, int afterJointRodLen, int side) {
        supportRod = new DraggableLine(supportRodsPin,jointPin, Colors.LINE);
        mainRodSupportPart = new DraggableLine(mainRodsPin,jointPin,Colors.LINE);
        mainRodMainPart = new DraggableLine(jointPin,mainPin,Colors.LINE);
        supportRod.setLength(smallRodLen);
        mainRodSupportPart.setLength(beforeJointRodLen);
        mainRodMainPart.setLength(afterJointRodLen);
        this.side = side;
        points = new ArrayList<>();
        isNan = false;
        this.getChildren().addAll(supportRod, mainRodSupportPart, mainRodMainPart, supportRodsPin, mainRodsPin,jointPin,mainPin);
        supportRod.setParentMachine(this);
        mainRodSupportPart.setParentMachine(this);
        mainRodMainPart.setParentMachine(this);
        supportRodsPin.setParentMachine(this);
        mainRodsPin.setParentMachine(this);
        jointPin.setParentMachine(this);
        mainPin.setParentMachine(this);
    }

    public void setSupportRodsPin(Machine smallRodsPinsMachine){
        this.supportRodsPin = smallRodsPinsMachine.getMainPin();
    }

    public void setMainRodsPin(Machine secondPinsMachine){
        this.mainRodsPin = secondPinsMachine.getMainPin();
    }

    public DraggableCircle getMainPin() {
        return mainPin;
    }

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
    public Boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
        if (isSelected) {
            supportRod.select();
            mainRodSupportPart.select();
            mainRodMainPart.select();
            supportRodsPin.select();
            mainRodsPin.select();
            jointPin.select();
            mainPin.select();
        }
        else {
            supportRod.deselect();
            mainRodSupportPart.deselect();
            mainRodMainPart.deselect();
            supportRodsPin.deselect();
            mainRodsPin.deselect();
            jointPin.deselect();
            mainPin.deselect();
        }
    }

    @Override
    public DraggableCircle[] getDraggableCircles() {
        return new DraggableCircle[]{supportRodsPin, mainRodsPin,jointPin,mainPin};
    }

    @Override
    public DraggableLine[] getDraggableLines() {
        return new DraggableLine[]{supportRod, mainRodMainPart, mainRodSupportPart};
    }

    @Override
    public void nextFrame(Label status) {
        jointPin.setLocation(MathHelper.getJoint(supportRod, mainRodSupportPart, supportRodsPin, mainRodsPin,side));
        mainPin.setLocation(MathHelper.getMainPin(mainRodsPin, jointPin, mainRodSupportPart, mainRodMainPart));
        isNan = jointPin.isNull()||mainPin.isNull();

        if (!isNan) {
            supportRod.setLocation(supportRodsPin,jointPin);
            mainRodSupportPart.setLocation(mainRodsPin,jointPin);
            mainRodMainPart.setLocation(jointPin,mainPin);

            points.add(new Point2D.Double(mainPin.getCenterX(),mainPin.getCenterY()));
            status.setText("");
        }
        else {
            status.setTextFill(Colors.RED.get());
            status.setText("NaN value appeared :    "+this);
        }
    }

    @Override
    public void setToDetailsBox(VBox detailsBox) {
        detailsBox.getChildren().clear();
        AnchorPane supportPinPane = new AnchorPane() , mainPinPane = new AnchorPane(), supportRodPane = new AnchorPane(),
                mainRodSupportPartPane = new AnchorPane(), mainRodMainPartPane = new AnchorPane();
        Button supportPinButton = new Button("support pin"), mainPinButton = new Button("main pin");
        Label supportRodLabel = new Label("support's length : "),
                mainRodSupportPartLabel = new Label("Imain's length : "), mainRodMainPartLabel = new Label("IImain's length : ");
        TextArea supportPinTextArea = new TextArea(), mainPinTextArea = new TextArea(), supportRodTextArea = new TextArea(),
                mainRodSupportPartTextArea = new TextArea(), mainRodMainPartTextArea = new TextArea();
        supportPinTextArea.setLayoutX(100); mainPinTextArea.setLayoutX(100); supportRodTextArea.setLayoutX(100);
        mainRodSupportPartTextArea.setLayoutX(100); mainRodMainPartTextArea.setLayoutX(100);

        supportPinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        supportPinTextArea.setText(String.valueOf(supportRodsPin));
        supportPinTextArea.setEditable(false);
        mainPinTextArea.setText(String.valueOf(mainRodsPin));
        mainPinTextArea.setEditable(false);
        supportRodTextArea.setText(String.valueOf(supportRod.getLength()));
        supportRodTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            double d = 0.0;
            try {d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            supportRod.setLength(d);
            supportRod.changed();
        });
        mainRodSupportPartTextArea.setText(String.valueOf(mainRodSupportPart.getLength()));
        mainRodSupportPartTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            double d = 0.0;
            try{d = -Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            mainRodSupportPart.setLength(d);
            mainRodSupportPart.changed();
        });
        mainRodMainPartTextArea.setText(String.valueOf(mainRodMainPart.getLength()));
        mainRodMainPartTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            double d = 0.0;
            try{d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            mainRodMainPart.setLength(d);
            mainRodMainPart.changed();
        });
        supportPinPane.getChildren().addAll(supportPinButton,supportPinTextArea);
        mainPinPane.getChildren().addAll(mainPinButton,mainPinTextArea);
        supportRodPane.getChildren().addAll(supportRodLabel,supportRodTextArea);
        mainRodSupportPartPane.getChildren().addAll(mainRodSupportPartLabel,mainRodSupportPartTextArea);
        mainRodMainPartPane.getChildren().addAll(mainRodMainPartLabel,mainRodMainPartTextArea);

        detailsBox.getChildren().addAll(supportPinPane,mainPinPane,supportRodPane,mainRodSupportPartPane,mainRodMainPartPane);

    }

//    public void nextFrameNew(){
//        double x1 = firstPin.getCenterX();
//        double x2 = mainRodsPin.getCenterX();
//        double y1 = firstPin.getCenterY();
//        double y2 = mainRodsPin.getCenterY();
//        double a = lenToTheJoint;
//        double b = secondLen;
//        double x = 0, y = 0;
//        // We must find x, y
//        // (x-x1)^2 + (y-y1)^2 = a^2
//        // (x-x2)^2 + (y-y2)^2 = b^2
//        if (y1 == y2){
//            // if we subtract they:
//            // 2*x(x2-x1) + x1*x1-x2*x2 = a*a - b*b
//            x = (a*a-b*b-x1*x1+x2*x2)/2/(x2-x1);
//            y = Math.sqrt(a*a - (x-x1)*(x-x1)) + y1;
//        }
//        else {
//            // if we subtract they:
//            // 2*x(x2-x1) + 2*y(y2-y1) = a^2 - b^2 + x2^2 - x1^2 + y2^2 - y1^2
//            // y = -x*(x2-x1)/(y2-y1) + (a^2 - b^2 + x2^2 - x1^2 + y2^2 - y1^2)/(y2-y1)
//            double t1 = -1 * (x2 - x1) / (y2 - y1);
//            double t2 = (a * a - b * b + x2 * x2 - x1 * x1 + y2 * y2 - y1 * y1) / (y2 - y1);
//            // So y = x*t1 + t2
//            // (x-x1)^2 + (y-y1)^2 = a^2 replace y
//            // (x-x1)^2 + (x*t1 + t2 - y1)^2 = a^2
//            t2 = t2 - y1;
//            // (x-x1)^2 + (x*t1 + t2)^2 = a^2
//            // x^2 - 2*x1*x + x1^2 + t1^2*x^2 + 2*t1*t2*x + t2^2-a^2 = 0
//            double t = t1 * t1 + 1;
//            t1 = -2 * x1 + 2 * t1 * t2;
//            t2 = x1 * x1 + t2 * t2 - a * a;
//            // t*x^2 + t1*x + t2 = 0;
//            System.out.println(t + " " + t1 + " " + t2);
//            x = (-1 * t1 + side * Math.sqrt(t1 * t1 - 4 * t * t2)) / 2 * t;
//            y = -x*(x2-x1)/(y2-y1) + (a*a - b*b + x2*x2 - x1*x1 + y2*y2 - y1*y2)/(y2-y1);
//        }
//        if (!Double.isNaN(x) && !Double.isNaN(y)) {
//            joint.setLocation(new Pair<>(x,y));
//            isNan = false;
//        }
//        else isNan = true;
//        double xm = x1 + (x - x1) * firstLen / a;
//        double ym = y1 + (y - y1) * firstLen / a;
//        mainPin.setLocation(new Pair<>(xm, ym));
//        points.add(new Point2D.Double(mainPin.getCenterX(),mainPin.getCenterY()));
//    }
}
