package clinickol.clinicmanagement.adapters.in.console;

import clinickol.clinicmanagement.application.ports.in.PatientInputPort;
import clinickol.clinicmanagement.domain.model.PatientDomain;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class PatientConsoleAdapter implements CommandLineRunner {

    private final PatientInputPort patientInputPort;
    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public PatientConsoleAdapter(PatientInputPort patientInputPort) {
        this.patientInputPort = patientInputPort;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("╔═══════════════════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE CLÍNICA - ARQUITECTURA HEX   ║");
        System.out.println("║          Manejo de Pacientes con Clean Architecture  ║");
        System.out.println("╚═══════════════════════════════════════════════════════╝");
        System.out.println();

        while (running) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            procesarOpcion(opcion);
        }

        System.out.println("\n¡Gracias por usar el sistema!");
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n╔════════════════════ MENÚ PRINCIPAL ════════════════════╗");
        System.out.println("║ 1. Gestionar Pacientes                                 ║");
        System.out.println("║ 2. Ver Estadísticas                                    ║");
        System.out.println("║ 3. Crear Datos de Ejemplo                              ║");
        System.out.println("║ 4. Salir                                               ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.print("➤ Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> gestionarPacientes();
            case 2 -> mostrarEstadisticas();
            case 3 -> crearDatosEjemplo();
            case 4 -> running = false;
            default -> System.out.println("❌ Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

    private void gestionarPacientes() {
        boolean enSubmenu = true;

        while (enSubmenu) {
            System.out.println("\n╔══════════════ GESTIÓN DE PACIENTES ═══════════════╗");
            System.out.println("║  1. Crear Paciente                                ║");
            System.out.println("║  2. Listar Todos los Pacientes                    ║");
            System.out.println("║  3. Listar Pacientes Activos                      ║");
            System.out.println("║  4. Buscar Paciente por ID                        ║");
            System.out.println("║  5. Buscar Paciente por Email                     ║");
            System.out.println("║  6. Buscar Pacientes por Nombre                   ║");
            System.out.println("║  7. Buscar Pacientes por Género                   ║");
            System.out.println("║  8. Actualizar Paciente                           ║");
            System.out.println("║  9. Desactivar Paciente                           ║");
            System.out.println("║ 10. Eliminar Paciente                             ║");
            System.out.println("║ 11. Volver al Menú Principal                      ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("➤ Seleccione una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1 -> crearPaciente();
                case 2 -> listarTodosLosPacientes();
                case 3 -> listarPacientesActivos();
                case 4 -> buscarPacientePorId();
                case 5 -> buscarPacientePorEmail();
                case 6 -> buscarPacientesPorNombre();
                case 7 -> buscarPacientesPorGenero();
                case 8 -> actualizarPaciente();
                case 9 -> desactivarPaciente();
                case 10 -> eliminarPaciente();
                case 11 -> enSubmenu = false;
                default -> System.out.println("❌ Opción inválida.");
            }
        }
    }

    private void crearPaciente() {
        System.out.println("\n╔═══════════ CREAR NUEVO PACIENTE ════════════╗");

        try {
            PatientDomain patient = new PatientDomain();

            System.out.print("Nombre: ");
            patient.setNombre(scanner.nextLine().trim());

            System.out.print("Apellido: ");
            patient.setApellido(scanner.nextLine().trim());

            System.out.print("Email: ");
            patient.setEmail(scanner.nextLine().trim());

            System.out.print("Teléfono: ");
            patient.setTelefono(scanner.nextLine().trim());

            System.out.print("Documento: ");
            patient.setDocumento(scanner.nextLine().trim());

            System.out.println("Tipo de documento:");
            System.out.println("1. CEDULA  2. PASAPORTE  3. TARJETA_IDENTIDAD");
            System.out.print("Seleccione: ");
            int tipoDoc = leerOpcion();
            String[] tipos = { "CEDULA", "PASAPORTE", "TARJETA_IDENTIDAD" };
            if (tipoDoc >= 1 && tipoDoc <= 3) {
                patient.setTipoDocumento(tipos[tipoDoc - 1]);
            }

            System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
            String fechaStr = scanner.nextLine().trim();
            try {
                patient.setFechaNacimiento(LocalDate.parse(fechaStr));
            } catch (DateTimeParseException e) {
                System.out.println("❌ Formato de fecha inválido");
                return;
            }

            System.out.println("Género:");
            System.out.println("1. MASCULINO  2. FEMENINO  3. OTRO");
            System.out.print("Seleccione: ");
            int genero = leerOpcion();
            String[] generos = { "MASCULINO", "FEMENINO", "OTRO" };
            if (genero >= 1 && genero <= 3) {
                patient.setGenero(generos[genero - 1]);
            }

            System.out.print("Ciudad (opcional): ");
            patient.setCiudad(scanner.nextLine().trim());

            System.out.print("Grupo sanguíneo (opcional): ");
            patient.setGrupoSanguineo(scanner.nextLine().trim());

            PatientDomain nuevoPaciente = patientInputPort.crearPaciente(patient);
            System.out.println("✅ Paciente creado exitosamente con ID: " + nuevoPaciente.getId());
            mostrarPaciente(nuevoPaciente);

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void listarTodosLosPacientes() {
        System.out.println("\n╔═══════════ TODOS LOS PACIENTES ════════════╗");
        List<PatientDomain> pacientes = patientInputPort.obtenerTodosLosPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            for (PatientDomain patient : pacientes) {
                mostrarPacienteResumido(patient);
            }
        }
    }

    private void listarPacientesActivos() {
        System.out.println("\n╔═══════════ PACIENTES ACTIVOS ════════════╗");
        List<PatientDomain> pacientes = patientInputPort.obtenerPacientesActivos();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes activos.");
        } else {
            for (PatientDomain patient : pacientes) {
                mostrarPacienteResumido(patient);
            }
        }
    }

    private void buscarPacientePorId() {
        System.out.print("\n➤ Ingrese el ID del paciente: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<PatientDomain> patient = patientInputPort.obtenerPacientePorId(id);

            if (patient.isPresent()) {
                mostrarPaciente(patient.get());
            } else {
                System.out.println("❌ Paciente no encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido");
        }
    }

    private void buscarPacientePorEmail() {
        System.out.print("\n➤ Ingrese el email: ");
        String email = scanner.nextLine().trim();
        Optional<PatientDomain> patient = patientInputPort.obtenerPacientePorEmail(email);

        if (patient.isPresent()) {
            mostrarPaciente(patient.get());
        } else {
            System.out.println("❌ Paciente no encontrado");
        }
    }

    private void buscarPacientesPorNombre() {
        System.out.print("\n➤ Ingrese el nombre a buscar: ");
        String nombre = scanner.nextLine().trim();
        List<PatientDomain> pacientes = patientInputPort.buscarPacientesPorNombre(nombre);

        if (pacientes.isEmpty()) {
            System.out.println("No se encontraron pacientes.");
        } else {
            pacientes.forEach(this::mostrarPacienteResumido);
        }
    }

    private void buscarPacientesPorGenero() {
        System.out.println("\nGénero:");
        System.out.println("1. MASCULINO  2. FEMENINO  3. OTRO");
        System.out.print("Seleccione: ");
        int opcion = leerOpcion();
        String[] generos = { "MASCULINO", "FEMENINO", "OTRO" };

        if (opcion >= 1 && opcion <= 3) {
            List<PatientDomain> pacientes = patientInputPort.buscarPacientesPorGenero(generos[opcion - 1]);
            if (pacientes.isEmpty()) {
                System.out.println("No se encontraron pacientes.");
            } else {
                pacientes.forEach(this::mostrarPacienteResumido);
            }
        }
    }

    private void actualizarPaciente() {
        System.out.print("\n➤ Ingrese el ID del paciente a actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<PatientDomain> patientOpt = patientInputPort.obtenerPacientePorId(id);

            if (patientOpt.isPresent()) {
                PatientDomain patient = patientOpt.get();
                System.out.println("Actualizar datos (Enter para mantener valor actual):");

                System.out.print("Nombre [" + patient.getNombre() + "]: ");
                String nombre = scanner.nextLine().trim();
                if (!nombre.isEmpty())
                    patient.setNombre(nombre);

                System.out.print("Ciudad [" + patient.getCiudad() + "]: ");
                String ciudad = scanner.nextLine().trim();
                if (!ciudad.isEmpty())
                    patient.setCiudad(ciudad);

                PatientDomain actualizado = patientInputPort.actualizarPaciente(id, patient);
                System.out.println("✅ Paciente actualizado exitosamente");
                mostrarPaciente(actualizado);
            } else {
                System.out.println("❌ Paciente no encontrado");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void desactivarPaciente() {
        System.out.print("\n➤ Ingrese el ID del paciente a desactivar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            patientInputPort.desactivarPaciente(id);
            System.out.println("✅ Paciente desactivado exitosamente");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void eliminarPaciente() {
        System.out.print("\n➤ Ingrese el ID del paciente a eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("¿Está seguro? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (confirmacion.equals("s")) {
                patientInputPort.eliminarPaciente(id);
                System.out.println("✅ Paciente eliminado exitosamente");
            } else {
                System.out.println("❌ Operación cancelada");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n╔═══════════ ESTADÍSTICAS DEL SISTEMA ════════════╗");

        long totalActivos = patientInputPort.contarPacientesActivos();
        long masculinos = patientInputPort.contarPacientesPorGenero("MASCULINO");
        long femeninos = patientInputPort.contarPacientesPorGenero("FEMENINO");

        System.out.println("📊 Total pacientes activos: " + totalActivos);
        System.out.println("👨 Masculinos: " + masculinos);
        System.out.println("👩 Femeninos: " + femeninos);
    }

    private void crearDatosEjemplo() {
        System.out.println("\n╔═══════════ CREANDO DATOS DE EJEMPLO ════════════╗");

        try {
            PatientDomain p1 = new PatientDomain("Juan", "Pérez", "juan@email.com",
                    "3001234567", "12345678", "CEDULA", LocalDate.of(1990, 5, 15), "MASCULINO");
            p1.setCiudad("Bogotá");
            p1.setGrupoSanguineo("O+");

            PatientDomain p2 = new PatientDomain("Ana", "García", "ana@email.com",
                    "3009876543", "87654321", "CEDULA", LocalDate.of(1985, 8, 22), "FEMENINO");
            p2.setCiudad("Medellín");
            p2.setGrupoSanguineo("A+");

            patientInputPort.crearPaciente(p1);
            patientInputPort.crearPaciente(p2);

            System.out.println("✅ Datos de ejemplo creados exitosamente");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    private void mostrarPaciente(PatientDomain patient) {
        System.out.println("\n┌────────────────────────────────────────┐");
        System.out.println("│ 📋 ID: " + patient.getId());
        System.out.println("│ 👤 Nombre: " + patient.getNombreCompleto());
        System.out.println("│ 📧 Email: " + patient.getEmail());
        System.out.println("│ 📞 Teléfono: " + patient.getTelefono());
        System.out.println("│ 🆔 Documento: " + patient.getDocumento());
        System.out.println("│ 🎂 Edad: " + patient.calcularEdad() + " años");
        System.out.println("│ 🏙️ Ciudad: " + (patient.getCiudad() != null ? patient.getCiudad() : "N/A"));
        System.out.println(
                "│ ⚕️ Grupo sanguíneo: " + (patient.getGrupoSanguineo() != null ? patient.getGrupoSanguineo() : "N/A"));
        System.out.println("│ ✅ Estado: " + (patient.getActivo() ? "Activo" : "Inactivo"));
        System.out.println("└────────────────────────────────────────┘");
    }

    private void mostrarPacienteResumido(PatientDomain patient) {
        System.out.printf("• ID: %d | %s | %s | %s%n",
                patient.getId(),
                patient.getNombreCompleto(),
                patient.getEmail(),
                patient.getActivo() ? "✅" : "❌");
    }
}
