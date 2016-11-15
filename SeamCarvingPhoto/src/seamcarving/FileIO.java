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
        int finalNumberOfColumns;       
        int finalNumberOfRows;
        try(Stream<String> stream = Files.lines(Paths.get(inputFileName)))
        {
            List<String> lines = stream
                .filter(s->!s.isEmpty())
                .collect(Collectors.toList());
            
            if(lines.isEmpty())
                throw new InputFileFormatException("The file is empty "+ inputFileName);
            
            int[] line1 = toIntArray(splitByWhiteSpace(lines.get(0)));
            
            if(line1.length != 4)
                throw new InputFileFormatException("First line should contain 3 integers "+ inputFileName);
                       
            finalNumberOfRows = line1[2];
            finalNumberOfColumns = line1[3]; 
            int rowsCount = line1[0];
            int colsCount = line1[1];
            
            if(rowsCount<1 || colsCount<1 || finalNumberOfColumns<1 || finalNumberOfRows<1)
                throw new InputFileFormatException("First line should contain 3 POSITIVE integers "+ inputFileName);
            
            if(lines.size()!=rowsCount+1)
                throw new InputFileFormatException(
                    "Since the first line is '" +
                    lines.get(0)+
                    "' the file should contain " +
                    rowsCount+1+
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
            
            return Optional.of(new InputValues(matrix, finalNumberOfRows, finalNumberOfColumns));
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

    private int[] toIntArray(String[] row) {
        int[] result = new int[row.length];
        for (int i = 0; i < row.length; i++) {
            result[i] = Integer.parseInt(row[i]);
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
  