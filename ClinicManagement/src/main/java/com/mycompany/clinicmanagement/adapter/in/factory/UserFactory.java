package com.mycompany.clinicmanagement.adapter.in.factory;

import com.mycompany.clinicmanagement.adapter.in.builder.UserBuilder;
import com.mycompany.clinicmanagement.domain.models.User;
import com.mycompany.clinicmanagement.domain.service.UserDomainService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Factory para crear usuarios de diferentes tipos
 * Utiliza el patrón Factory junto con el Builder para crear usuarios
 * con configuraciones predefinidas
 */
@Component
public class UserFactory {

    private final UserDomainService userDomainService;

    public UserFactory(UserDomainService userDomainService) {
        this.userDomainService = userDomainService;
    }

    /**
     * Crea un usuario de Recursos Humanos
     */
    public User createHRUser(String documentNumber, String fullName, String email) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber(documentNumber)
                .withFullName(fullName)
                .withEmail(email)
                .withPhone(generateRandomPhone())
                .withBirthDate(LocalDate.now().minusYears(25))
                .withAddress("Dirección por defecto")
                .withRole(User.Role.RECURSOS_HUMANOS)
                .withUsername(generateUsername(fullName))
                .withPassword(generateSecurePassword())
                .withActiveStatus(true)
                .build();
    }

    /**
     * Crea un usuario de Personal Administrativo
     */
    public User createAdminUser(String documentNumber, String fullName, String email) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber(documentNumber)
                .withFullName(fullName)
                .withEmail(email)
                .withPhone(generateRandomPhone())
                .withBirthDate(LocalDate.now().minusYears(30))
                .withAddress("Dirección por defecto")
                .withRole(User.Role.PERSONAL_ADMINISTRATIVO)
                .withUsername(generateUsername(fullName))
                .withPassword(generateSecurePassword())
                .withActiveStatus(true)
                .build();
    }

    /**
     * Crea un usuario Médico
     */
    public User createDoctorUser(String documentNumber, String fullName, String email) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber(documentNumber)
                .withFullName(fullName)
                .withEmail(email)
                .withPhone(generateRandomPhone())
                .withBirthDate(LocalDate.now().minusYears(35))
                .withAddress("Dirección por defecto")
                .withRole(User.Role.MEDICO)
                .withUsername(generateUsername(fullName))
                .withPassword(generateSecurePassword())
                .withActiveStatus(true)
                .build();
    }

    /**
     * Crea un usuario Enfermera
     */
    public User createNurseUser(String documentNumber, String fullName, String email) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber(documentNumber)
                .withFullName(fullName)
                .withEmail(email)
                .withPhone(generateRandomPhone())
                .withBirthDate(LocalDate.now().minusYears(28))
                .withAddress("Dirección por defecto")
                .withRole(User.Role.ENFERMERA)
                .withUsername(generateUsername(fullName))
                .withPassword(generateSecurePassword())
                .withActiveStatus(true)
                .build();
    }

    /**
     * Crea un usuario de Soporte de Información
     */
    public User createSupportUser(String documentNumber, String fullName, String email) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber(documentNumber)
                .withFullName(fullName)
                .withEmail(email)
                .withPhone(generateRandomPhone())
                .withBirthDate(LocalDate.now().minusYears(32))
                .withAddress("Dirección por defecto")
                .withRole(User.Role.SOPORTE_INFORMACION)
                .withUsername(generateUsername(fullName))
                .withPassword(generateSecurePassword())
                .withActiveStatus(true)
                .build();
    }

    /**
     * Crea un usuario con datos personalizados
     */
    public User createCustomUser(String documentNumber, String fullName, String email,
            String phone, LocalDate birthDate, String address,
            User.Role role, String username, String password) {
        return UserBuilder.create(userDomainService)
                .withDocumentNumber(documentNumber)
                .withFullName(fullName)
                .withEmail(email)
                .withPhone(phone)
                .withBirthDate(birthDate)
                .withAddress(address)
                .withRole(role)
                .withUsername(username)
                .withPassword(password)
                .withActiveStatus(true)
                .build();
    }

    /**
     * Genera un nombre de usuario basado en el nombre completo
     */
    private String generateUsername(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "user" + System.currentTimeMillis();
        }

        String[] names = fullName.trim().toLowerCase().split("\\s+");
        if (names.length >= 2) {
            return names[0] + names[1].charAt(0);
        } else {
            return names[0];
        }
    }

    /**
     * Genera un teléfono aleatorio
     */
    private String generateRandomPhone() {
        return String.valueOf(1000000000 + (int) (Math.random() * 9000000000L));
    }

    /**
     * Genera una contraseña segura
     */
    private String generateSecurePassword() {
        return "TempPass123!";
    }

    /**
     * Crea usuarios de ejemplo para diferentes roles
     */
    public java.util.List<User> createSampleUsers() {
        return java.util.List.of(
                createHRUser("12345678", "Ana García", "ana.garcia@clinic.com"),
                createAdminUser("87654321", "Carlos López", "carlos.lopez@clinic.com"),
                createDoctorUser("11223344", "Dr. María Rodríguez", "maria.rodriguez@clinic.com"),
                createNurseUser("44332211", "Laura Martínez", "laura.martinez@clinic.com"),
                createSupportUser("55667788", "Pedro Silva", "pedro.silva@clinic.com"));
    }
}
