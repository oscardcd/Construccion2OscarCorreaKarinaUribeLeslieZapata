package com.mycompany.clinicmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuración de seguridad para la aplicación
 * Proporciona beans de seguridad necesarios
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean para encriptación de contraseñas
     * Usa BCrypt con factor de trabajo 12
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}