package com.mycompany.clinicmanagement.console;

import com.mycompany.clinicmanagement.application.port.PatientServicePort;
import com.mycompany.clinicmanagement.application.port.UserServicePort;
import com.mycompany.clinicmanagement.domain.models.Patient;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Aplicación de consola para gestión de pacientes
 * Interfaz de usuario para Personal Administrativo
 */
@Component
public class PatientConsoleApp {

    private final PatientServicePort patientService;
    private final UserServicePort userService;
    private final Scanner scanner;

    public PatientConsoleApp(PatientServicePort patientService, UserServicePort userService) {
        this.patientService = patientService;
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Ejecuta la aplicación de consola para pacientes
     */
    public void run() {
        System.out.println("🏥 Sistema de Gestión de Pacientes");
        System.out.println("==================================");

        // Verificar que existe un usuario administrativo
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            System.out.println("❌ No se encontró un usuario administrativo válido.");
            System.out
                    .println("Por favor, ejecute primero la gestión de usuarios para crear un usuario administrativo.");
            return;
        }

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Seleccione una opción: ");

            try {
                switch (choice) {
                    case 1 -> registerPatient(currentUser);
                    case 2 -> searchPatients();
                    case 3 -> updatePatient(currentUser);
                    case 4 -> updateInsurance(currentUser);
                    case 5 -> updateEmergencyContact(currentUser);
                    case 6 -> showPatientStatistics();
                    case 7 -> showInsuranceStatistics();
                    case 8 -> exportPatientData();
                    case 9 -> showSystemInfo();
                    case 0 -> {
                        System.out.println("👋 ¡Hasta luego!");
                        running = false;
                    }
                    default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }

            if (running) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
    }

    private void showMainMenu() {
        System.out.println("\n📋 MENÚ PRINCIPAL - GESTIÓN DE PACIENTES");
        System.out.println("==========================================");
        System.out.println("1. 📝 Registrar nuevo paciente");
        System.out.println("2. 🔍 Buscar pacientes");
        System.out.println("3. ✏️  Actualizar datos del paciente");
        System.out.println("4. 🏥 Actualizar información de seguro");
        System.out.println("5. 🚨 Actualizar contacto de emergencia");
        System.out.println("6. 📊 Ver estadísticas de pacientes");
        System.out.println("7. 📈 Ver estadísticas de seguros");
        System.out.println("8. 📤 Exportar datos de paciente");
        System.out.println("9. ℹ️  Información del sistema");
        System.out.println("0. 🚪 Salir");
        System.out.println("==========================================");
    }

    private void registerPatient(User currentUser) {
        System.out.println("\n📝 REGISTRAR NUEVO PACIENTE");
        System.out.println("============================");

        try {
            Patient patient = new Patient();

            patient.setDocumentNumber(getStringInput("Número de cédula: "));
            patient.setFullName(getStringInput("Nombre completo: "));
            patient.setBirthDate(getDateInput("Fecha de nacimiento (DD/MM/YYYY): "));
            patient.setGender(getGenderInput());
            patient.setAddress(getStringInput("Dirección: "));
            patient.setPhone(getStringInput("Teléfono (10 dígitos): "));
            patient.setEmail(getStringInput("Email (opcional): "));

            // Información de contacto de emergencia
            System.out.println("\n📞 INFORMACIÓN DE CONTACTO DE EMERGENCIA");
            patient.setEmergencyContactName(getStringInput("Nombre del contacto: "));
            patient.setEmergencyContactRelation(getStringInput("Relación con el paciente: "));
            patient.setEmergencyContactPhone(getStringInput("Teléfono del contacto: "));

            // Información de seguro
            System.out.println("\n🏥 INFORMACIÓN DE SEGURO MÉDICO");
            patient.setInsuranceCompany(getStringInput("Compañía de seguros: "));
            patient.setPolicyNumber(getStringInput("Número de póliza: "));
            patient.setPolicyActive(getBooleanInput("¿Póliza activa? (s/n): "));
            if (patient.isPolicyActive()) {
                patient.setPolicyExpirationDate(getDateInput("Fecha de vencimiento (DD/MM/YYYY): "));
            }

            Patient savedPatient = patientService.registerPatient(patient, currentUser);
            System.out.println("✅ Paciente registrado exitosamente:");
            System.out.println("   ID: " + savedPatient.getId());
            System.out.println("   Cédula: " + savedPatient.getDocumentNumber());
            System.out.println("   Nombre: " + savedPatient.getFullName());
            System.out.println("   Edad: " + savedPatient.getAge() + " años");

        } catch (Exception e) {
            System.out.println("❌ Error al registrar paciente: " + e.getMessage());
        }
    }

    private void searchPatients() {
        System.out.println("\n🔍 BUSCAR PACIENTES");
        System.out.println("===================");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por cédula");
        System.out.println("3. Ver todos los pacientes");
        System.out.println("4. Buscar por género");
        System.out.println("5. Buscar por rango de edad");
        System.out.println("6. Pacientes con seguro activo");
        System.out.println("7. Pacientes sin seguro");

        int choice = getIntInput("Seleccione una opción: ");

        try {
            List<Patient> patients = switch (choice) {
                case 1 -> {
                    String name = getStringInput("Ingrese el nombre a buscar: ");
                    yield patientService.searchPatientsByName(name);
                }
                case 2 -> {
                    String document = getStringInput("Ingrese la cédula: ");
                    yield patientService.findPatientByDocumentNumber(document)
                            .map(List::of).orElse(List.of());
                }
                case 3 -> patientService.getAllPatients();
                case 4 -> {
                    Patient.Gender gender = getGenderInput();
                    yield patientService.getPatientsByGender(gender);
                }
                case 5 -> {
                    int minAge = getIntInput("Edad mínima: ");
                    int maxAge = getIntInput("Edad máxima: ");
                    yield patientService.getPatientsByAgeRange(minAge, maxAge);
                }
                case 6 -> patientService.getPatientsWithActiveInsurance();
                case 7 -> patientService.getPatientsWithoutInsurance();
                default -> {
                    System.out.println("❌ Opción inválida");
                    yield List.of();
                }
            };

            if (patients.isEmpty()) {
                System.out.println("❌ No se encontraron pacientes");
            } else {
                System.out.println("\n📋 RESULTADOS DE BÚSQUEDA (" + patients.size() + " pacientes):");
                System.out.println("================================================");
                for (Patient patient : patients) {
                    displayPatientInfo(patient);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error en la búsqueda: " + e.getMessage());
        }
    }

    private void updatePatient(User currentUser) {
        System.out.println("\n✏️ ACTUALIZAR DATOS DEL PACIENTE");
        System.out.println("=================================");

        String documentNumber = getStringInput("Cédula del paciente a actualizar: ");

        try {
            Optional<Patient> patientOpt = patientService.findPatientByDocumentNumber(documentNumber);
            if (patientOpt.isEmpty()) {
                System.out.println("❌ Paciente no encontrado");
                return;
            }

            Patient patient = patientOpt.get();
            System.out.println("📋 Datos actuales del paciente:");
            displayPatientInfo(patient);

            System.out.println("\n✏️ Ingrese los nuevos datos (presione Enter para mantener el actual):");

            String newName = getStringInput("Nombre completo [" + patient.getFullName() + "]: ");
            if (!newName.trim().isEmpty()) {
                patient.setFullName(newName);
            }

            LocalDate newBirthDate = getDateInput("Fecha de nacimiento [" +
                    (patient.getBirthDate() != null
                            ? patient.getBirthDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                            : "N/A")
                    + "]: ");
            if (newBirthDate != null) {
                patient.setBirthDate(newBirthDate);
            }

            Patient.Gender newGender = getGenderInput("Género [" + patient.getGender().getDisplayName() + "]: ");
            if (newGender != null) {
                patient.setGender(newGender);
            }

            String newAddress = getStringInput("Dirección [" + patient.getAddress() + "]: ");
            if (!newAddress.trim().isEmpty()) {
                patient.setAddress(newAddress);
            }

            String newPhone = getStringInput("Teléfono [" + patient.getPhone() + "]: ");
            if (!newPhone.trim().isEmpty()) {
                patient.setPhone(newPhone);
            }

            String newEmail = getStringInput(
                    "Email [" + (patient.getEmail() != null ? patient.getEmail() : "N/A") + "]: ");
            if (!newEmail.trim().isEmpty()) {
                patient.setEmail(newEmail);
            }

            Patient updatedPatient = patientService.updatePatient(patient, currentUser);
            System.out.println("✅ Paciente actualizado exitosamente:");
            displayPatientInfo(updatedPatient);

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar paciente: " + e.getMessage());
        }
    }

    private void updateInsurance(User currentUser) {
        System.out.println("\n🏥 ACTUALIZAR INFORMACIÓN DE SEGURO");
        System.out.println("====================================");

        String documentNumber = getStringInput("Cédula del paciente: ");

        try {
            Optional<Patient> patientOpt = patientService.findPatientByDocumentNumber(documentNumber);
            if (patientOpt.isEmpty()) {
                System.out.println("❌ Paciente no encontrado");
                return;
            }

            Patient patient = patientOpt.get();
            System.out.println("📋 Información actual del seguro:");
            System.out.println(
                    "   Compañía: " + (patient.getInsuranceCompany() != null ? patient.getInsuranceCompany() : "N/A"));
            System.out.println("   Póliza: " + (patient.getPolicyNumber() != null ? patient.getPolicyNumber() : "N/A"));
            System.out.println("   Activa: " + (patient.isPolicyActive() ? "Sí" : "No"));

            String insuranceCompany = getStringInput("Nueva compañía de seguros: ");
            String policyNumber = getStringInput("Nuevo número de póliza: ");
            boolean isActive = getBooleanInput("¿Póliza activa? (s/n): ");
            LocalDate expirationDate = null;
            if (isActive) {
                expirationDate = getDateInput("Fecha de vencimiento (DD/MM/YYYY): ");
            }

            Patient updatedPatient = patientService.updateInsuranceInfo(
                    documentNumber, insuranceCompany, policyNumber, isActive, expirationDate, currentUser);

            System.out.println("✅ Información de seguro actualizada exitosamente");
            System.out.println("   Compañía: " + updatedPatient.getInsuranceCompany());
            System.out.println("   Póliza: " + updatedPatient.getPolicyNumber());
            System.out.println("   Activa: " + (updatedPatient.isPolicyActive() ? "Sí" : "No"));

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar seguro: " + e.getMessage());
        }
    }

    private void updateEmergencyContact(User currentUser) {
        System.out.println("\n🚨 ACTUALIZAR CONTACTO DE EMERGENCIA");
        System.out.println("=====================================");

        String documentNumber = getStringInput("Cédula del paciente: ");

        try {
            Optional<Patient> patientOpt = patientService.findPatientByDocumentNumber(documentNumber);
            if (patientOpt.isEmpty()) {
                System.out.println("❌ Paciente no encontrado");
                return;
            }

            Patient patient = patientOpt.get();
            System.out.println("📋 Contacto de emergencia actual:");
            System.out.println("   Nombre: "
                    + (patient.getEmergencyContactName() != null ? patient.getEmergencyContactName() : "N/A"));
            System.out.println("   Relación: "
                    + (patient.getEmergencyContactRelation() != null ? patient.getEmergencyContactRelation() : "N/A"));
            System.out.println("   Teléfono: "
                    + (patient.getEmergencyContactPhone() != null ? patient.getEmergencyContactPhone() : "N/A"));

            String contactName = getStringInput("Nombre del contacto: ");
            String relation = getStringInput("Relación con el paciente: ");
            String phone = getStringInput("Teléfono del contacto: ");

            Patient updatedPatient = patientService.updateEmergencyContact(
                    documentNumber, contactName, relation, phone, currentUser);

            System.out.println("✅ Contacto de emergencia actualizado exitosamente");
            System.out.println("   Nombre: " + updatedPatient.getEmergencyContactName());
            System.out.println("   Relación: " + updatedPatient.getEmergencyContactRelation());
            System.out.println("   Teléfono: " + updatedPatient.getEmergencyContactPhone());

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar contacto: " + e.getMessage());
        }
    }

    private void showPatientStatistics() {
        System.out.println("\n📊 ESTADÍSTICAS DE PACIENTES");
        System.out.println("=============================");

        try {
            Map<String, Object> stats = patientService.getPatientStatistics();

            System.out.println("📈 Resumen General:");
            System.out.println("   Total de pacientes: " + stats.get("totalPatients"));
            System.out.println("   Pacientes adultos: " + stats.get("adultPatients"));
            System.out.println("   Pacientes menores: " + stats.get("minorPatients"));
            System.out.println("   Con seguro médico: " + stats.get("patientsWithInsurance"));
            System.out.println("   Sin seguro médico: " + stats.get("patientsWithoutInsurance"));

        } catch (Exception e) {
            System.out.println("❌ Error al obtener estadísticas: " + e.getMessage());
        }
    }

    private void showInsuranceStatistics() {
        System.out.println("\n📈 ESTADÍSTICAS DE SEGUROS");
        System.out.println("===========================");

        try {
            Map<String, Object> stats = patientService.getInsuranceStatistics();

            System.out.println("🏥 Cobertura de Seguros:");
            System.out.println("   Total de pacientes: " + stats.get("totalPatients"));
            System.out.println("   Con seguro activo: " + stats.get("withActiveInsurance"));
            System.out.println("   Sin seguro: " + stats.get("withoutInsurance"));
            System.out.println(
                    "   Porcentaje de cobertura: " + String.format("%.1f%%", stats.get("insuranceCoveragePercentage")));

        } catch (Exception e) {
            System.out.println("❌ Error al obtener estadísticas: " + e.getMessage());
        }
    }

    private void exportPatientData() {
        System.out.println("\n📤 EXPORTAR DATOS DE PACIENTE");
        System.out.println("==============================");

        String documentNumber = getStringInput("Cédula del paciente a exportar: ");

        try {
            Map<String, Object> exportData = patientService.exportPatientData(documentNumber);

            System.out.println("📋 Datos exportados:");
            System.out.println("===================");
            for (Map.Entry<String, Object> entry : exportData.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }

        } catch (Exception e) {
            System.out.println("❌ Error al exportar datos: " + e.getMessage());
        }
    }

    private void showSystemInfo() {
        System.out.println("\nℹ️ INFORMACIÓN DEL SISTEMA");
        System.out.println("===========================");
        System.out.println("🏥 Sistema de Gestión de Clínica");
        System.out.println("📅 Versión: 1.0.0");
        System.out.println("👨‍💻 Desarrollado para: Estudiantes de 3er semestre");
        System.out.println("🏗️ Arquitectura: En capas");
        System.out.println("☕ Tecnologías: Java 17, Spring Boot, H2 Database");
        System.out.println("📊 Funcionalidades:");
        System.out.println("   • Gestión de usuarios");
        System.out.println("   • Gestión de pacientes");
        System.out.println("   • Gestión de seguros médicos");
        System.out.println("   • Estadísticas y reportes");
    }

    private void displayPatientInfo(Patient patient) {
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ID: " + String.format("%-50s", patient.getId()) + "│");
        System.out.println("│ Cédula: " + String.format("%-45s", patient.getDocumentNumber()) + "│");
        System.out.println("│ Nombre: " + String.format("%-44s", patient.getFullName()) + "│");
        System.out.println("│ Edad: " + String.format("%-46s", patient.getAge() + " años") + "│");
        System.out.println("│ Género: " + String.format("%-44s", patient.getGender().getDisplayName()) + "│");
        System.out.println("│ Teléfono: " + String.format("%-42s", patient.getPhone()) + "│");
        System.out.println(
                "│ Email: " + String.format("%-45s", patient.getEmail() != null ? patient.getEmail() : "N/A") + "│");
        System.out.println(
                "│ Seguro: " + String.format("%-44s", patient.hasActiveInsurance() ? "Activo" : "Inactivo") + "│");
        System.out.println("└─────────────────────────────────────────────────────────┘");
    }

    // Métodos auxiliares para entrada de datos
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido");
            }
        }
    }

