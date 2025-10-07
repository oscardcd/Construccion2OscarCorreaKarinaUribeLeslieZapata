
package com.mycompany.clinicmanagement.domain.models;

import java.util.List;
import java.time.LocalDate;

public class Nurse {
    private String nurseId;
    private String fullName;
    private String identification;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private String specialization;
    private String licenseNumber;

    private Nurse() {}

    public static class Builder {
        private Nurse nurse;

        public Builder() {
            this.nurse = new Nurse();
        }

        public Builder nurseId(String nurseId) {
            nurse.nurseId = nurseId;
            return this;
        }

        public Builder fullName(String fullName) {
            nurse.fullName = fullName;
            return this;
        }

        public Builder identification(String identification) {
            nurse.identification = identification;
            return this;
        }

        public Builder email(String email) {
            nurse.email = email;
            return this;
        }

        public Builder phone(String phone) {
            nurse.phone = phone;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            nurse.birthDate = birthDate;
            return this;
        }

        public Builder specialization(String specialization) {
            nurse.specialization = specialization;
            return this;
        }

        public Builder licenseNumber(String licenseNumber) {
            nurse.licenseNumber = licenseNumber;
            return this;
        }

        public Nursing build() {
            if (nurse.nurseId == null || nurse.nurseId.trim().isEmpty()) {
                throw new IllegalStateException("Nurse ID is required");
            }
            if (nurse.fullName == null || nurse.fullName.trim().isEmpty()) {
                throw new IllegalStateException("Full name is required");
            }
            return nurse;
        }
    }
    public String getNurseId() { return nurseId; }
    public String getFullName() { return fullName; }
    public String getIdentification() { return identification; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public LocalDate getBirthDate() { return birthDate; }
    public String getSpecialization() { return specialization; }
    public String getLicenseNumber() { return licenseNumber; }
}