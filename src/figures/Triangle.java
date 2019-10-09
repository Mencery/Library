package figures;

/**
 * @author Denys Plekhanov
 */
public class Triangle extends Figure {
    private double sideA;
    private double sideB;
    private double hypotenuse;

    public Triangle(Color color, double sideA, double sideB) {
        super(color);
        if (sideA < 0 || sideB < 0) {
            sideA = 0;
            sideB = 0;
        }
        this.sideA = sideA;
        this.sideB = sideB;
        hypotenuse = Math.sqrt(sideA * sideA + sideB * sideB);
        setArea(areaTriangle());

    }

   public void setSideAAndSideB(double sideA, double sideB) {
        if (sideA < 0 || sideB < 0) {
            sideA = 0;
            sideB = 0;
        }
        this.sideA = sideA;
        this.sideB = sideB;
        setArea(areaTriangle());

    }

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public double areaTriangle() {

        return (sideA * sideB) / 2;
    }

    public double getHypotenuse() {
        return hypotenuse;
    }

    @Override
    public String printFigure() {

        return String.format("Figure: triangle, area: %5.2f square meters. units,  hypotenuse: %3.2f, color: %s",
                super.getArea(), hypotenuse, super.getColor());

    }
}
