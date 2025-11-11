package clinickol.clinicmanagement.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "health_insurances")
public class HealthInsurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String companyName;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private boolean policyStatus = true;

    @NotNull
    @Future
    @Column(nullable = false)
    private LocalDate policyExpirationDate;

    public HealthInsurance() {
    }

    public long getRemainingDays() {
        if (policyExpirationDate == null) {
            return 0;
        }
        LocalDate today = LocalDate.now();
        if (policyExpirationDate.isBefore(today)) {
            return 0;
        }
        return ChronoUnit.DAYS.between(today, policyExpirationDate);
    }

    public boolean isPolicyActive() {
        return policyStatus && policyExpirationDate != null && policyExpirationDate.isAfter(LocalDate.now());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean isPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(boolean policyStatus) {
        this.policyStatus = policyStatus;
    }

    public LocalDate getPolicyExpirationDate() {
        return policyExpirationDate;
    }

    public void setPolicyExpirationDate(LocalDate policyExpirationDate) {
        this.policyExpirationDate = policyExpirationDate;
    }

    

}
