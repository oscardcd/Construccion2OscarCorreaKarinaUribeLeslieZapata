package com.mycompany.clinicmanagement;

import com.mycompany.clinicmanagement.console.PatientConsoleApp;
import com.mycompany.clinicmanagement.console.UserConsoleApp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Aplicación principal de gestión de clínica
 * Implementa arquitectura en capas con Spring Boot
 * Configurada como aplicación de consola
 */
@SpringBootApplication
public class ClinicManagement {

    public static void main(String[] args) {
        // Verificar si se debe ejecutar como consola o web
        boolean isConsoleMode = args.length > 0 && "console".equals(args[0]);

        if (isConsoleMode) {
            // Configurar para aplicación de consola
            System.setProperty("spring.main.web-application-type", "none");
        }

        SpringApplication.run(ClinicManagement.class, args);
    }

    @Bean
    public CommandLineRunner run(UserConsoleApp userConsoleApp, PatientConsoleApp patientConsoleApp) {
        return args -> {
            if (args.length > 0 && "console".equals(args[0])) {
                showMainMenu(userConsoleApp, patientConsoleApp);
            }
        };
    }

    private void showMainMenu(UserConsoleApp userConsoleApp, PatientConsoleApp patientConsoleApp) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        while (true) {
            System.out.println("\n🏥 SISTEMA DE GESTIÓN DE CLÍNICA");
            System.out.println("=================================");
            System.out.println("1. 👥 Gestión de Usuarios");
            System.out.println("2. 🏥 Gestión de Pacientes");
            System.out.println("0. 🚪 Salir");
            System.out.println("=================================");

            System.out.print("Seleccione una opción: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1 -> userConsoleApp.run();
                case 2 -> patientConsoleApp.run();
                case 0 -> {
                    System.out.println("👋 ¡Hasta luego!");
                    System.exit(0);
                }
                default -> System.out.println("❌ Opción inválida. Intente nuevamente.");
            }
        }
    }
}
