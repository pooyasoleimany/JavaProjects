
package seamcarving;

public class InputValues {
    private int[][] matrix;
    private int columnsToRemove;
    int[][] getMatrix(){
        return this.matrix;
    }
    int getColumnRemove(){
        return this.columnsToRemove;
    }
    public InputValues(int[][] matrix, int columnToRemove){
        this.columnsToRemove=columnToRemove;
        this.matrix=matrix;
    }
}
