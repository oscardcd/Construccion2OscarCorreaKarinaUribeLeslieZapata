package com.mycompany.clinicmanagement.console;

import com.mycompany.clinicmanagement.adapter.in.builder.UserBuilder;
import com.mycompany.clinicmanagement.adapter.in.factory.UserFactory;
import com.mycompany.clinicmanagement.application.usecases.rh.CreateUserUseCase;
import com.mycompany.clinicmanagement.application.usecases.rh.DeleteUserUseCase;
import com.mycompany.clinicmanagement.application.usecases.rh.GetUserStatisticsUseCase;
import com.mycompany.clinicmanagement.application.usecases.rh.UpdateUserUseCase;
import com.mycompany.clinicmanagement.domain.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación de consola para gestión de usuarios
 * Proporciona un menú interactivo para manejar usuarios localmente
 */
@Component
public class UserConsoleApp implements CommandLineRunner {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserStatisticsUseCase getUserStatisticsUseCase;
    private final UserBuilder userBuilder;
    private final UserFactory userFactory;
    private final Scanner scanner;

    public UserConsoleApp(CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserStatisticsUseCase getUserStatisticsUseCase,
            UserBuilder userBuilder,
            UserFactory userFactory) {
        this.createUserUseCase = createUserUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
        this.getUserStatisticsUseCase = getUserStatisticsUseCase;
        this.userBuilder = userBuilder;
        this.userFactory = userFactory;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("🏥 Sistema de Gestión de Clínica - Gestión de Usuarios");
        System.out.println("=====================================================");

        // Crear usuario administrador por defecto
        createDefaultAdmin();

        boolean running = true;
        while (running) {
            showMainMenu();
            int choice = getIntInput("Seleccione una opción: ");

            switch (choice) {
                case 1 -> createUser();
                case 2 -> createUserWithBuilder();
                case 3 -> createUserWithFactory();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 6 -> showUserStatistics();
                case 7 -> createSampleUsers();
                case 8 -> showSystemInfo();
                case 9 -> showAllUsers();
                case 0 -> {
                    System.out.println("👋 ¡Hasta luego!");
                    running = false;
                }
                default -> System.out.println("❌ Opción inválida. Intente de nuevo.");
            }

            if (running) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();
    }

    private void showMainMenu() {
        System.out.println("\n📋 MENÚ PRINCIPAL");
        System.out.println("==================");
        System.out.println("1. Crear usuario (método tradicional)");
        System.out.println("2. Crear usuario con Builder");
        System.out.println("3. Crear usuario con Factory");
        System.out.println("4. Actualizar usuario");
        System.out.println("5. Eliminar usuario");
        System.out.println("6. Ver estadísticas");
        System.out.println("7. Crear usuarios de ejemplo");
        System.out.println("8. Información del sistema");
        System.out.println("9. Ver todos los usuarios");
        System.out.println("0. Salir");
        System.out.println("==================");
    }

    private void createUser() {
        System.out.println("\n👤 CREAR USUARIO (Método Tradicional)");
        System.out.println("=====================================");

        try {
            User user = new User();
            user.setDocumentNumber(getStringInput("Número de cédula: "));
            user.setFullName(getStringInput("Nombre completo: "));
            user.setEmail(getStringInput("Email: "));
            user.setPhone(getStringInput("Teléfono: "));
            user.setBirthDate(getDateInput("Fecha de nacimiento (yyyy-MM-dd): "));
            user.setAddress(getStringInput("Dirección: "));
            user.setRole(getRoleInput("Rol (HR/ADMIN/DOCTOR/NURSE/SUPPORT): "));
            user.setUsername(getStringInput("Nombre de usuario: "));
            user.setPassword(getStringInput("Contraseña: "));
            user.setActive(true);
            user.setCreatedAt(java.time.LocalDateTime.now());
            user.setUpdatedAt(java.time.LocalDateTime.now());

            User currentUser = getCurrentUser();
            User createdUser = createUserUseCase.execute(user, currentUser);

            System.out.println("✅ Usuario creado exitosamente:");
            System.out.println("   ID: " + createdUser.getId());
            System.out.println("   Nombre: " + createdUser.getFullName());
            System.out.println("   Rol: " + createdUser.getRole().getDisplayName());

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuario: " + e.getMessage());
        }
    }

    private void createUserWithBuilder() {
        System.out.println("\n🏗️ CREAR USUARIO CON BUILDER");
        System.out.println("=============================");

        try {
            User user = userBuilder
                    .withDocumentNumber(getStringInput("Número de cédula: "))
                    .withFullName(getStringInput("Nombre completo: "))
                    .withEmail(getStringInput("Email: "))
                    .withPhone(getStringInput("Teléfono: "))
                    .withBirthDate(getDateInput("Fecha de nacimiento (yyyy-MM-dd): "))
                    .withAddress(getStringInput("Dirección: "))
                    .withRole(getRoleInput("Rol (HR/ADMIN/DOCTOR/NURSE/SUPPORT): "))
                    .withUsername(getStringInput("Nombre de usuario: "))
                    .withPassword(getStringInput("Contraseña: "))
                    .build();

            User currentUser = getCurrentUser();
            User createdUser = createUserUseCase.execute(user, currentUser);

            System.out.println("✅ Usuario creado con Builder exitosamente:");
            System.out.println("   ID: " + createdUser.getId());
            System.out.println("   Nombre: " + createdUser.getFullName());
            System.out.println("   Rol: " + createdUser.getRole().getDisplayName());

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
            User createdUser = createUserUseCase.execute(user, currentUser);

            System.out.println("✅ Usuario creado con Factory exitosamente:");
            System.out.println("   ID: " + createdUser.getId());
            System.out.println("   Nombre: " + createdUser.getFullName());
            System.out.println("   Rol: " + createdUser.getRole().getDisplayName());

        } catch (Exception e) {
            System.out.println("❌ Error al crear usuario: " + e.getMessage());
        }
    }

    private void updateUser() {
        System.out.println("\n✏️ ACTUALIZAR USUARIO");
        System.out.println("=====================");

        Long userId = getLongInput("ID del usuario a actualizar: ");

        try {
            // Crear usuario con datos actualizados
            User updatedUser = userBuilder
                    .withId(userId)
                    .withDocumentNumber(getStringInput("Número de cédula: "))
                    .withFullName(getStringInput("Nombre completo: "))
                    .withEmail(getStringInput("Email: "))
                    .withPhone(getStringInput("Teléfono: "))
                    .withBirthDate(getDateInput("Fecha de nacimiento (yyyy-MM-dd): "))
                    .withAddress(getStringInput("Dirección: "))
                    .withRole(getRoleInput("Rol (HR/ADMIN/DOCTOR/NURSE/SUPPORT): "))
                    .withUsername(getStringInput("Nombre de usuario: "))
                    .withPassword(getStringInput("Contraseña: "))
                    .withActiveStatus(getBooleanInput("¿Usuario activo? (true/false): "))
                    .buildWithoutValidation();

            User currentUser = getCurrentUser();
            User result = updateUserUseCase.execute(updatedUser, currentUser);

            System.out.println("✅ Usuario actualizado exitosamente:");
            System.out.println("   ID: " + result.getId());
            System.out.println("   Nombre: " + result.getFullName());
            System.out.println("   Rol: " + result.getRole().getDisplayName());

        } catch (Exception e) {
            System.out.println("❌ Error al actualizar usuario: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.println("\n🗑️ ELIMINAR USUARIO");
        System.out.println("===================");

        Long userId = getLongInput("ID del usuario a eliminar: ");

        try {
            User currentUser = getCurrentUser();
            deleteUserUseCase.execute(userId, currentUser);

            System.out.println("✅ Usuario eliminado exitosamente");

        } catch (Exception e) {
            System.out.println("❌ Error al eliminar usuario: " + e.getMessage());
        }
    }

    private void showUserStatistics() {
        System.out.println("\n📊 ESTADÍSTICAS DE USUARIOS");
        System.out.println("============================");

        try {
            User currentUser = getCurrentUser();
            var statistics = getUserStatisticsUseCase.execute(currentUser);

            System.out.println("📈 Estadísticas del sistema:");
            statistics.forEach((key, value) -> System.out.println("   " + key + ": " + value));

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
                    createUserUseCase.execute(user, currentUser);
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

    private void showSystemInfo() {
        System.out.println("\nℹ️ INFORMACIÓN DEL SISTEMA");
        System.out.println("==========================");
        System.out.println("🏥 Sistema de Gestión de Clínica");
        System.out.println("📋 Arquitectura: Hexagonal (Ports and Adapters)");
        System.out.println("☕ Tecnología: Java 17 + Spring Boot 3.2.0");
        System.out.println("🗄️ Base de datos: H2 (desarrollo)");
        System.out.println("🔧 Modo: Consola interactiva");
        System.out.println("👥 Roles disponibles: RRHH, Admin, Médico, Enfermera, Soporte");
        System.out.println("📊 Funcionalidades: Gestión de usuarios, validaciones, estadísticas");
    }

    private void showAllUsers() {
        System.out.println("\n👥 TODOS LOS USUARIOS");
        System.out.println("=====================");

        // TODO: Implementar método para obtener todos los usuarios
        System.out.println("⚠️ Funcionalidad en desarrollo...");
    }

    private void createDefaultAdmin() {
        try {
            User admin = userFactory.createHRUser("00000000", "Administrador", "admin@clinic.com");
            admin.setUsername("admin");
            admin.setPassword("Admin123!");

            // Crear usuario administrador por defecto si no existe
            System.out.println("🔧 Configurando usuario administrador por defecto...");

        } catch (Exception e) {
            System.out.println("⚠️ No se pudo crear el usuario administrador: " + e.getMessage());
        }
    }

    private User getCurrentUser() {
        // Usuario administrador por defecto
        User user = new User();
        user.setId(1L);
        user.setRole(User.Role.RECURSOS_HUMANOS);
        user.setActive(true);
        return user;
    }

    // Métodos auxiliares para entrada de datos
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido.");
            }
        }
    }

    private Long getLongInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Por favor ingrese un número válido.");
            }
        }
    }

    private LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.nextLine();
                return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (Exception e) {
                System.out.println("❌ Formato de fecha inválido. Use yyyy-MM-dd");
            }
        }
    }

    private User.Role getRoleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String roleStr = scanner.nextLine().toUpperCase();
                return User.Role.valueOf(roleStr);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Rol inválido. Use: HR, ADMIN, DOCTOR, NURSE, SUPPORT");
            }
        }
    }

    private boolean getBooleanInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("true") || input.equals("t") || input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("false") || input.equals("f") || input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println("❌ Por favor ingrese true/false, t/f, yes/no, o y/n");
            }
        }
    }
}
