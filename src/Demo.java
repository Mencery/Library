/**
 * @author Denys Plekhanov
 */

import figures.*;
import genarate.GenerateFigure;
import genarate.OutOfRangeIndexFiguresException;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Demo {
    public static void main(String[] args) {

        List<Figure>  figures= new ArrayList<>();
        Random sizeOfList = new Random();
        Random indexOfFigure = new Random();
        /*
          for example, let the maximum number be 10
         */
        final int maxSizeForExample = 10;
        for(int i = 0; i< sizeOfList.nextInt(maxSizeForExample);i++){
            try {
                figures.add( GenerateFigure.generateFigure(indexOfFigure.nextInt(4)));
            } catch (OutOfRangeIndexFiguresException e) {
                e.printStackTrace();
                System.exit(-1);
            }

        }

        figures.forEach(figure -> System.out.println(figure.printFigure()));
    }
}
