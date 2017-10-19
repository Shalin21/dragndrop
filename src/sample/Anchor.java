package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Created by admin on 19.10.17.
 */
public class Anchor extends Circle {
    int type;

    public Anchor(double centerX, double centerY, double radius, int type) {
        super(centerX, centerY, radius);
        super.setFill(Color.RED);
        this.type = type;
    }

    public Anchor() {
    }
}
