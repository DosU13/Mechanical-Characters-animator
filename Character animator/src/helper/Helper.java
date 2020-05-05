package helper;

import javafx.scene.Node;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Translate;

import java.awt.*;

public class Helper {
    public static void movePivot(Node shape, Point toPoint){
        double x = - shape.getLayoutBounds().getCenterX() + toPoint.getX();
        double y = - shape.getLayoutBounds().getCenterY() + toPoint.getY();
        shape.getTransforms().add(new Translate(-x,-y));
        shape.setTranslateX(x); shape.setTranslateY(y);
    }


}
