package app.domain.models;

public class Insurance {
    private String companyName;
    private String policyNumber;
    private boolean active;
    private String expirationDate; // format: dd/MM/yyyy

    // Default constructor
    public Insurance() {
    }

    // Parameterized constructor
    public Insurance(String companyName, String policyNumber, boolean active, String expirationDate) {
        this.companyName = companyName;
        this.policyNumber = policyNumber;
        this.active = active;
        this.expirationDate = expirationDate;
    }

    // Getters and Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    // Utility method: check if policy is expired
    public boolean isExpired(String currentDate) {
        // For now, simple string comparison (you could improve with LocalDate)
        return expirationDate.compareTo(currentDate) < 0;
    }

    @Override
    public String toString() {
        return "Insurance {" +
                "companyName='" + companyName + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", active=" + active +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
