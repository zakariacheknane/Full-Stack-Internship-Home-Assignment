package ma.dnaengineering.backend.ParsingMethod;

import ma.dnaengineering.backend.model.Employee;
import ma.dnaengineering.backend.model.JobSummary;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface CsvParsingMethod {

    List<Employee> parseEmployees(MultipartFile file) throws IOException;

    List<JobSummary> calculateJobSummary(List<Employee> employees);

    List<String[]> parse(InputStream inputStream) throws IOException;
}
