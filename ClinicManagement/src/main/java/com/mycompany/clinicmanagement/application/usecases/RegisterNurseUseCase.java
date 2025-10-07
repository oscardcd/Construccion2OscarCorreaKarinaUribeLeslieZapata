package com.mycompany.clinicmanagement.application.usecases;

import com.mycompany.clinicmanagement.domain.models.Nurse;
import com.mycompany.clinicmanagement.domain.ports.NursePort;
import java.time.LocalDate;
import java.util.Optional;

public class RegisterNurseUseCase {
    private final NursePort nursePort;


    public RegisterNurseUseCase(NursePort nursePort) {
        this.nursePort = nursePort;
    }
    
    public Nurse register(String nurseId, String fullName, String identification,
            String email, String phone, LocalDate birthDate,
            String specialization, String licenseNumber) {
        Optional<Nurse> existing = nursePort.findAll().stream()
                .filter(n -> n.getLicenseNumber().equalsIgnoreCase(licenseNumber))
                .findFirst();
        if (existing.isPresent())
            throw new IllegalArgumentException("Nurse already registered with this license number");
        
        Nurse nurse = new Nurse.Builder()
                .nurseId(nurseId)
                .fullName(fullName)
                .identification(identification)
                .email(email)
                .phone(phone)
               .birthDate(birthDate)
               .specialization(specialization)
               .licenseNumber(licenseNumber)
               .build();
        return nursePort.save(nurse);
    }
}

