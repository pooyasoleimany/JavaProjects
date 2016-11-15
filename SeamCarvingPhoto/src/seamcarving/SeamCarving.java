
package seamcarving;

import java.util.Optional;

public class SeamCarving {

    private static final LogBase log = new ConsoleLog();
    private static final IResizer resizer = new SeamCarveBottomUp();
        
    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            log.error("Failed to run.", e);
        }
    }

    private static void run() {
        IInputOutput io=new FileIO(
            "C:\\Pouneh\\Education\\Tsinghua\\Algorithms and Combinatorics\\Course Work\\Algorithms\\Project\\4-in.txt", 
            "C:\\Pouneh\\Education\\Tsinghua\\Algorithms and Combinatorics\\Course Work\\Algorithms\\Project\\4-out.txt", 
            log
        );
        int[][] matrix;
        
        Optional<InputValues> optionalInputValues = io.tryRead();
        
        if(optionalInputValues.isPresent())
        {
            InputValues inputValues = optionalInputValues.get();
            
            matrix = inputValues.getMatrix();
            
            int initialNumberOfRows = matrix.length;            
            int initialNumberOfColumns = matrix[0].length;
            int changeInRows = inputValues.getFinalNumberOfRows()-initialNumberOfRows;            
            int changeColumns = inputValues.getFinalNumberOfColumns()-initialNumberOfColumns;
            
            resize(matrix, changeColumns);
            rotateRight90(matrix);
            resize(matrix, changeInRows);
            rotateLeft90(matrix);
                    
            if(io.tryWrite(matrix))
            {
                log.debug("Result is saved.");
            }
            else
            {
                log.error("Result is NOT saved.");
            }
        }else
        {
            log.error("Input values are invalid.");
        }
    }

    private static void resize(int[][] matrix, int changeColumns) {
        if(changeColumns > 0)
        {
            for (int i = 0; i < changeColumns; i++) {
                resizer.enlarge(matrix);    
            }
        }else if(changeColumns<0){
            for (int i = 0; i < -changeColumns; i++) {
                resizer.crop(matrix);    
            }
        }
    }

    private static int[][] rotateRight90(int[][] matrix) {
        int[][] rotatedRight90Matrix;
        rotatedRight90Matrix = new int[matrix[0].length][matrix.length];
        for(int i=0; i< matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                rotatedRight90Matrix[j][matrix.length-i-1]=matrix[i][j];
            }
        }
        return rotatedRight90Matrix;
    }
    
    

    private static int[][] rotateLeft90(int[][] matrix) {
        int[][] rotatedLeft90Matrix;
        rotatedLeft90Matrix = new int[matrix[0].length][matrix.length];
        for(int i=0; i< matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                rotatedLeft90Matrix[matrix[0].length-j-1][i]=matrix[i][j];
            }
        }
        return rotatedLeft90Matrix;
    }
}
           