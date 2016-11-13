package seamcarving;

/**
 *
 * @author Pouneh
 */
public interface LogBase {
    void error(String message, Exception exception);
    void info(String message, Exception exception);
    void debug(String message, Exception exception);
    void error(String message);
    void info(String message);
    void debug(String message);
}
