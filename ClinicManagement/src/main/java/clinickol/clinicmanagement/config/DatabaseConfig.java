package clinickol.clinicmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Configuración de la base de datos
 */
@Configuration
@EnableJpaRepositories(basePackages = "clinickol.clinicmanagement.repository")
public class DatabaseConfig {
    // Configuración adicional de base de datos si es necesaria
}
