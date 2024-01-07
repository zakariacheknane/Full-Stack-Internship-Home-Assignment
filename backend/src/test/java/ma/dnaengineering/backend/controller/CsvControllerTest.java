package ma.dnaengineering.backend.controller;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import ma.dnaengineering.backend.service.CsvService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CsvControllerTest {

    @Mock
    private CsvService csvService;

    @InjectMocks
    private CsvController csvController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUploadCsvSuccess() {
        MultipartFile file = mock(MultipartFile.class);
        ResponseEntity<List<Employee>> responseEntity = csvController.uploadCsv(file);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(0, responseEntity.getBody().size()); // Assuming you expect an empty list in the success case
    }

    @Test
    void testGetEmployees() {
        List<Employee> expectedEmployees = Arrays.asList(new Employee(), new Employee());
        when(csvService.getEmployees()).thenReturn(expectedEmployees);

        ResponseEntity<List<Employee>> responseEntity = csvController.getEmployees();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedEmployees, responseEntity.getBody());
    }

    @Test
    void testGetJobSummary() {
        List<JobSummary> expectedJobSummary = Arrays.asList(new JobSummary(), new JobSummary());
        when(csvService.getJobSummary()).thenReturn(expectedJobSummary);

        ResponseEntity<List<JobSummary>> responseEntity = csvController.getJobSummary();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedJobSummary, responseEntity.getBody());
    }
}
