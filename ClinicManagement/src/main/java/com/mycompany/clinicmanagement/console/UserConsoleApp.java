package com.mycompany.clinicmanagement.console;

import com.mycompany.clinicmanagement.adapter.in.factory.UserFactory;
import com.mycompany.clinicmanagement.application.port.UserServicePort;
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
 * Aplicación de consola para gestión de usuarios
 * Interfaz de usuario para Recursos Humanos
 */
@Component
public class UserConsoleApp {

    private final UserServicePort userService;
    private final UserFactory userFactory;
    private final Scanner scanner;

    public UserConsoleApp(UserServicePort userService, UserFactory userFactory) {
        this.userService = userService;
        this.userFactory = userFactory;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Ejecuta la aplicación de consola para usuarios
     */
    public void run() {
        System.out.println("👥 Sistema de Gestión de Usuarios");
        System.out.println("=================================");

        // Crear usuario administrador por defecto si no existe
        createDefaultAdmin();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Seleccione una opción: ");

            try {
                switch (choice) {
                    case 1 -> createUser();
                    case 2 -> createUserWithBuilder();
                    case 3 -> createUserWithFactory();
                    case 4 -> updateUser();
                    case 5 -> deleteUser();
                    case 6 -> searchUsers();
                    case 7 -> showUserStatistics();
                    case 8 -> createSampleUsers();
                    case 9 -> showAllUsers();
                    case 10 -> showSystemInfo();
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
        System.out.println("\n📋 MENÚ PRINCIPAL - GESTIÓN DE USUARIOS");
        System.out.println("=========================================");
        System.out.println("1. 📝 Crear usuario (método tradicional)");
        System.out.println("2. 🔨 Crear usuario con Builder");
        System.out.println("3. 🏭 Crear usuario con Factory");
        System.out.println("4. ✏️  Actualizar usuario");
        System.out.println("5. 🗑️  Eliminar usuario");
        System.out.println("6. 🔍 Buscar usuarios");
        System.out.println("7. 📊 Ver estadísticas");
        System.out.println("8. 🎭 Crear usuarios de ejemplo");
        System.out.println("9. 👥 Ver todos los usuarios");
        System.out.println("10. ℹ️  Información del sistema");
        System.out.println("0. 🚪 Salir");
        System.out.println("=========================================");
    }

    private void createUser() {
        System.out.println("\n📝 CREAR USUARIO (Método Tradicional)");
        System.out.println("=====================================");

        try {
            User user = new User();
            user.setDocumentNumber(getStringInput("Número de cédula: "));
            user.setFullName(getStringInput("Nombre completo: "));
            user.setEmail(getStringInput("Email: "));
            user.setPhone(getStringInput("Teléfono: "));
            user.setBirthDate(getDateInput("Fecha de nacimiento (DD/MM/YYYY): "));
            user.setAddress(getStringInput("Dirección: "));
            user.setRole(getRoleInput());
            user.setUsername(getStringInput("Nombre de usuario: "));
            user.setPassword(getStringInput("Contraseña: "));
            user.setActive(true);

            User currentUser = getCurrentUser();
            User createdUser = userService.createUser(user, currentUser);

            System.out.println("✅ Usuario creado exitosamente:");
            displayUserInfo(createdUser);

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuario: " + e.getMessage());
        }
    }

    private void createUserWithBuilder() {
        System.out.println("\n🔨 CREAR USUARIO CON BUILDER");
        System.out.println("=============================");

        try {
            User user = new User();
            user.setDocumentNumber(getStringInput("Número de cédula: "));
            user.setFullName(getStringInput("Nombre completo: "));
            user.setEmail(getStringInput("Email: "));
            user.setPhone(getStringInput("Teléfono: "));
            user.setBirthDate(getDateInput("Fecha de nacimiento (DD/MM/YYYY): "));
            user.setAddress(getStringInput("Dirección: "));
            user.setRole(getRoleInput());
            user.setUsername(getStringInput("Nombre de usuario: "));
            user.setPassword(getStringInput("Contraseña: "));
            user.setActive(true);

            User currentUser = getCurrentUser();
            User createdUser = userService.createUser(user, currentUser);

            System.out.println("✅ Usuario creado con Builder exitosamente:");
            displayUserInfo(createdUser);

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuario: " + e.getMessage());
        }
    }

    private void createUserWithFactory() {
        System.out.println("\n🏭 CREAR USUARIO CON FACTORY");
        System.out.println("=============================");

        System.out.println("Tipos de usuario disponibles:");
        System.out.println("1. Recursos Humanos");
        System.out.println("2. Personal Administrativo");
        System.out.println("3. Médico");
        System.out.println("4. Enfermera");
        System.out.println("5. Soporte de Información");

        int roleChoice = getIntInput("Seleccione el tipo de usuario: ");

        try {
            User user = switch (roleChoice) {
                case 1 -> userFactory.createHRUser(
                        getStringInput("Número de cédula: "),
                        getStringInput("Nombre completo: "),
                        getStringInput("Email: "));
                case 2 -> userFactory.createAdminUser(
                        getStringInput("Número de cédula: "),
                        getStringInput("Nombre completo: "),
                        getStringInput("Email: "));
                case 3 -> userFactory.createDoctorUser(
                        getStringInput("Número de cédula: "),
                        getStringInput("Nombre completo: "),
                        getStringInput("Email: "));
                case 4 -> userFactory.createNurseUser(
                        getStringInput("Número de cédula: "),
                        getStringInput("Nombre completo: "),
                        getStringInput("Email: "));
                case 5 -> userFactory.createSupportUser(
                        getStringInput("Número de cédula: "),
                        getStringInput("Nombre completo: "),
                        getStringInput("Email: "));
                default -> throw new IllegalArgumentException("Opción inválida");
            };

            User currentUser = getCurrentUser();
            User createdUser = userService.createUser(user, currentUser);

            System.out.println("✅ Usuario creado con Factory exitosamente:");
            displayUserInfo(createdUser);

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuario: " + e.getMessage());
        }
    }

    private void updateUser() {
        System.out.println("\n✏️ ACTUALIZAR USUARIO");
        System.out.println("======================");

        String documentNumber = getStringInput("Cédula del usuario a actualizar: ");

        try {
            Optional<User> userOpt = userService.findUserByDocumentNumber(documentNumber);
            if (userOpt.isEmpty()) {
                System.out.println("❌ Usuario no encontrado");
                return;
            }

            User user = userOpt.get();
            System.out.println("📋 Datos actuales del usuario:");
            displayUserInfo(user);

            System.out.println("\n✏️ Ingrese los nuevos datos (presione Enter para mantener el actual):");

            String newName = getStringInput("Nombre completo [" + user.getFullName() + "]: ");
            if (!newName.trim().isEmpty()) {
                user.setFullName(newName);
            }

            String newEmail = getStringInput("Email [" + user.getEmail() + "]: ");
            if (!newEmail.trim().isEmpty()) {
                user.setEmail(newEmail);
            }

            String newPhone = getStringInput("Teléfono [" + user.getPhone() + "]: ");
            if (!newPhone.trim().isEmpty()) {
                user.setPhone(newPhone);
            }

            String newAddress = getStringInput("Dirección [" + user.getAddress() + "]: ");
            if (!newAddress.trim().isEmpty()) {
                user.setAddress(newAddress);
            }

            User currentUser = getCurrentUser();
            User updatedUser = userService.updateUser(user, currentUser);

            System.out.println("✅ Usuario actualizado exitosamente:");
            displayUserInfo(updatedUser);

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar usuario: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.println("\n🗑️ ELIMINAR USUARIO");
        System.out.println("===================");

        String documentNumber = getStringInput("Cédula del usuario a eliminar: ");

        try {
            Optional<User> userOpt = userService.findUserByDocumentNumber(documentNumber);
            if (userOpt.isEmpty()) {
                System.out.println("❌ Usuario no encontrado");
                return;
            }

            User user = userOpt.get();
            System.out.println("📋 Usuario a eliminar:");
            displayUserInfo(user);

            String confirm = getStringInput("¿Está seguro de eliminar este usuario? (s/n): ");
            if (confirm.toLowerCase().equals("s") || confirm.toLowerCase().equals("si")) {
                User currentUser = getCurrentUser();
                userService.deleteUser(user.getId(), currentUser);
                System.out.println("✅ Usuario eliminado exitosamente");
            } else {
                System.out.println("❌ Operación cancelada");
            }

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar usuario: " + e.getMessage());
        }
    }

    private void searchUsers() {
        System.out.println("\n🔍 BUSCAR USUARIOS");
        System.out.println("==================");
        System.out.println("1. Buscar por nombre");
        System.out.println("2. Buscar por cédula");
        System.out.println("3. Buscar por rol");
        System.out.println("4. Ver todos los usuarios");
        System.out.println("5. Usuarios activos");
        System.out.println("6. Usuarios inactivos");

        int choice = getIntInput("Seleccione una opción: ");

        try {
            List<User> users = switch (choice) {
                case 1 -> {
                    String name = getStringInput("Ingrese el nombre a buscar: ");
                    yield userService.getAllUsers().stream()
                            .filter(user -> user.getFullName().toLowerCase().contains(name.toLowerCase()))
                            .toList();
                }
                case 2 -> {
                    String document = getStringInput("Ingrese la cédula: ");
                    yield userService.findUserByDocumentNumber(document)
                            .map(List::of).orElse(List.of());
                }
                case 3 -> {
                    User.Role role = getRoleInput();
                    yield userService.getUsersByRole(role);
                }
                case 4 -> userService.getAllUsers();
                case 5 -> userService.getActiveUsers();
                case 6 -> {
                    yield userService.getAllUsers().stream()
                            .filter(user -> !user.isActive())
                            .toList();
                }
                default -> {
                    System.out.println("❌ Opción inválida");
                    yield List.of();
                }
            };

            if (users.isEmpty()) {
                System.out.println("❌ No se encontraron usuarios");
            } else {
                System.out.println("\n📋 RESULTADOS DE BÚSQUEDA (" + users.size() + " usuarios):");
                System.out.println("================================================");
                for (User user : users) {
                    displayUserInfo(user);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error en la búsqueda: " + e.getMessage());
        }
    }

    private void showUserStatistics() {
        System.out.println("\n📊 ESTADÍSTICAS DE USUARIOS");
        System.out.println("============================");

        try {
            Map<String, Object> stats = userService.getUserStatistics();

            System.out.println("📈 Resumen General:");
            System.out.println("   Total de usuarios: " + stats.get("totalUsers"));
            System.out.println("   Usuarios activos: " + stats.get("activeUsers"));
            System.out.println("   Usuarios inactivos: " + stats.get("inactiveUsers"));

            System.out.println("\n👥 Por Rol:");
            @SuppressWarnings("unchecked")
            Map<String, Object> roleStats = (Map<String, Object>) stats.get("usersByRole");
            if (roleStats != null) {
                for (Map.Entry<String, Object> entry : roleStats.entrySet()) {
                    System.out.println("   " + entry.getKey() + ": " + entry.getValue());
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener estadísticas: " + e.getMessage());
        }
    }

    private void createSampleUsers() {
        System.out.println("\n🎭 CREAR USUARIOS DE EJEMPLO");
        System.out.println("=============================");

        try {
            User currentUser = getCurrentUser();
            List<User> sampleUsers = userFactory.createSampleUsers();

            int created = 0;
            for (User user : sampleUsers) {
                try {
                    userService.createUser(user, currentUser);
                    created++;
                } catch (Exception e) {
                    System.out.println("⚠️ Error al crear usuario " + user.getFullName() + ": " + e.getMessage());
                }
            }

            System.out.println("✅ Se crearon " + created + " usuarios de ejemplo");

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuarios de ejemplo: " + e.getMessage());
        }
    }

    private void showAllUsers() {
        System.out.println("\n👥 TODOS LOS USUARIOS");
        System.out.println("=====================");

        try {
            List<User> users = userService.getAllUsers();

            if (users.isEmpty()) {
                System.out.println("❌ No hay usuarios registrados");
            } else {
                System.out.println("📋 Lista de usuarios (" + users.size() + " total):");
                System.out.println("================================================");
                for (User user : users) {
                    displayUserInfo(user);
                }
            }

        } catch (Exception e) {
            System.out.println("❌ Error al obtener usuarios: " + e.getMessage());
        }
    }

    private void showSystemInfo() {
        System.out.println("\nℹ️ INFORMACIÓN DEL SISTEMA");
        System.out.println("==========================");
        System.out.println("🏥 Sistema de Gestión de Clínica");
        System.out.println("📅 Versión: 1.0.0");
        System.out.println("👨‍💻 Desarrollado para: Estudiantes de 3er semestre");
        System.out.println("🏗️ Arquitectura: En capas");
        System.out.println("☕ Tecnologías: Java 17, Spring Boot, H2 Database");
        System.out.println("👥 Roles disponibles:");
        System.out.println("   • Recursos Humanos");
        System.out.println("   • Personal Administrativo");
        System.out.println("   • Médico");
        System.out.println("   • Enfermera");
        System.out.println("   • Soporte de Información");
        System.out.println("📊 Funcionalidades:");
        System.out.println("   • Gestión de usuarios");
        System.out.println("   • Gestión de pacientes");
        System.out.println("   • Estadísticas y reportes");
    }

    private void displayUserInfo(User user) {
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│ ID: " + String.format("%-50s", user.getId()) + "│");
        System.out.println("│ Cédula: " + String.format("%-45s", user.getDocumentNumber()) + "│");
        System.out.println("│ Nombre: " + String.format("%-44s", user.getFullName()) + "│");
        System.out.println("│ Email: " + String.format("%-45s", user.getEmail()) + "│");
        System.out.println("│ Teléfono: " + String.format("%-42s", user.getPhone()) + "│");
        System.out.println("│ Rol: " + String.format("%-47s", user.getRole().getDisplayName()) + "│");
        System.out.println("│ Username: " + String.format("%-42s", user.getUsername()) + "│");
        System.out.println("│ Estado: " + String.format("%-44s", user.isActive() ? "Activo" : "Inactivo") + "│");
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

    private User.Role getRoleInput() {
        while (true) {
            System.out.println("Roles disponibles:");
            System.out.println("1. Recursos Humanos");
            System.out.println("2. Personal Administrativo");
            System.out.println("3. Médico");
            System.out.println("4. Enfermera");
            System.out.println("5. Soporte de Información");

            int choice = getIntInput("Seleccione una opción: ");
            return switch (choice) {
                case 1 -> User.Role.RECURSOS_HUMANOS;
                case 2 -> User.Role.PERSONAL_ADMINISTRATIVO;
                case 3 -> User.Role.MEDICO;
                case 4 -> User.Role.ENFERMERA;
                case 5 -> User.Role.SOPORTE_INFORMACION;
                default -> {
                    System.out.println("❌ Opción inválida");
                    yield null;
                }
            };
        }
    }

    private User getCurrentUser() {
        // Buscar un usuario de RRHH existente
        try {
            List<User> allUsers = userService.getAllUsers();
            return allUsers.stream()
                    .filter(user -> User.Role.RECURSOS_HUMANOS.equals(user.getRole()))
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    private void createDefaultAdmin() {
        try {
            // Verificar si ya existe un usuario administrativo
            List<User> existingUsers = userService.getAllUsers();
            if (!existingUsers.isEmpty()) {
                return; // Ya hay usuarios, no crear por defecto
            }

            // Crear usuario administrador por defecto
            User admin = userFactory.createHRUser("00000000", "Administrador", "admin@clinic.com");
            admin.setUsername("admin");
            admin.setPassword("Admin123!");
            admin.setActive(true);

            // Crear un usuario temporal para poder crear el admin
            User tempUser = new User();
            tempUser.setDocumentNumber("99999999");
            tempUser.setFullName("Sistema");
            tempUser.setRole(User.Role.RECURSOS_HUMANOS);
            tempUser.setActive(true);

            userService.createUser(admin, tempUser);
            System.out.println("✅ Usuario administrador creado por defecto");
            System.out.println("   Username: admin");
            System.out.println("   Password: Admin123!");

        } catch (Exception e) {
            System.out.println("⚠️ No se pudo crear el usuario administrador por defecto: " + e.getMessage());
        }
    }
}