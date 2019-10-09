package figures;

/**
 * @author Denys Plekhanov
 */
public class Square extends Figure {


    private double side;

    public Square(Color color, double side) {
        super(color);

        if (side < 0) {
            side = 0;
        }
        this.side = side;
        setArea(getSquareArea());
    }


    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        if (side < 0) {
            side = 0;
        }
        this.side = side;
        setArea(getSquareArea());
    }
   public double getSquareArea(){
        return side*side;
    }


    @Override
    public String printFigure() {
        return String.format("Figure: square, area: %5.2f square meters. units, side length: %3.2f units, color: %s",
                super.getArea(), side, super.getColor());
    }
}
