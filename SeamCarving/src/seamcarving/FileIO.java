package seamcarving;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO implements IInputOutput{
    private final String fileName;
    private final LogBase log;
    FileIO (String fileName, LogBase log){
        this.fileName = fileName;
        this.log = log;
    }

    @Override
    public Optional<InputValues> tryRead() {
        int[][] matrix = null;
        int columnToRemove = 0;
        try(Stream<String> stream = Files.lines(Paths.get(fileName)))
        {
            List<String> lines = stream.collect(Collectors.toList());
            if(lines.isEmpty())
                throw new InputFileFormatException("The file is empty "+ fileName);
            
            var int[] line1 = lines.get(0).split(split" ",);
            
            return Optional.of(new InputValues(matrix, columnToRemove));
        }
        catch(IOException ex)
        {
            this.log.error("Exception in reading the file "+fileName, ex);
            return Optional.empty();
        }
    }
     
    @Override
    public boolean tryWrite(int[][] m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
  