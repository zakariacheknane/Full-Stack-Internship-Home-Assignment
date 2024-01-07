package ma.dnaengineering.backend.Controller;
import ma.dnaengineering.backend.Model.Employee;
import ma.dnaengineering.backend.Service.CsvParserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/csv")
public class CsvController {
	 private CsvParserService csvParserService;

	    public CsvController(CsvParserService csvParserService) {
	        this.csvParserService = csvParserService;
	    }

	    @PostMapping("/upload")
	    public ResponseEntity<List<Employee>> parseCsvAndReturnList(@RequestParam("file") MultipartFile file) {
	        List<Employee> employees = csvParserService.parseCsvAndReturnList(file);
	        return new ResponseEntity<>(employees, HttpStatus.OK);
	    }

	    @PostMapping("/summary")
	    public ResponseEntity<Map<String, Double>> getAverageSalarySummary(@RequestParam("file") MultipartFile file) {
	        List<Employee> employees = csvParserService.parseCsvAndReturnList(file);
	        Map<String, Double> averageSalaryMap = csvParserService.calculateAverageSalary(employees);
	        return new ResponseEntity<>(averageSalaryMap, HttpStatus.OK);
	    }
	}