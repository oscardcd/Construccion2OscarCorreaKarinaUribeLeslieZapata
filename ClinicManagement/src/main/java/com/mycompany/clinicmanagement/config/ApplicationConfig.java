package com.mycompany.clinicmanagement.config;

import com.mycompany.clinicmanagement.application.port.UserRepositoryPort;
import com.mycompany.clinicmanagement.application.port.PatientRepositoryPort;
import com.mycompany.clinicmanagement.application.port.MedicalRecordRepositoryPort;
import com.mycompany.clinicmanagement.application.port.OrderRepositoryPort;
import com.mycompany.clinicmanagement.application.port.VitalSignsRepositoryPort;
import com.mycompany.clinicmanagement.application.port.InvoiceRepositoryPort;
import com.mycompany.clinicmanagement.adapter.out.persistence.UserRepositoryAdapter;
import com.mycompany.clinicmanagement.adapter.out.persistence.PatientRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración principal de la aplicación
 * Define los beans y la inyección de dependencias
 */
@Configuration
public class ApplicationConfig {

    /**
     * Configuración de repositorios
     * En producción se usarían implementaciones reales con JPA/MongoDB
     */

    @Bean
    public UserRepositoryPort userRepository() {
        return new UserRepositoryAdapter();
    }

    @Bean
    public PatientRepositoryPort patientRepository() {
        return new PatientRepositoryAdapter();
    }

}
