package figures;

/**
 * @author Denys Plekhanov
 */
public class Trapezoid extends Figure {



    private double height;
    private double median;

    public Trapezoid(Color color, double height, double median) {
        super(color);
        if (height < 0) {
            height = 0;
        }
        if (median < 0) {
            median = 0;
        }
        this.height = height;
        this.median = median;
        setArea(getTrapezoidArea());

    }
    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height < 0) {
            height = 0;
        }
        this.height = height;
        setArea(getTrapezoidArea());

    }

    public double getMedian() {

        return median;
    }

    public void setMedian(double median) {
        if (median < 0) {
            median = 0;
        }
        this.median = median;
        setArea(getTrapezoidArea());
    }
    public double getTrapezoidArea(){
        return median*height ;
    }
    @Override
    public String printFigure() {
        return String.format("Figure: trapezoid, area: %5.2f square meters. units,  height: %3.2f, median: %3.2f, color: %s",
                super.getArea(), height,median, super.getColor());
    }

}
