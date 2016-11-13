
package seamcarving;

import java.util.Optional;

public class SeamCarving {

    private static final LogBase log = new ConsoleLog();
    
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
        IResizer resizer= new SeamCarveBottomUp();
        int[][] result;
        
        Optional<InputValues> optionalInputValues = io.tryRead();
        
        if(optionalInputValues.isPresent())
        {
            InputValues inputValues = optionalInputValues.get();
            
            result = inputValues.getMatrix();
            
            for (int i = 0; i < inputValues.getColumnRemove(); i++) {
                result= resizer.crop(result);
            }
            
            if(io.tryWrite(result))
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
}
           