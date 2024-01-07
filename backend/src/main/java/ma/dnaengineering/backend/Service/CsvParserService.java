package ma.dnaengineering.backend.Service;

import ma.dnaengineering.backend.Model.Employee;
import ma.dnaengineering.backend.Util.CsvParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CsvParserService {
	 public List<Employee> parseCsvAndReturnList(MultipartFile file) {
	        List<String[]> csvData = CsvParser.readCsvData(file);

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

	    public Map<String, Double> calculateAverageSalary(List<Employee> employees) {
	        return employees.stream()
	                .collect(Collectors.groupingBy(Employee::getJobTitle,
	                        Collectors.averagingDouble(Employee::getSalary)));
	    }
	}