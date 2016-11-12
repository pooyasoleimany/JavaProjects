
package seamcarving;

import java.util.Optional;

public interface IInputOutput {
    public Optional<InputValues> tryRead();
    public boolean tryWrite(int[][] m);
}
