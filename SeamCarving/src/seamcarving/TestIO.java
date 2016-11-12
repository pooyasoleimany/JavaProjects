
package seamcarving;

import java.util.Optional;


public class TestIO implements IInputOutput{
    @Override
    public Optional<InputValues> tryRead() {  
        int[][] matrix= {
             {1,2,3,2,3},
             {2,1,2,3,4},
             {3,1,3,4,5},
             {2,3,1,3,5},
             {2,3,3,3,5}
        };
        return Optional.of(new InputValues(matrix,1));
    }

    @Override
    public boolean tryWrite(int[][] m) {return false;}
}
