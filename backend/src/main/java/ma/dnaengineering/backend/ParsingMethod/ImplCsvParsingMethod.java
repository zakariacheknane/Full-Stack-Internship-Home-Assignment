package ma.dnaengineering.backend.ParsingMethod;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ImplCsvParsingMethod implements CsvParsingMethod {

    private static final Logger LOGGER = Logger.getLogger(ImplCsvParsingMethod.class.getName());

    private static final int SCALE = 2; // 2 chiffres après la virgule

    @Override
    public List<Employee> parseEmployees(MultipartFile file) throws IOException {
        List<Employee> employees = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4 && isValidSalary(data[3])) {
                    try {
                        Employee employee = new Employee();
                        employee.setId(data[0]);
                        employee.setEmployeeName(data[1]);
                        employee.setJobTitle(data[2]);
                        employee.setSalary(Double.parseDouble(data[3]));
                        employees.add(employee);
                    } catch (NumberFormatException e) {
                        LOGGER.log(Level.WARNING, "Invalid salary format for line: " + line);
                    }
                } else {
                    LOGGER.log(Level.WARNING, "Invalid data format for line: " + line);
                }
            }
        }

        return employees;
    }

    @Override
    public List<JobSummary> calculateJobSummary(List<Employee> employees) {
        Map<String, BigDecimal> jobTitleTotalSalary = new HashMap<>();
        Map<String, Integer> jobTitleCount = new HashMap<>();

        for (Employee employee : employees) {
            String jobTitle = employee.getJobTitle();
            double salary = employee.getSalary();

            jobTitleTotalSalary.put(jobTitle, jobTitleTotalSalary.getOrDefault(jobTitle, BigDecimal.ZERO).add(BigDecimal.valueOf(salary)));
            jobTitleCount.put(jobTitle, jobTitleCount.getOrDefault(jobTitle, 0) + 1);
        }

        List<JobSummary> jobSummaries = new ArrayList<>();
        for (Map.Entry<String, BigDecimal> entry : jobTitleTotalSalary.entrySet()) {
            String jobTitle = entry.getKey();
            BigDecimal totalSalary = entry.getValue();
            int count = jobTitleCount.get(jobTitle);
            BigDecimal averageSalary = totalSalary.divide(BigDecimal.valueOf(count), SCALE, RoundingMode.HALF_UP);

            JobSummary jobSummary = new JobSummary();
            jobSummary.setJobTitle(jobTitle);
            jobSummary.setAverageSalary(averageSalary.doubleValue()); // Convert to double
            jobSummaries.add(jobSummary);
        }

        return jobSummaries;
    }


    @Override
    public List<String[]> parse(InputStream inputStream) throws IOException {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                rows.add(data);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error parsing CSV from InputStream", e);
            throw e;
        }

        return rows;
    }

    private boolean isValidSalary(String salary) {
        try {
            Double.parseDouble(salary);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
