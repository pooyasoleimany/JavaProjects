
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
            "C:\\Users\\pnezhadian\\Google Drive\\Personal\\Pouneh\\Project\\1-in.txt", 
            "C:\\Users\\pnezhadian\\Google Drive\\Personal\\Pouneh\\Project\\1-out.txt", 
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
                log.debug("Result is NOT saved.");
            }
        }
    }
}
             
        
    

