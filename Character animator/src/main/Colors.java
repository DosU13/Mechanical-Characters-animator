package main;


import javafx.scene.paint.Color;

public enum Colors {
    AXLE(Color.rgb(70,71,62)),
    GEAR(Color.rgb(170,169,173)),
    RED(Color.rgb(253,51,51)),
    LINE(Color.rgb(133, 94, 66));

    private Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color get() {
        return color;
    }

    public Color getSelected() {
        return color.brighter();
    }
}
