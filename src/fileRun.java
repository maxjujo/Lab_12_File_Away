import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class fileRun {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                try (InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

                    int line = 0;
                    while ((rec = reader.readLine()) != null) {
                        line++;
                        System.out.printf("\nLine %4d %-60s ", line, rec);
                    }
                    System.out.println("\n\nData file read!");
                    // Call the processFile method to analyze the file
                    processFile(selectedFile.getAbsolutePath());

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Call the processFile method if you want to analyze the file
                // processFile(selectedFile.getAbsolutePath());

            } else {
                System.out.println("No file selected!!! ... exiting.\nRun the program again and select a file.");
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

        private static void processFile (String filePath){
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                int lineCount = 0;
                int wordCount = 0;
                int charCount = 0;

                while ((line = reader.readLine()) != null) {
                    lineCount++;
                    charCount += line.length();
                    // Count words using a simple split by whitespace
                    wordCount += line.split("\\s+").length;
                }

                JOptionPane.showMessageDialog(null,
                        "File Name: " + filePath +
                                "\nNumber of Lines: " + lineCount +
                                "\nNumber of Words: " + wordCount +
                                "\nNumber of Characters: " + charCount);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
