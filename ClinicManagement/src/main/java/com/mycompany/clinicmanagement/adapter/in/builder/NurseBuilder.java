package com.mycompany.clinicmanagement.adapter.in.builder;

import com.mycompany.clinicmanagement.domain.models.Nurse;
import com.mycompany.clinicmanagement.adapter.in.validator.NurseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class NurseBuilder {

    @Autowired
    private NurseValidator nurseValidator;

    public Nurse build(String fullName, String idNumber, String email, String phoneNumber,
                      LocalDate birthDate, String address, String username, String password,
                      String specialization) {

        Nurse nurse = new Nurse();
        nurse.setNurseId(generateNurseId());
        nurse.setFullName(nurseValidator.fullNameValidator(fullName));
        nurse.setIdNumber(nurseValidator.idNumberValidator(idNumber));
        nurse.setEmail(nurseValidator.emailValidator(email));
        nurse.setPhoneNumber(nurseValidator.phoneNumberValidator(phoneNumber));
        nurse.setBirthDate(nurseValidator.birthDateValidator(birthDate));
        nurse.setAddress(nurseValidator.addressValidator(address));
        nurse.setUsername(nurseValidator.usernameValidator(username));
        nurse.setPassword(nurseValidator.passwordValidator(password));
        nurse.setSpecialization(nurseValidator.specializationValidator(specialization));
        nurse.setActive(true);

        return nurse;
    }

    public Nurse buildForUpdate(Nurse existingNurse, String fullName, String phoneNumber, 
                               String address, String specialization) {
        existingNurse.setFullName(nurseValidator.fullNameValidator(fullName));
        existingNurse.setPhoneNumber(nurseValidator.phoneNumberValidator(phoneNumber));
        existingNurse.setAddress(nurseValidator.addressValidator(address));
        existingNurse.setSpecialization(nurseValidator.specializationValidator(specialization));
        return existingNurse;
    }

    private String generateNurseId() {
        return "NUR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}