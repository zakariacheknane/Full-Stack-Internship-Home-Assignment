package ma.dnaengineering.backend.service;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.util.CsvParserUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CsvParserService {
    public List<Employee> parseCsvAndReturnList(MultipartFile file) {
        List<String[]> csvData = CsvParserUtil.readCsvData(file);

        return csvData.stream()
                .skip(1)
                .map(data -> Employee.builder()
                        .id(Integer.parseInt(data[0]))
                        .name(data[1])
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
