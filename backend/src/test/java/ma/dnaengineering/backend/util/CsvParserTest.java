package ma.dnaengineering.backend.util;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvParserTest {

    @Test
    void testParseEmployees() throws IOException {
        CsvParser csvParser = new CsvParser();
        String csvData = "1,John,Doe,50000\n2,Jane,Smith,60000";
        MultipartFile file = new MockMultipartFile("test.csv", csvData.getBytes());

        List<Employee> employees = csvParser.parseEmployees(file);

        assertEquals(2, employees.size());

        Employee firstEmployee = employees.get(0);
        assertEquals("1", firstEmployee.getId());
        assertEquals("John", firstEmployee.getEmployeeName());
        assertEquals("Doe", firstEmployee.getJobTitle());
        assertEquals(50000, firstEmployee.getSalary());

        Employee secondEmployee = employees.get(1);
        assertEquals("2", secondEmployee.getId());
        assertEquals("Jane", secondEmployee.getEmployeeName());
        assertEquals("Smith", secondEmployee.getJobTitle());
        assertEquals(60000, secondEmployee.getSalary());
    }

    @Test
    void testCalculateJobSummary() {
        CsvParser csvParser = new CsvParser();
        List<Employee> employees = List.of(
                new Employee("1", "John", "Engineer", 50000),
                new Employee("2", "Jane", "Manager", 60000),
                new Employee("3", "Bob", "Engineer", 55000),
                new Employee("4", "Alice", "Manager", 70000)
        );

        List<JobSummary> jobSummaries = csvParser.calculateJobSummary(employees);

        assertEquals(2, jobSummaries.size());

        JobSummary engineerSummary = jobSummaries.get(0);
        assertEquals("Engineer", engineerSummary.getJobTitle());
        assertEquals(52500.0, engineerSummary.getAverageSalary());

        JobSummary managerSummary = jobSummaries.get(1);
        assertEquals("Manager", managerSummary.getJobTitle());
        assertEquals(65000.0, managerSummary.getAverageSalary());
    }
}

