package ma.dnaengineering.backend.service;

import lombok.Getter;
import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import ma.dnaengineering.backend.ParsingMethod.CsvParsingMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class CsvService {

    private final CsvParsingMethod csvParsingMethod;

    @Getter
    private List<Employee> employees;
    @Getter
    private List<JobSummary> jobSummary;

    public CsvService(CsvParsingMethod csvParsingMethod) {
        this.csvParsingMethod = csvParsingMethod;
    }

    public List<Employee> processCsv(MultipartFile file) {
        try {
            employees = csvParsingMethod.parseEmployees(file);
            jobSummary = csvParsingMethod.calculateJobSummary(employees);
            return employees; // Return the list of employees
        } catch (IOException e) {
            e.printStackTrace();
            return List.of(); // Return an empty list or handle the error accordingly
        }
    }
}
