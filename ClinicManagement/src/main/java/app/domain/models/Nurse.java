package app.domain.models;

public class Nurse extends User {
    private String username;
    private String password;
    private String department; // e.g., surgery, vaccination, recovery, etc.

    // Constructor
    public Nurse(String id, String name, int age, String username, String password, String department) {
        super(id, name, age, "Nurse"); // Calls the Person constructor with role
        this.username = username;
        this.password = password;
        this.department = department;
        
    }

    // Methods specific to Nurse
    public void assistProcedure(String procedure, String petName) {
        System.out.println(name + " assisted in " + procedure + " for pet " + petName);
    }

    public void applyMedication(String medication, String petName, String dose) {
        System.out.println(name + " applied " + dose + " of " + medication + " to pet " + petName);
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
