package testing;

import figures.Color;
import figures.Trapezoid;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Denys Plekhanov
 */
public class TrapezoidTest {

    @Test
    public void getHeight() {
        Trapezoid trapezoid = new Trapezoid(Color.Blue, 5, 10);
        double actual = trapezoid.getHeight();
        double expected = 5;
        assertTrue(actual == expected);

        Trapezoid trapezoid2 = new Trapezoid(Color.Blue, -1, 10);
        actual = trapezoid2.getHeight();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void setHeight() {
        Trapezoid trapezoid = new Trapezoid(Color.Blue, 5, 10);
        trapezoid.setHeight(7.5);
        double actual = trapezoid.getHeight();
        double expected = 7.5;
        assertTrue(actual == expected);

        Trapezoid trapezoid2 = new Trapezoid(Color.Blue, 5, 10);
        trapezoid2.setHeight(-1);
        actual = trapezoid2.getHeight();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void getMedian() {
        Trapezoid trapezoid = new Trapezoid(Color.Blue, 5, 10);
        double actual = trapezoid.getMedian();
        double expected = 10;
        assertTrue(actual == expected);

        Trapezoid trapezoid2 = new Trapezoid(Color.Blue, -1, -2);
        actual = trapezoid2.getMedian();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void setMedian() {
        Trapezoid trapezoid = new Trapezoid(Color.Blue, 5, 10);
        trapezoid.setMedian(7.5);
        double actual = trapezoid.getMedian();
        double expected = 7.5;
        assertTrue(actual == expected);

        Trapezoid trapezoid2 = new Trapezoid(Color.Blue, 5, 10);
        trapezoid2.setMedian(-1);
        actual = trapezoid2.getMedian();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void getTrapezoidArea() {
        Trapezoid trapezoid = new Trapezoid(Color.Blue, 5, 10);
        double actual = trapezoid.getTrapezoidArea();
        double expected = 50;
        assertTrue(actual == expected);
        Trapezoid trapezoid2 = new Trapezoid(Color.Blue, -1, 10);
         actual = trapezoid2.getTrapezoidArea();
        expected = 0;
        assertTrue(actual == expected);

    }
}