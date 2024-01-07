package ma.dnaengineering.backend.controller;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import ma.dnaengineering.backend.service.CsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/csv")
public class CsvController {

    @Autowired
    private CsvService csvService;

    @PostMapping("/upload")
    public ResponseEntity<List<Employee>> uploadCsv(@RequestParam("file") MultipartFile file) {
        try {
            List<Employee> uploadedEmployees = csvService.processCsv(file);
            return new ResponseEntity<>(uploadedEmployees, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = csvService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/job-summary")
    public ResponseEntity<List<JobSummary>> getJobSummary() {
        List<JobSummary> jobSummary = csvService.getJobSummary();
        return new ResponseEntity<>(jobSummary, HttpStatus.OK);
    }
}
