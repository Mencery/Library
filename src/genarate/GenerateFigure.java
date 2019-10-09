package genarate;

import figures.*;

import java.util.Random;

/**
 * @author Denys Plekhanov
 */
public class GenerateFigure {
    public static Figure generateFigure(int i) throws OutOfRangeIndexFiguresException {
        Figure figure;
        Random random =new Random();
        switch (i){
            case 0:

                figure = new Square(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100);
                break;
            case 1:
                figure = new Circle(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100);
                break;
            case 2:
                figure = new Triangle(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100,random.nextDouble()*100);
                break;
            case 3:
                figure = new Trapezoid(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100,random.nextDouble()*100);

                break;
                default: throw  new OutOfRangeIndexFiguresException("Cannot generate figure because invalid number of figure");
        }
        return figure;
    }
    public static Figure generateFigure(String nameOfFigure) throws OutOfRangeIndexFiguresException {
        Figure figure;
        Random random =new Random();
        switch (nameOfFigure){
            case "Square":

                figure = new Square(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100);
                break;
            case "Circle":
                figure = new Circle(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100);
                break;
            case "Triangle":
                figure = new Triangle(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100,random.nextDouble()*100);
                break;
            case  "Trapezoid":
                figure = new Trapezoid(Color.getColorByIndex(random.nextInt( Color.values().length)), random.nextDouble()*100,random.nextDouble()*100);

                break;
            default: throw  new OutOfRangeIndexFiguresException("Cannot generate figure figure with this name don't exist");
        }
        return figure;
    }
}
