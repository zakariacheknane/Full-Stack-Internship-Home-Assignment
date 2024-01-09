package ma.dnaengineering.backend;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.services.CsvParserService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Map;

/**
 * Tests for the BackendApplication functionality.
 */
@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private CsvParserService csv;

    /**
     * Test the CSV parsing and employee list generation functionality.
     */
    @Test
    void testParseCsvAndReturnList() {
        // Arrange
        MultipartFile file = new MockMultipartFile("data", "filename.csv", "text/plain",
                "id,employee_name,job_title,salary\n1,Jon Ball,Mobile App Developer,7348.0\n2,Lindsey Young,Project Manager (IT),1499.0".getBytes());

        // Act
        List<Employee> result = csv.parseCsvAndReturnList(file);

        // Assert
        assertEquals(2, result.size());
        assertEquals("Jon Ball", result.get(0).getEmployeeName());
        assertEquals("Mobile App Developer", result.get(0).getJobTitle());
        assertEquals(7348.0, result.get(0).getSalary());
    }

    /**
     * Test the calculation of average salaries based on job titles.
     */
    @Test
    void testCalculateAverageSalary() {
        // Arrange
        List<Employee> employees = Arrays.asList(
                new Employee(1, "Kimberly Allen", "IT Support Specialist", 13428.0),
                new Employee(2, "Jason Johnson", "IT Support Specialist", 10087.0),
                new Employee(3, "John Griffin", "Systems Administrator",7397.0)
        );

        // Act
        Map<String, Double> averageSalaries = csv.calculateAverageSalary(employees);

        // Assert
        assertEquals(2, averageSalaries.size());
        assertEquals(11,757.5, averageSalaries.get("Developer"));
        assertEquals(7397.0, averageSalaries.get("Systems Administrator"));
    }

    /**
     * Test to ensure that the application context loads successfully.
     */
    @Test
    void contextLoads() {
    }
}
