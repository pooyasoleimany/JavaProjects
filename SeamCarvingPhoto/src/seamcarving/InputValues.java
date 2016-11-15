
package seamcarving;

public class InputValues {
    private final int[][] matrix;
    private final int finalNumberOfColumns;
    private final int finalNumberOfRows;
    int[][] getMatrix(){
        return this.matrix;
    }
    int getFinalNumberOfColumns(){
        return this.finalNumberOfColumns;
    }
    int getFinalNumberOfRows(){
        return this.finalNumberOfRows;
    }
    
    public InputValues(int[][] matrix, int finalNumberOfRows, int finalNumberOfColumns){
        this.finalNumberOfRows=finalNumberOfRows;
        this.finalNumberOfColumns=finalNumberOfColumns;
        this.matrix=matrix;
    }
}
