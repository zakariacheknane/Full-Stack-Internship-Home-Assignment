package ma.dnaengineering.backend.Controllers;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.services.CsvParserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * The `CsvController` class handles CSV-related endpoints for employee data.
 */
@RestController
@RequestMapping("/api/csv")
public class CsvController {
    private  CsvParserService csvService;

    /**
     * Constructor for CsvController.
     *
     * @param csvParserService The service responsible for CSV parsing and processing.
     */
    public CsvController(CsvParserService csvService) {
        this.csvService = csvService;
    }

    /**
     * Endpoint for uploading a CSV file and parsing its data to return a list of employees.
     *
     * @param file The CSV file to be uploaded.
     * @return ResponseEntity containing the list of parsed employees and HTTP status.
     */
    @PostMapping("/upload")
    public ResponseEntity<List<Employee>> parseCsvAndReturnList(@RequestParam("file") MultipartFile file) {
        // Parse CSV file and get the list of employees
        List<Employee> employees = csvService.parseCsvAndReturnList(file);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Endpoint for uploading a CSV file and obtaining a summary of average salaries based on job titles.
     *
     * @param file The CSV file to be uploaded.
     * @return ResponseEntity containing the average salary summary and HTTP status.
     */
    @PostMapping("/summary")
    public ResponseEntity<Map<String, Double>> getAverageSalarySummary(@RequestParam("file") MultipartFile file) {
        // Parse CSV file and get the list of employees
        List<Employee> employees = csvService.parseCsvAndReturnList(file);

        // Calculate average salary summary based on job titles
        Map<String, Double> averageSalaryMap = csvService.calculateAverageSalary(employees);

        return new ResponseEntity<>(averageSalaryMap, HttpStatus.OK);
    }
}
