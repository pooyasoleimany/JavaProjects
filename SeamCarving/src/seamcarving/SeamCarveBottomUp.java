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
        int[][] resizedMatrix=this.removePixel(distanceMatrix, seamPath);
        return resizedMatrix;
    }

    @Override
    public int[][] enlarge(int[][] matrix) {
        return matrix;
    }

    private int[][] calculateDistance(int[][] matrix) {
        return matrix;
    }

    private int[] calculateSeam(int[][] distanceMatrix) {
        return distanceMatrix[0];
    }

    private int[][] removePixel(int[][] distanceMatrix, int[] seamPath) {
        return distanceMatrix;
    }
    
}
