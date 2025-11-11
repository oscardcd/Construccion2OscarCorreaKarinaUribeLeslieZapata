package clinickol.clinicmanagement.repository;


import clinickol.clinicmanagement.domain.model.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByIdAndActiveTrue(String id);

    Optional<Patient> findByiD(String id);

    List<Patient> findByActiveTrue();

    boolean existsById(String id);

    List<Patient> findByFullNameContainingIgnoreCase(String fullName);
}
