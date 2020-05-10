package window;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;


public class EditorPane extends Pane {
    private int width = 2000, height = 2000, disBtwLines = 20;

    public EditorPane() {
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color: #e0e0e0");
        for (int i = disBtwLines; i < width; i += disBtwLines) {
            if ((i / disBtwLines) % 5 != 0) {
                Line line = new Line(i, 0, i, height);
                line.setStroke(Color.rgb(200,200,200));
                this.getChildren().add(line);
            } else {
                Line line = new Line(i, 0, i, height);
                line.setStroke(Color.rgb(150,150,150));
                this.getChildren().add(line);
            }

        }

        for (int i = disBtwLines; i < height; i += disBtwLines) {
            if ((i / disBtwLines) % 5 != 0) {
                Line line = new Line(0, i, width, i);
                line.setStroke(Color.rgb(200,200,200));
                this.getChildren().add(line);
            } else {
                Line line = new Line(0, i, width, i);
                line.setStroke(Color.rgb(150,150,150));
                this.getChildren().add(line);
            }

        }
    }
}
