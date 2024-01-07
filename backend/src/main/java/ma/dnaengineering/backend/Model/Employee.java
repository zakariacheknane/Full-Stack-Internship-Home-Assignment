package ma.dnaengineering.backend.Model;

import lombok.*;

@Setter @Getter @ToString @AllArgsConstructor @NoArgsConstructor @Builder
public class Employee {
    private int id;
    private String employeeName;
    private String jobTitle;
    private double salary;
}