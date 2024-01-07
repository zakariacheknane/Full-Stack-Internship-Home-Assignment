package ma.dnaengineering.backend.services;
import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.util.CsvParserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** The `CsvParserService` class provides functionality
to parse CSV files and perform operations on employee data.**/
@Service
public class CsvParserService {
	/**
    * Parses the CSV file and returns a list of employee objects.
    *
    * @param file The CSV file to be parsed.
    * @return A list of employee objects.
    */
    public List<Employee> parseCsvAndReturnListEmployees(MultipartFile file) {
    	// Read CSV data from the file using CsvParserUtil
        List<String[]> csvData = CsvParserUtil.readCsvData(file);
     // Skip the header row and map each CSV row to an Employee object
        return csvData.stream()
                .skip(1)
                .map(data -> Employee.builder()
                        .id(Integer.parseInt(data[0]))
                        .employeeName(data[1])
                        .jobTitle(data[2])
                        .salary(Double.parseDouble(data[3]))
                        .build())
                .collect(Collectors.toList());
    }
    /**
     * Calculates the average salary for each job title from the provided list of employees.
     *
     * @param employees The list of employees.
     * @return A map containing job titles as keys and average salaries as values.
     */
    public Map<String, Double> calculateAverageSalary(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getJobTitle,
                        Collectors.averagingDouble(Employee::getSalary)));
    }
}
