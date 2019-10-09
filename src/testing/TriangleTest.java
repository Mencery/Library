package testing;

import figures.Color;
import figures.Triangle;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Denys Plekhanov
 */
public class TriangleTest {

    @Test
    public void setSideAAndSideB() {
        Triangle triangle = new Triangle(Color.Yellow, 3, 4);
        double actual = triangle.getSideA();
        double expected = 3;
        assertTrue(actual == expected);
        actual = triangle.getSideB();
        expected = 4;
        assertTrue(actual == expected);
        triangle.setSideAAndSideB(2, 6);
        actual = triangle.getSideA();
        expected = 2;
        assertTrue(actual == expected);
        actual = triangle.getSideB();
        expected = 6;
        assertTrue(actual == expected);

    }

    @Test
    public void areaTriangle() {
        Triangle triangle = new Triangle(Color.Yellow, 3, 4);
        double actual = triangle.areaTriangle();
        double expected = 6;
        assertTrue(actual == expected);

        Triangle triangle2 = new Triangle(Color.Yellow, -4, 4);
        actual = triangle2.areaTriangle();
        expected = 0;
        assertTrue(actual == expected);

        Triangle triangle3 = new Triangle(Color.Yellow, 4, -4);
        actual = triangle3.areaTriangle();
        expected = 0;
        assertTrue(actual == expected);

        Triangle triangle4 = new Triangle(Color.Yellow, 0, 0);
        actual = triangle4.areaTriangle();
        expected = 0;
        assertTrue(actual == expected);
    }

    @Test
    public void getHypotenuse() {
        Triangle triangle = new Triangle(Color.Yellow, 3, 4);
        double actual = triangle.getHypotenuse();
        double expected = 5;
        assertTrue(actual == expected);
        Triangle triangle2 = new Triangle(Color.Yellow, -2, 4);
        actual = triangle2.getHypotenuse();
        expected = 0;
        assertTrue(actual == expected);
    }
}