package testing;

import figures.Circle;
import figures.Color;
import figures.Square;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Denys Plekhanov
 */
public class CircleTest {

    @Test
    public void getRadius() {
         Circle circle = new Circle(Color.Red, 4.5);
        double actual = circle.getRadius();
        double expected = 4.5;
        assertTrue(actual == expected);

    }

    @Test
    public void setRadius() {
        Circle circle = new Circle(Color.Red, 4.5);
        circle.setRadius(5);
        double actual = circle.getRadius();
        double expected = 5;
        assertTrue(actual == expected);

        circle.setRadius(0);
        actual = circle.getRadius();
        expected = 0;
        assertTrue(actual == expected);

        circle.setRadius(-14);
        actual = circle.getRadius();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void getCircleArea() {
        Circle circle = new Circle(Color.Red, 4.5);
        double actual = circle.getCircleArea();
        double expected = 20.25*Math.PI;
        assertTrue(actual == expected);

        Circle circle2 = new Circle(Color.Red, -1);
        actual = circle2.getCircleArea();
        expected = 0;
        assertTrue(actual == expected);
    }
}