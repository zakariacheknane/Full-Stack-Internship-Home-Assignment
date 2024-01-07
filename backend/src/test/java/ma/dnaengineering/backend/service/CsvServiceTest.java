package ma.dnaengineering.backend.service;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import ma.dnaengineering.backend.util.CsvParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CsvServiceTest {

    @Mock
    private CsvParser csvParser;

    @InjectMocks
    private CsvService csvService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

}
