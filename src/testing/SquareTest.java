package testing;

import figures.Color;
import figures.Square;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Denys Plekhanov
 */
public class SquareTest {

    @Test
    public void getSide() {
        Square square = new Square(Color.Red, 4.5);
        double actual = square.getSide();
        double expected = 4.5;
        assertTrue(actual == expected);


    }

    @Test
    public void setSide() {
        Square square = new Square(Color.Red, 4.5);
        square.setSide(5);
        double actual = square.getSide();
        double expected = 5;
        assertTrue(actual == expected);

        square.setSide(0);
        actual = square.getSide();
        expected = 0;
        assertTrue(actual == expected);

        square.setSide(-14);
        actual = square.getSide();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void getSquareArea() {
        Square square = new Square(Color.Red, 4.5);
        double actual = square.getSquareArea();
        double expected = 20.25;
        assertTrue(actual == expected);

        Square square2 = new Square(Color.Red, -1);
        actual = square2.getSquareArea();
        expected = 0;
        assertTrue(actual == expected);
    }

}