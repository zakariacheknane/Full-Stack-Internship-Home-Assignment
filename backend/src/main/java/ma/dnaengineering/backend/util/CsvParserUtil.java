package ma.dnaengineering.backend.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The `CsvParserUtil` class provides utility methods for parsing CSV files.
 */
public class CsvParserUtil {

    /**
     * Reads CSV data from a given MultipartFile and returns a list of String arrays representing CSV rows.
     *
     * @param file The MultipartFile containing the CSV data.
     * @return A list of String arrays representing CSV rows.
     */
    public static List<String[]> readCsvData(MultipartFile file) {
        List<String[]> csvData = new ArrayList<>();
        try {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                String line;
                // Read each line from the CSV file and split into String array using comma as the delimiter
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    csvData.add(data);
                }
            } catch (IOException e) {
                // Handle IOException if an error occurs while reading the file
                e.printStackTrace();
            }
        } catch (Exception e) {
            // Handle general exception if an error occurs
            e.printStackTrace();
        }

        return csvData;
    }
}
