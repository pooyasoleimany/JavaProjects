package seamcarving;

public class ConsoleLog implements LogBase {

    public ConsoleLog() {
    }

    @Override
    public void error(String message, Exception exception) {
        writeLine("error", message, exception);
    }

    @Override
    public void info(String message, Exception exception) {
        writeLine("info", message, exception);
    }

    @Override
    public void debug(String message, Exception exception) {
        writeLine("debug", message, exception);
    }

    @Override
    public void error(String message) {
        writeLine("error", message);
    }

    @Override
    public void info(String message) {
        writeLine("info", message);
    }

    @Override
    public void debug(String message) {
        writeLine("debug", message);
    }
    
    private void writeLine(String level, String message)
    {
        System.out.println(level +"|"+ message);
    }
    
    private void writeLine(String level, String message, Exception ex)
    {
        System.out.println(level +"|"+ message + "|" + ex.toString());
    }
}
