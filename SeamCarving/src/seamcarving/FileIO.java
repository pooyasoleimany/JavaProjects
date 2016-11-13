package seamcarving;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileIO implements IInputOutput{
    private final String inputFileName;
    private final String outputFileName;
    private final LogBase log;
    FileIO (String inputFileName, String outputFileName, LogBase log){
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        this.log = log;
    }
    
    @Override
    public Optional<InputValues> tryRead() {
        int[][] matrix;
        int columnToRemove;
        try(Stream<String> stream = Files.lines(Paths.get(inputFileName)))
        {
            List<String> lines = stream
                .filter(s->!s.isEmpty())
                .collect(Collectors.toList());
            
            if(lines.isEmpty())
                throw new InputFileFormatException("The file is empty "+ inputFileName);
            
            int[] line1 = toIntArray(splitByWhiteSpace(lines.get(0)));
            
            if(line1.length != 3)
                throw new InputFileFormatException("First line should contain 3 integers "+ inputFileName);
            
            columnToRemove = line1[2];
            int rowsCount = line1[0];
            int colsCount = line1[1];
            
            if(rowsCount<1 || colsCount<1 || columnToRemove<1)
                throw new InputFileFormatException("First line should contain 3 POSITIVE integers "+ inputFileName);
            
            if(lines.size()!=rowsCount+1)
                throw new InputFileFormatException(
                    "Since the first line is '" +
                    lines.get(0)+
                    "' the file should contain " +
                    rowsCount+
                    " not empty lines "+ 
                    inputFileName
                );

            matrix = new int[rowsCount][];
            for (int i = 0; i < rowsCount; i++) {
                matrix[i] = toIntArray(
                    splitByWhiteSpace(lines.get(i+1))
                );
                
                if(matrix[i].length != colsCount)
                    throw new InputFileFormatException("Row " +i+" of the matris does not contain " + colsCount + " items " + inputFileName);
            }
            
            return Optional.of(new InputValues(matrix, columnToRemove));
        }
        catch(IOException ex)
        {
            this.log.error("Exception in reading the file "+inputFileName, ex);
            return Optional.empty();
        }
    }
     
    @Override
    public boolean tryWrite(int[][] m) {
        System.out.println("Writing to file: " + outputFileName);
        // Files.newBufferedWriter() uses UTF-8 encoding by default
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName))) {
            String result = toString(m);
            writer.write(result);
        } catch (IOException ex) {
            log.error("Was not able to write matris to the output file.", ex);
        }
        
        return true;
    }

    private int[] toIntArray(String[] strings) {
        int[] result = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            result[i] = Integer.parseInt(strings[i]);
        }
        return result;
    }

    private String[] splitByWhiteSpace(String string) {
        return string.split("[ ]+", 0);
    }

    private String toString(int[][] m) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : m) {
            for (int col = 0; col < row.length; col++) {
                sb.append(row[col]);
                sb.append(" ");
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
  