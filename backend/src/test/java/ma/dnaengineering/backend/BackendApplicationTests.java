package ma.dnaengineering.backend;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.service.CsvParserService;
import ma.dnaengineering.backend.util.CsvParserUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	private CsvParserService csvParserService;
	@Test
	void testParseCsvAndReturnList() {
		// Arrange
		MultipartFile file = new MockMultipartFile("data", "filename.csv", "text/plain", "id,employee_name,job_title,salary\n1,John Doe,Developer,60000\n2,Jane Doe,Manager,80000".getBytes());

		// Act
		List<Employee> result = csvParserService.parseCsvAndReturnList(file);

		// Assert
		assertEquals(2, result.size());
		assertEquals("John Doe", result.get(0).getName());
		assertEquals("Developer", result.get(0).getJobTitle());
		assertEquals(60000, result.get(0).getSalary());
	}

	@Test
	void testCalculateAverageSalary() {
		List<Employee> employees = Arrays.asList(
				new Employee(1, "John Doe", "Developer", 60000.0),
				new Employee(2, "Jane Doe", "Developer", 70000.0),
				new Employee(3, "Bob Smith", "Manager", 80000.0)
		);

		// Act
		Map<String, Double> averageSalaries = csvParserService.calculateAverageSalary(employees);

		// Assert
		assertEquals(2, averageSalaries.size());
		assertEquals(65000.0, averageSalaries.get("Developer"));
		assertEquals(80000.0, averageSalaries.get("Manager"));
	}

	@Test
	void contextLoads() {
	}
}
