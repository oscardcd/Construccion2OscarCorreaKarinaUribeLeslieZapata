package clinickol.clinicmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClinicManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicManagementApplication.class, args);
        System.out.println("\n╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   CLINIC MANAGEMENT SYSTEM - API REST                ║");
        System.out.println("║   Server running on: http://localhost:8080           ║");
        System.out.println("║   H2 Console: http://localhost:8080/h2-console       ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝\n");
    }
}