    private boolean getBooleanInput(String prompt) {
        while (true) {
            String input = getStringInput(prompt).toLowerCase();
            if (input.equals("s") || input.equals("si") || input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            } else {
                System.out.println("❌ Por favor ingrese 's' para sí o 'n' para no");
            }
        }
    }

    private LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                String input = getStringInput(prompt);
                if (input.trim().isEmpty()) {
                    return null;
                }
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("❌ Formato de fecha inválido. Use DD/MM/YYYY");
            }
        }
    }

    private Patient.Gender getGenderInput() {
        return getGenderInput(null);
    }

    private Patient.Gender getGenderInput(String currentValue) {
        while (true) {
            System.out.println("Género:");
            System.out.println("1. Masculino");
            System.out.println("2. Femenino");
            System.out.println("3. Otro");
            if (currentValue != null) {
                System.out.println("Actual: " + currentValue);
            }

            int choice = getIntInput("Seleccione una opción: ");
            return switch (choice) {
                case 1 -> Patient.Gender.MASCULINO;
                case 2 -> Patient.Gender.FEMENINO;
                case 3 -> Patient.Gender.OTRO;
                default -> {
                    System.out.println("❌ Opción inválida");
                    yield null;
                }
            };
        }
    }

    private User getCurrentUser() {
        // Buscar un usuario administrativo existente
        try {
            List<User> allUsers = userService.getAllUsers();
            return allUsers.stream()
                    .filter(user -> User.Role.PERSONAL_ADMINISTRATIVO.equals(user.getRole()))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
