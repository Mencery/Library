package figures;

import figures.Color;

/**
 * @author Denys Plekhanov
 */
public abstract class Figure {
    private double area;
    private Color color;

    Figure(Color color) {

        this.color = color;
    }

    double getArea() {
        return area;
    }

     void setArea(double area) {
        if (area < 0) {
            area = 0;
        }
        this.area = area;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    abstract public String printFigure();
}

