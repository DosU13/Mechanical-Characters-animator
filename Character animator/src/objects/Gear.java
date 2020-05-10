package objects;

import helper.MathHelper;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import main.Colors;
import window.DetailsBoxManager;

public class Gear extends Group implements Machine {
    private Boolean isSelected = false, isAnimation = false;
    private DraggableCircle center, gear, pinionCircle;
    private DraggableLine line;
    private int degree, speedDegreePerSecond, startingAngle;
    private String name;

    public Gear(int x , int y, int radius, int startingAngle, int speedDegreePerSecond) {
        this.degree = startingAngle;
        this.speedDegreePerSecond = speedDegreePerSecond;
        this.startingAngle = startingAngle;

        center = new DraggableCircle(x,y,5,Colors.AXLE);
        gear = new DraggableCircle(x,y,radius+10,Colors.GEAR);
        Pair<Double,Double> pair = MathHelper.getPinionLocation(center,radius,degree);
        pinionCircle = new DraggableCircle(pair.getKey(),pair.getValue(),5,Colors.AXLE);
        line = new DraggableLine(center,pinionCircle,Colors.LINE);
        center.setParentMachine(this);gear.setParentMachine(this);pinionCircle.setParentMachine(this);line.setParentMachine(this);
        this.getChildren().addAll(gear,line,center,pinionCircle);
    }

    @Override
    public Boolean isSelected() {
        return isSelected;
    }

    @Override
    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
        if (isSelected) {
            center.select();
            pinionCircle.select();
            gear.select();
            line.select();
        }
        else {
            center.deselect();
            pinionCircle.deselect();
            gear.deselect();
            line.deselect();
        }
    }

    @Override
    public DraggableCircle[] getDraggableCircles() {
        return new DraggableCircle[]{center,pinionCircle,gear};
    }

    @Override
    public DraggableLine[] getDraggableLines() {
        return new DraggableLine[]{line};
    }

    @Override
    public DraggableCircle getMainPin(){return pinionCircle;}

    @Override
    public void setAnimationSwitch(Boolean animationSwitch) {
        isAnimation = animationSwitch;
        if (isAnimation){
            degree = startingAngle;
        }
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
        if (isAnimation) degree -= speedDegreePerSecond;
        if (degree < 0) {
            degree += 360;
        }

        if(center.isChanged()){
            gear.setCenterX(center.getCenterX());
            gear.setCenterY(center.getCenterY());
            pinionCircle.setLocation(MathHelper.getPinionLocation(center, line.getLength(), degree));
            line.setLocation(center,pinionCircle);
        }
        else if(gear.isChanged()){
            center.setCenterX(gear.getCenterX());
            center.setCenterY(gear.getCenterY());
            pinionCircle.setLocation(MathHelper.getPinionLocation(center, line.getLength(), degree));
            line.setLocation(center,pinionCircle);
        }
        else if(pinionCircle.isChanged()){
            line.setLength((int)MathHelper.distance(center,pinionCircle));
            gear.setRadius(line.getLength()+10);
            degree = MathHelper.getAngle(center,pinionCircle);
            startingAngle = degree;
            line.setLength(line.getLength());
            line.setLocation(center,pinionCircle);
        }
        else if(line.isChanged()){
            pinionCircle.setLocation(MathHelper.getPinionLocation(center, line.getLength(), degree));
            line.setLocation(center,pinionCircle);
            gear.setRadius(line.getLength()+10);
        }
        else{
            pinionCircle.setLocation(MathHelper.getPinionLocation(center, line.getLength(), degree));
            line.setLocation(center,pinionCircle);
        }
    }

    @Override
    public void setToDetailsBox(VBox detailsBox, DetailsBoxManager detailsBoxManager){
        detailsBox.getChildren().clear();
        AnchorPane nameAnchorPane = new AnchorPane(), xAnchorPane = new AnchorPane() , yAnchorPane = new AnchorPane(),
                radiusPane = new AnchorPane(), startingAnglePane = new AnchorPane(), speedPane = new AnchorPane();
        Label nameLabel = new Label("name : "), xLabel = new Label("x : "), yLabel = new Label("y : "), radiusLabel = new Label("radius : "),
                startingAngleLabel = new Label("angle : "), speedLabel = new Label("speed : ");
        TextArea xTextArea = new TextArea(), yTextArea = new TextArea(), radiusTextArea = new TextArea(),
                startingAngleTextArea = new TextArea(), speedTextArea = new TextArea();
        xTextArea.setLayoutX(50); yTextArea.setLayoutX(50); radiusTextArea.setLayoutX(50);
        startingAngleTextArea.setLayoutX(50); speedTextArea.setLayoutX(50);

        TextField nameTextArea = new TextField(name);
        nameTextArea.setLayoutX(50);
        nameTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            this.setName(newValue);
            detailsBoxManager.makeRefreshHierarchy();
        });

        xTextArea.setText(String.valueOf(center.getCenterX()));
        xTextArea.textProperty().addListener((observable , oldValue, newValue) -> {
            double d = 0.0;
            try {d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            gear.setCenterX(d);
            center.setCenterX(d);
        });
        yTextArea.setText(String.valueOf(center.getCenterY()));
        yTextArea.textProperty().addListener((observable,oldValue,newValue) -> {
            double d = 0.0;
            try {d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            gear.setCenterY(d);
            center.setCenterY(d);
        });
        radiusTextArea.setText(String.valueOf(line.getLength()));
        radiusTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            double d = 0.0;
            try {d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            line.setLength(d);
            line.changed();
        });
        startingAngleTextArea.setText(String.valueOf(-startingAngle));
        startingAngleTextArea.textProperty().addListener((observableValue, oldValue, newValue) -> {
            double d = 0.0;
            try{d = -Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            startingAngle = (int)d;
            degree = startingAngle;
        });
        speedTextArea.setText(String.valueOf(speedDegreePerSecond));
        speedTextArea.textProperty().addListener((observable, oldValue, newValue) -> {
            double d = 0.0;
            try{d = Double.parseDouble(newValue);}
            catch (NumberFormatException e) {e.fillInStackTrace();}
            speedDegreePerSecond = (int)d;
        });
        nameAnchorPane.getChildren().addAll(nameLabel,nameTextArea);
        xAnchorPane.getChildren().addAll(xLabel,xTextArea);
        yAnchorPane.getChildren().addAll(yLabel,yTextArea);
        radiusPane.getChildren().addAll(radiusLabel,radiusTextArea);
        startingAnglePane.getChildren().addAll(startingAngleLabel,startingAngleTextArea);
        speedPane.getChildren().addAll(speedLabel,speedTextArea);

        detailsBox.getChildren().addAll(nameAnchorPane,xAnchorPane,yAnchorPane,radiusPane,startingAnglePane,speedPane);
    }
}
