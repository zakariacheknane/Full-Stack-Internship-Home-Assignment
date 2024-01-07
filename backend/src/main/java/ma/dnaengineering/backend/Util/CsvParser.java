package ma.dnaengineering.backend.Util;

import java.util.ArrayList;
import java.util.List;
import java.io.*;
import org.springframework.web.multipart.MultipartFile;

public class CsvParser {
	  public static List<String[]> readCsvData(MultipartFile file) {
	        List<String[]> csvData = new ArrayList<>();
	        try {
	            try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

	                String line;
	                while ((line = br.readLine()) != null) {
	                    String[] data = line.split(",");
	                    csvData.add(data);
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return csvData;
	    }
	}
