package seamcarving;

/**
 *
 * @author Pouneh
 */
class SeamCarveBottomUp implements IResizer {

    public SeamCarveBottomUp() {
    }
    
    @Override
    public int[][] crop(int[][] matrix) {
        int[][] distanceMatrix = this.calculateDistance(matrix);
        int[] seamPath= this.calculateSeam(distanceMatrix);
        int[][] resizedMatrix=this.removePixel(matrix, seamPath);
        return resizedMatrix;
    }

    @Override
    public int[][] enlarge(int[][] matrix) {
        return matrix;
    }

    private int[][] calculateDistance(int[][] matrix) {
        int[][] distanceMatrix =new int[matrix.length][matrix[0].length];
        for(int col=0; col < distanceMatrix[0].length; col++ ){
            distanceMatrix[0][col]=matrix[0][col];
        }
        for (int row = 1; row < distanceMatrix.length; row++) {
            for(int col=0; col < distanceMatrix[0].length; col++ ){
                distanceMatrix[row][col]=
                    matrix[row][col]+
                    distanceMatrix[row-1][
                        findIndexOfMinimum(
                            distanceMatrix[row-1], 
                            col-1, 
                            3
                        )
                    ];
            }
        }
        return distanceMatrix;
    }

    private int[] calculateSeam(int[][] distanceMatrix) {
        int[] seam=new int[distanceMatrix.length];
        seam[seam.length-1]= findIndexOfMinimum(distanceMatrix[distanceMatrix.length-1], 0, distanceMatrix[0].length);
        for (int row= distanceMatrix.length-2; row>=0;row--){
            seam[row]=findIndexOfMinimum(distanceMatrix[row], seam[row+1]-1, 3);
        }
        return seam;
    }
    
    private int[][] removePixel(int[][] matrix, int[] seamPath) {
       int columnCount =matrix[0].length;
        int[][]resultMatrix= new int[matrix.length][columnCount - 1];
        for (int row=0; row<resultMatrix.length;row++){
            int destinationCol=0;
            for (int col = 0; col < columnCount; col++) {
                if (col!=seamPath[row]){
                    resultMatrix[row][destinationCol]=matrix[row][col]; 
                    destinationCol++;
                }
            }
        }
        return resultMatrix;
    }
    
    private int findIndexOfMinimum(int array[], int start, int length){
        if (start<0){
            length=length+start;
            start=0;   
        }else if(start+length>array.length){
            length= array.length-start;
        }
        int indexMin= start;
        for (int i = start+1; i < start+length ; i++) {
            if (array[i]<array[indexMin]){
                indexMin=i;
            }
        }
        return indexMin;
    }
}
