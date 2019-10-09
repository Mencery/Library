package figures;

/**
 * @author Denys Plekhanov
 */
public enum Color {
    Blue,
    Green,
    Red,
    Yellow,
    Gray;

    public static Color[] index = new Color[]{Blue, Green, Red, Yellow, Gray};
    public static  Color getColorByIndex(int i){
        if(i<0 || i>Color.values().length){
            throw new ArrayIndexOutOfBoundsException();
        }
        else {
            return index[i];
        }
    }
}

