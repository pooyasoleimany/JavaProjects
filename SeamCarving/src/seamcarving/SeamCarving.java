
package seamcarving;


public class SeamCarving {
    public static void main(String[] args) {
        IInputOutput io=new TestIO();
        IResizer resizer= new SeamCarveBottomUp();
        int[][] result;
        InputValues inputValues= io.tryRead();
        result = inputValues.getMatrix();
        for (int i = 0; i < inputValues.getColumnRemove(); i++) {
            result= resizer.crop(result);
        }
        io.tryWrite(result);
    }
}
             
        
    

