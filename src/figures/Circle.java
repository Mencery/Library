package figures;

/**
 * @author Denys Plekhanov
 */
public class Circle extends Figure {


    private double radius;

    public Circle(Color color, double radius) {
        super(color);
        if (radius < 0) {
            radius = 0;
        }
        this.radius = radius;
        setArea(getCircleArea());
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        if(radius<0){
            radius = 0;
        }
        this.radius = radius;
        setArea(getCircleArea());
    }

    public double getCircleArea(){
        return Math.PI * radius * radius;
    }

    @Override
    public String printFigure() {
        return String.format("Figure: circle, area: %5.2f square meters. units,  radius: %3.2f, color: %s",
                super.getArea(), radius, super.getColor());
    }
}
