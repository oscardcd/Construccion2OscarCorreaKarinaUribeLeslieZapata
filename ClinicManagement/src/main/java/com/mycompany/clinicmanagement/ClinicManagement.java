package com.mycompany.clinicmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

/**
 * Aplicación principal de gestión de clínica
 * Implementa arquitectura hexagonal con Spring Boot
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
}
