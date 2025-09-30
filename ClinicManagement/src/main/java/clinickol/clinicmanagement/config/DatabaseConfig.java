package clinickol.clinicmanagement.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "clinickol.clinicmanagement.repository")
public class DatabaseConfig {
}
