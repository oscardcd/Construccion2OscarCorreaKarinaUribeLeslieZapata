package clinickol.clinicmanagement.console;

import clinickol.clinicmanagement.model.Gender;
import clinickol.clinicmanagement.model.Patient;
import clinickol.clinicmanagement.model.DocumentType;
import clinickol.clinicmanagement.service.PatientService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClinicConsoleApp implements CommandLineRunner {

    @Autowired
    private PatientService patientService;

    private Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== SISTEMA DE GESTIÓN DE CLÍNICA ===");
        System.out.println("Bienvenido al sistema de gestión de pacientes\n");

        while (running) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            procesarOpcion(opcion);
        }

        System.out.println("¡Gracias por usar el sistema!");
        scanner.close();
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n=== MENÚ PRINCIPAL ===");
        System.out.println("1. Gestionar Pacientes");
        System.out.println("2. Ver Estadísticas");
        System.out.println("3. Crear Datos de Ejemplo");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
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
            case 1:
                gestionarPacientes();
                break;
            case 2:
                mostrarEstadisticas();
                break;
            case 3:
                crearDatosEjemplo();
                break;
            case 4:
                running = false;
                break;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
        }
    }

    private void gestionarPacientes() {
        boolean enSubmenu = true;

        while (enSubmenu) {
            System.out.println("\n=== GESTIÓN DE PACIENTES ===");
            System.out.println("1. Crear Paciente");
            System.out.println("2. Listar Todos los Pacientes");
            System.out.println("3. Listar Pacientes Activos");
            System.out.println("4. Buscar Paciente por ID");
            System.out.println("5. Buscar Paciente por Email");
            System.out.println("6. Buscar Paciente por Documento");
            System.out.println("7. Buscar Pacientes por Nombre");
            System.out.println("8. Buscar Pacientes por Género");
            System.out.println("9. Buscar Pacientes por Ciudad");
            System.out.println("10. Actualizar Paciente");
            System.out.println("11. Desactivar Paciente");
            System.out.println("12. Activar Paciente");
            System.out.println("13. Eliminar Paciente");
            System.out.println("14. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    crearPaciente();
                    break;
                case 2:
                    listarTodosLosPacientes();
                    break;
                case 3:
                    listarPacientesActivos();
                    break;
                case 4:
                    buscarPacientePorId();
                    break;
                case 5:
                    buscarPacientePorEmail();
                    break;
                case 6:
                    buscarPacientePorDocumento();
                    break;
                case 7:
                    buscarPacientesPorNombre();
                    break;
                case 8:
                    buscarPacientesPorGenero();
                    break;
                case 9:
                    buscarPacientesPorCiudad();
                    break;
                case 10:
                    actualizarPaciente();
                    break;
                case 11:
                    desactivarPaciente();
                    break;
                case 12:
                    activarPaciente();
                    break;
                case 13:
                    eliminarPaciente();
                    break;
                case 14:
                    enSubmenu = false;
                    break;
                default:
                    System.out.println(" Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    private void crearPaciente() {
        System.out.println("\n=== CREAR NUEVO PACIENTE ===");

        try {
            Patient patient = new Patient();

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
            for (int i = 0; i < DocumentType.values().length; i++) {
                System.out.println((i + 1) + ". " + DocumentType.values()[i].getDescripcion());
            }
            System.out.print("Seleccione tipo de documento: ");
            int tipoDoc = leerOpcion() - 1;
            if (tipoDoc >= 0 && tipoDoc < DocumentType.values().length) {
                patient.setTipoDocumento(DocumentType.values()[tipoDoc]);
            } else {
                System.out.println("Tipo de documento inválido");
                return;
            }

            System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
            String fechaStr = scanner.nextLine().trim();
            try {
                patient.setFechaNacimiento(LocalDate.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido");
                return;
            }

            System.out.println("Género:");
            for (int i = 0; i < Gender.values().length; i++) {
                System.out.println((i + 1) + ". " + Gender.values()[i].getDescripcion());
            }
            System.out.print("Seleccione género: ");
            int genero = leerOpcion() - 1;
            if (genero >= 0 && genero < Gender.values().length) {
                patient.setGenero(Gender.values()[genero]);
            } else {
                System.out.println("❌ Género inválido");
                return;
            }

            System.out.print("Dirección (opcional): ");
            patient.setDireccion(scanner.nextLine().trim());

            System.out.print("Ciudad (opcional): ");
            patient.setCiudad(scanner.nextLine().trim());

            System.out.print("Código postal (opcional): ");
            patient.setCodigoPostal(scanner.nextLine().trim());

            System.out.print("Contacto de emergencia (opcional): ");
            patient.setContactoEmergencia(scanner.nextLine().trim());

            System.out.print("Teléfono de emergencia (opcional): ");
            patient.setTelefonoEmergencia(scanner.nextLine().trim());

            System.out.print("Alergias (opcional): ");
            patient.setAlergias(scanner.nextLine().trim());

            System.out.print("Medicamentos actuales (opcional): ");
            patient.setMedicamentosActuales(scanner.nextLine().trim());

            System.out.print("Grupo sanguíneo (opcional): ");
            patient.setGrupoSanguineo(scanner.nextLine().trim());

            System.out.print("Peso en kg (opcional): ");
            String pesoStr = scanner.nextLine().trim();
            if (!pesoStr.isEmpty()) {
                try {
                    patient.setPeso(Double.parseDouble(pesoStr));
                } catch (NumberFormatException e) {
                    System.out.println("❌ Peso inválido");
                    return;
                }
            }

            System.out.print("Altura en metros (opcional): ");
            String alturaStr = scanner.nextLine().trim();
            if (!alturaStr.isEmpty()) {
                try {
                    patient.setAltura(Double.parseDouble(alturaStr));
                } catch (NumberFormatException e) {
                    System.out.println("❌ Altura inválida");
                    return;
                }
            }

            Patient nuevoPaciente = patientService.crearPaciente(patient);
            System.out.println("✅ Paciente creado exitosamente con ID: " + nuevoPaciente.getId());
            mostrarPaciente(nuevoPaciente);

        } catch (Exception e) {
            System.out.println("❌ Error al crear paciente: " + e.getMessage());
        }
    }

    private void listarTodosLosPacientes() {
        System.out.println("\n=== TODOS LOS PACIENTES ===");
        List<Patient> pacientes = patientService.obtenerTodosLosPacientes();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            for (Patient patient : pacientes) {
                mostrarPaciente(patient);
                System.out.println("---");
            }
        }
    }

    private void listarPacientesActivos() {
        System.out.println("\n=== PACIENTES ACTIVOS ===");
        List<Patient> pacientes = patientService.obtenerPacientesActivos();

        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes activos.");
        } else {
            for (Patient patient : pacientes) {
                mostrarPaciente(patient);
                System.out.println("---");
            }
        }
    }

    private void buscarPacientePorId() {
        System.out.print("\nIngrese el ID del paciente: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Patient> patient = patientService.obtenerPacientePorId(id);

            if (patient.isPresent()) {
                mostrarPaciente(patient.get());
            } else {
                System.out.println("❌ Paciente no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido");
        }
    }

    private void buscarPacientePorEmail() {
        System.out.print("\nIngrese el email del paciente: ");
        String email = scanner.nextLine().trim();
        Optional<Patient> patient = patientService.obtenerPacientePorEmail(email);

        if (patient.isPresent()) {
            mostrarPaciente(patient.get());
        } else {
            System.out.println("❌ Paciente no encontrado con email: " + email);
        }
    }

    private void buscarPacientePorDocumento() {
        System.out.print("\nIngrese el documento del paciente: ");
        String documento = scanner.nextLine().trim();
        Optional<Patient> patient = patientService.obtenerPacientePorDocumento(documento);

        if (patient.isPresent()) {
            mostrarPaciente(patient.get());
        } else {
            System.out.println("❌ Paciente no encontrado con documento: " + documento);
        }
    }

    private void buscarPacientesPorNombre() {
        System.out.print("\nIngrese el nombre a buscar: ");
        String nombre = scanner.nextLine().trim();
        List<Patient> pacientes = patientService.buscarPacientesPorNombre(nombre);

        if (pacientes.isEmpty()) {
            System.out.println("No se encontraron pacientes con el nombre: " + nombre);
        } else {
            System.out.println("Pacientes encontrados:");
            for (Patient patient : pacientes) {
                mostrarPaciente(patient);
                System.out.println("---");
            }
        }
    }

    private void buscarPacientesPorGenero() {
        System.out.println("\nSeleccione el género:");
        for (int i = 0; i < Gender.values().length; i++) {
            System.out.println((i + 1) + ". " + Gender.values()[i].getDescripcion());
        }
        System.out.print("Seleccione género: ");
        int genero = leerOpcion() - 1;

        if (genero >= 0 && genero < Gender.values().length) {
            List<Patient> pacientes = patientService.buscarPacientesPorGenero(Gender.values()[genero]);

            if (pacientes.isEmpty()) {
                System.out.println("No se encontraron pacientes con el género seleccionado.");
            } else {
                System.out.println("Pacientes encontrados:");
                for (Patient patient : pacientes) {
                    mostrarPaciente(patient);
                    System.out.println("---");
                }
            }
        } else {
            System.out.println("❌ Género inválido");
        }
    }

    private void buscarPacientesPorCiudad() {
        System.out.print("\nIngrese la ciudad a buscar: ");
        String ciudad = scanner.nextLine().trim();
        List<Patient> pacientes = patientService.buscarPacientesPorCiudad(ciudad);

        if (pacientes.isEmpty()) {
            System.out.println("No se encontraron pacientes en la ciudad: " + ciudad);
        } else {
            System.out.println("Pacientes encontrados:");
            for (Patient patient : pacientes) {
                mostrarPaciente(patient);
                System.out.println("---");
            }
        }
    }

    private void actualizarPaciente() {
        System.out.print("\nIngrese el ID del paciente a actualizar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            Optional<Patient> patientOpt = patientService.obtenerPacientePorId(id);

            if (patientOpt.isPresent()) {
                Patient patient = patientOpt.get();
                System.out.println("Paciente actual:");
                mostrarPaciente(patient);

                System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");

                System.out.print("Nombre [" + patient.getNombre() + "]: ");
                String nombre = scanner.nextLine().trim();
                if (!nombre.isEmpty()) {
                    patient.setNombre(nombre);
                }

                System.out.print("Apellido [" + patient.getApellido() + "]: ");
                String apellido = scanner.nextLine().trim();
                if (!apellido.isEmpty()) {
                    patient.setApellido(apellido);
                }

                System.out.print("Email [" + patient.getEmail() + "]: ");
                String email = scanner.nextLine().trim();
                if (!email.isEmpty()) {
                    patient.setEmail(email);
                }

                System.out.print("Teléfono [" + patient.getTelefono() + "]: ");
                String telefono = scanner.nextLine().trim();
                if (!telefono.isEmpty()) {
                    patient.setTelefono(telefono);
                }

                System.out.print("Dirección [" + patient.getDireccion() + "]: ");
                String direccion = scanner.nextLine().trim();
                if (!direccion.isEmpty()) {
                    patient.setDireccion(direccion);
                }

                System.out.print("Ciudad [" + patient.getCiudad() + "]: ");
                String ciudad = scanner.nextLine().trim();
                if (!ciudad.isEmpty()) {
                    patient.setCiudad(ciudad);
                }

                System.out.print("Peso [" + patient.getPeso() + "]: ");
                String pesoStr = scanner.nextLine().trim();
                if (!pesoStr.isEmpty()) {
                    try {
                        patient.setPeso(Double.parseDouble(pesoStr));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Peso inválido, manteniendo valor actual");
                    }
                }

                System.out.print("Altura [" + patient.getAltura() + "]: ");
                String alturaStr = scanner.nextLine().trim();
                if (!alturaStr.isEmpty()) {
                    try {
                        patient.setAltura(Double.parseDouble(alturaStr));
                    } catch (NumberFormatException e) {
                        System.out.println("❌ Altura inválida, manteniendo valor actual");
                    }
                }

                Patient pacienteActualizado = patientService.actualizarPaciente(id, patient);
                System.out.println("✅ Paciente actualizado exitosamente");
                mostrarPaciente(pacienteActualizado);

            } else {
                System.out.println("❌ Paciente no encontrado con ID: " + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido");
        } catch (Exception e) {
            System.out.println("❌ Error al actualizar paciente: " + e.getMessage());
        }
    }

    private void desactivarPaciente() {
        System.out.print("\nIngrese el ID del paciente a desactivar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            patientService.desactivarPaciente(id);
            System.out.println("✅ Paciente desactivado exitosamente");
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido");
        } catch (Exception e) {
            System.out.println("❌ Error al desactivar paciente: " + e.getMessage());
        }
    }

    private void activarPaciente() {
        System.out.print("\nIngrese el ID del paciente a activar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            patientService.activarPaciente(id);
            System.out.println("✅ Paciente activado exitosamente");
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido");
        } catch (Exception e) {
            System.out.println("❌ Error al activar paciente: " + e.getMessage());
        }
    }

    private void eliminarPaciente() {
        System.out.print("\nIngrese el ID del paciente a eliminar: ");
        try {
            Long id = Long.parseLong(scanner.nextLine().trim());
            System.out.print("¿Está seguro de que desea eliminar este paciente? (s/n): ");
            String confirmacion = scanner.nextLine().trim().toLowerCase();

            if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
                patientService.eliminarPaciente(id);
                System.out.println("✅ Paciente eliminado exitosamente");
            } else {
                System.out.println("❌ Operación cancelada");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ ID inválido");
        } catch (Exception e) {
            System.out.println("❌ Error al eliminar paciente: " + e.getMessage());
        }
    }

    private void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS DEL SISTEMA ===");

        try {
            long totalActivos = patientService.contarPacientesActivos();
            long masculinos = patientService.contarPacientesPorGenero(Gender.MASCULINO);
            long femeninos = patientService.contarPacientesPorGenero(Gender.FEMENINO);
            long otros = patientService.contarPacientesPorGenero(Gender.OTRO);
            long noEspecificado = patientService.contarPacientesPorGenero(Gender.NO_ESPECIFICADO);

            System.out.println("Total de pacientes activos: " + totalActivos);
            System.out.println("Pacientes masculinos: " + masculinos);
            System.out.println("Pacientes femeninos: " + femeninos);
            System.out.println("Otros géneros: " + otros);
            System.out.println("No especificado: " + noEspecificado);

        } catch (Exception e) {
            System.out.println("❌ Error al obtener estadísticas: " + e.getMessage());
        }
    }

    private void crearDatosEjemplo() {
        System.out.println("\n=== CREANDO DATOS DE EJEMPLO ===");

        try {
            // Crear pacientes de ejemplo
            Patient paciente1 = new Patient(
                    "Juan", "Pérez", "juan.perez@email.com", "3001234567",
                    "12345678", DocumentType.CEDULA,
                    LocalDate.of(1990, 5, 15), Gender.MASCULINO);
            paciente1.setDireccion("Calle 123 #45-67");
            paciente1.setCiudad("Bogotá");
            paciente1.setCodigoPostal("110111");
            paciente1.setContactoEmergencia("María Pérez");
            paciente1.setTelefonoEmergencia("3007654321");
            paciente1.setAlergias("Penicilina, Polen");
            paciente1.setMedicamentosActuales("Vitamina D");
            paciente1.setGrupoSanguineo("O+");
            paciente1.setPeso(75.5);
            paciente1.setAltura(1.75);

            Patient paciente2 = new Patient(
                    "Ana", "García", "ana.garcia@email.com", "3009876543",
                    "87654321", DocumentType.CEDULA,
                    LocalDate.of(1985, 8, 22), Gender.FEMENINO);
            paciente2.setDireccion("Carrera 45 #78-90");
            paciente2.setCiudad("Medellín");
            paciente2.setCodigoPostal("050001");
            paciente2.setContactoEmergencia("Carlos García");
            paciente2.setTelefonoEmergencia("3001112222");
            paciente2.setAlergias("Ninguna");
            paciente2.setMedicamentosActuales("Anticonceptivos");
            paciente2.setGrupoSanguineo("A+");
            paciente2.setPeso(62.0);
            paciente2.setAltura(1.65);

            Patient paciente3 = new Patient(
                    "Carlos", "López", "carlos.lopez@email.com", "3005556666",
                    "11223344", DocumentType.CEDULA,
                    LocalDate.of(1992, 12, 3), Gender.MASCULINO);
            paciente3.setDireccion("Avenida 68 #12-34");
            paciente3.setCiudad("Cali");
            paciente3.setCodigoPostal("760001");
            paciente3.setContactoEmergencia("Laura López");
            paciente3.setTelefonoEmergencia("3007778888");
            paciente3.setAlergias("Mariscos");
            paciente3.setMedicamentosActuales("Ninguno");
            paciente3.setGrupoSanguineo("B+");
            paciente3.setPeso(80.0);
            paciente3.setAltura(1.80);

            // Guardar pacientes
            patientService.crearPaciente(paciente1);
            patientService.crearPaciente(paciente2);
            patientService.crearPaciente(paciente3);

            System.out.println("✅ Datos de ejemplo creados exitosamente");
            System.out.println("Se crearon 3 pacientes de ejemplo");

        } catch (Exception e) {
            System.out.println("❌ Error al crear datos de ejemplo: " + e.getMessage());
        }
    }

    private void mostrarPaciente(Patient patient) {
        System.out.println("\n📋 INFORMACIÓN DEL PACIENTE");
        System.out.println("ID: " + patient.getId());
        System.out.println("Nombre: " + patient.getNombreCompleto());
        System.out.println("Email: " + patient.getEmail());
        System.out.println("Teléfono: " + (patient.getTelefono() != null ? patient.getTelefono() : "No especificado"));
        System.out.println(
                "Documento: " + patient.getDocumento() + " (" + patient.getTipoDocumento().getDescripcion() + ")");
        System.out.println("Fecha de nacimiento: " + patient.getFechaNacimiento());
        System.out.println("Edad: " + patient.calcularEdad() + " años");
        System.out.println("Género: " + patient.getGenero().getDescripcion());
        System.out
                .println("Dirección: " + (patient.getDireccion() != null ? patient.getDireccion() : "No especificada"));
        System.out.println("Ciudad: " + (patient.getCiudad() != null ? patient.getCiudad() : "No especificada"));
        System.out.println("Grupo sanguíneo: "
                + (patient.getGrupoSanguineo() != null ? patient.getGrupoSanguineo() : "No especificado"));

        if (patient.getPeso() != null && patient.getAltura() != null) {
            System.out.println("Peso: " + patient.getPeso() + " kg");
            System.out.println("Altura: " + patient.getAltura() + " m");
            System.out.println(
                    "IMC: " + String.format("%.2f", patient.calcularIMC()) + " (" + patient.getCategoriaIMC() + ")");
        }

        if (patient.getAlergias() != null && !patient.getAlergias().isEmpty()) {
            System.out.println("Alergias: " + patient.getAlergias());
        }

        if (patient.getMedicamentosActuales() != null && !patient.getMedicamentosActuales().isEmpty()) {
            System.out.println("Medicamentos actuales: " + patient.getMedicamentosActuales());
        }

        if (patient.getContactoEmergencia() != null && !patient.getContactoEmergencia().isEmpty()) {
            System.out.println("Contacto de emergencia: " + patient.getContactoEmergencia());
            if (patient.getTelefonoEmergencia() != null) {
                System.out.println("Teléfono de emergencia: " + patient.getTelefonoEmergencia());
            }
        }

        System.out.println("Estado: " + (patient.getActivo() ? "✅ Activo" : "❌ Inactivo"));
        System.out.println("Fecha de registro: " + patient.getFechaCreacion());
    }
}
