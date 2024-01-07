package ma.dnaengineering.backend.model;
// Employee class

public class Employee {
    private String id;
    private String employeeName;
    private String jobTitle;
    private double salary;

    // Constructeur vide
    public Employee() {
        // Constructeur vide
    }

    public Employee(String id, String employeeName, String jobTitle, double salary) {
        this.id = id;
        this.employeeName = employeeName;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
// Getters and setters
}
