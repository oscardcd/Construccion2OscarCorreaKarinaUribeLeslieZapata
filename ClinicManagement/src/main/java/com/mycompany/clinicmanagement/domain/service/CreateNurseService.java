
package com.mycompany.clinicmanagement.domain.service;

import com.mycompany.clinicmanagement.domain.models.Nurse;
import com.mycompany.clinicmanagement.domain.ports.NursePort;
import java.time.LocalDate;

public class CreateNurseService {
    private final NursePort nursePort;


    public CreateNurseService(NursePort nursePort) {
        this.nursePort = nursePort;
    }

    public Nurse create(String nurseId, String fullName, String identification,
            String email, String phone, LocalDate birthDate,
            String specialization, String licenseNumber) {
        
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
