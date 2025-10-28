# Guía de Implementación - Sistema de Gestión Clínica

## Estado Actual del Proyecto

### ✅ Completado

1. **Configuración Base**
   - `pom.xml` configurado con Spring Boot 3.3.4
   - `application.properties` con H2 Database
   - `ClinicManagementApplication.java` - Clase principal

2. **Entidades Creadas**
   - `Empleado` - Gestión de usuarios del sistema con roles
   - `Paciente` - Información completa del paciente
   - `ContactoEmergencia` - Contacto de emergencia del paciente
   - `SeguroMedico` - Información de seguros con lógica de copago
   - Enums: `Role`, `Gender`

### 🔨 Pendiente por Crear

#### 1. Entidades de Inventario
```
domain/model/
  - Medicamento.java
  - Procedimiento.java
  - AyudaDiagnostica.java
  - Especialidad.java
```

#### 2. Entidades de Órdenes
```
domain/model/
  - Orden.java
  - OrdenMedicamento.java
  - OrdenProcedimiento.java
  - OrdenAyudaDiagnostica.java
```

#### 3. Repositorios
```
repository/
  - EmpleadoRepository.java
  - PacienteRepository.java
  - MedicamentoRepository.java
  - ProcedimientoRepository.java
  - AyudaDiagnosticaRepository.java
  - EspecialidadRepository.java
  - OrdenRepository.java
  - OrdenMedicamentoRepository.java
  - OrdenProcedimientoRepository.java
  - OrdenAyudaDiagnosticaRepository.java
```

#### 4. DTOs (Data Transfer Objects)
```
dto/
  - EmpleadoDTO.java
  - PacienteDTO.java
  - OrdenDTO.java
  - FacturaDTO.java
  etc...
```

#### 5. Servicios
```
service/
  - EmpleadoService.java
  - PacienteService.java
  - OrdenService.java
  - FacturacionService.java
  - InventarioService.java
```

#### 6. Controladores REST
```
controller/
  - EmpleadoController.java
  - PacienteController.java
  - OrdenController.java
  - FacturacionController.java
  - InventarioController.java
```

##  Próximos Pasos

¿Deseas que:

**Opción A:** Continúe creando TODOS los archivos necesarios de manera completa (tomará tiempo pero tendrás el sistema completo funcional)

**Opción B:** Cree un módulo completo de ejemplo (ej: Módulo de Empleados con entidad, repository, service, controller) para que veas cómo funciona todo integrado, y luego repliques para otros módulos

**Opción C:** Te proporcione templates/plantillas de código para que puedas completar los módulos restantes tú mismo

## Endpoints REST que tendrá el sistema

### Empleados (Recursos Humanos)
- POST /api/empleados - Crear empleado
- GET /api/empleados - Listar todos
- GET /api/empleados/{id} - Obtener por ID
- PUT /api/empleados/{id} - Actualizar
- DELETE /api/empleados/{id} - Eliminar

### Pacientes (Personal Administrativo)
- POST /api/pacientes - Registrar paciente
- GET /api/pacientes - Listar todos
- GET /api/pacientes/{cedula} - Buscar por cédula
- PUT /api/pacientes/{id} - Actualizar
- POST /api/pacientes/{id}/seguro - Agregar seguro

### Órdenes (Médicos)
- POST /api/ordenes - Crear orden
- GET /api/ordenes - Listar órdenes
- GET /api/ordenes/{numero} - Obtener orden
- PUT /api/ordenes/{numero}/medicamento - Agregar medicamento
- PUT /api/ordenes/{numero}/procedimiento - Agregar procedimiento

### Facturación (Personal Administrativo)
- GET /api/facturas/{pacienteId} - Generar factura
- POST /api/facturas/calcular - Calcular monto

### Inventario (Soporte de Información)
- POST /api/inventario/medicamentos - Agregar medicamento
- POST /api/inventario/procedimientos - Agregar procedimiento
- GET /api/inventario/medicamentos - Listar medicamentos
- GET /api/inventario/procedimientos - Listar procedimientos


