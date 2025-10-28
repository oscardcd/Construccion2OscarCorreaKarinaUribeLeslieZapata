# Sistema de Gestión de Clínica - API REST

## Estado del Proyecto

### ✅ Completado

1. **Entidades del Dominio**
   - ✅ Empleado con roles (RRHH, Admin, Soporte, Enfermera, Médico)
   - ✅ Paciente con ContactoEmergencia y SeguroMedico
   - ✅ Inventario: Medicamento, Procedimiento, AyudaDiagnostica, Especialidad
   - ✅ Órdenes: Orden, OrdenMedicamento, OrdenProcedimiento, OrdenAyudaDiagnostica

2. **Repositorios JPA**
   - ✅ Todos los repositorios creados

3. **Servicios y Controladores REST**
   - ✅ EmpleadoService + EmpleadoController
   - ✅ PacienteService + PacienteController
   - ✅ MedicamentoService + MedicamentoController

## Cómo Ejecutar

### 1. Compilar el proyecto
```bash
cd "/Users/oscarcorrea/Desktop/TdeA/construccion de sorfware II/Construccion2OscarCorreaKarinaUribeLeslieZapata/ClinicManagement"
mvn clean install
```

### 2. Ejecutar la aplicación
```bash
mvn spring-boot:run
```

### 3. Acceder al Sistema

#### 🌐 Interfaz Web
- **Página Principal**: http://localhost:8080 (redirige al login)
- **Login**: http://localhost:8080/login.html
- **Dashboard**: http://localhost:8080/dashboard.html

#### 🔌 API REST
- **API Base URL**: http://localhost:8080/api
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:clinicdb`
  - User: `sa`
  - Password: (dejar vacío)

### 4. Crear primer usuario y hacer Login

**Paso 1: Crear un empleado (vía API)**
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCompleto": "Admin Sistema",
    "cedula": "1234567890",
    "correoElectronico": "admin@clinica.com",
    "telefono": "3001234567",
    "fechaNacimiento": "1990-01-15",
    "direccion": "Calle 123",
    "rol": "RECURSOS_HUMANOS",
    "nombreUsuario": "admin",
    "contrasena": "Admin123@"
  }'
```

**Paso 2: Acceder al Login**
- Ir a: http://localhost:8080
- Cédula: `1234567890`
- Contraseña: `Admin123@`
- Click en "Iniciar Sesión"

Ver `GUIA_LOGIN.md` para más detalles.

## Endpoints API Disponibles

### Empleados (Recursos Humanos)
```
POST   /api/empleados                  - Crear empleado
GET    /api/empleados                  - Listar todos
GET    /api/empleados/{id}             - Obtener por ID
GET    /api/empleados/cedula/{cedula}  - Obtener por cédula
GET    /api/empleados/rol/{rol}        - Listar por rol
GET    /api/empleados/activos          - Listar activos
PUT    /api/empleados/{id}             - Actualizar
DELETE /api/empleados/{id}             - Eliminar
PATCH  /api/empleados/{id}/desactivar  - Desactivar
```

### Pacientes (Personal Administrativo)
```
POST   /api/pacientes                  - Registrar paciente
GET    /api/pacientes                  - Listar todos
GET    /api/pacientes/{id}             - Obtener por ID
GET    /api/pacientes/cedula/{cedula}  - Obtener por cédula
GET    /api/pacientes/activos          - Listar activos
GET    /api/pacientes/buscar?nombre=   - Buscar por nombre
PUT    /api/pacientes/{id}             - Actualizar
PATCH  /api/pacientes/{id}/desactivar  - Desactivar
```

### Inventario - Medicamentos (Soporte de Información)
```
POST   /api/inventario/medicamentos           - Crear medicamento
GET    /api/inventario/medicamentos           - Listar todos
GET    /api/inventario/medicamentos/{id}      - Obtener por ID
GET    /api/inventario/medicamentos/activos   - Listar activos
GET    /api/inventario/medicamentos/buscar?nombre= - Buscar
PUT    /api/inventario/medicamentos/{id}      - Actualizar
DELETE /api/inventario/medicamentos/{id}      - Eliminar
```

## Ejemplos de Uso con curl

### Crear un Empleado (Recursos Humanos)
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCompleto": "Juan Pérez",
    "cedula": "1234567890",
    "correoElectronico": "juan@clinica.com",
    "telefono": "3001234567",
    "fechaNacimiento": "1990-01-15",
    "direccion": "Calle 123 #45-67",
    "rol": "RECURSOS_HUMANOS",
    "nombreUsuario": "jperez",
    "contrasena": "Password1@"
  }'
```

### Registrar un Paciente
```bash
curl -X POST http://localhost:8080/api/pacientes \
  -H "Content-Type: application/json" \
  -d '{
    "cedula": "9876543210",
    "nombreCompleto": "María García",
    "fechaNacimiento": "1985-05-20",
    "genero": "FEMENINO",
    "direccion": "Avenida 45 #12-34",
    "telefono": "3009876543",
    "correoElectronico": "maria@email.com",
    "contactoEmergencia": {
      "nombre": "Pedro",
      "apellido": "García",
      "relacionConPaciente": "Hermano",
      "telefonoEmergencia": "3001112233"
    },
    "seguroMedico": {
      "nombreCompania": "Salud Total",
      "numeroPoliza": "POL-12345",
      "estadoPoliza": true,
      "vigenciaPoliza": "2025-12-31"
    }
  }'
```

### Crear un Medicamento
```bash
curl -X POST http://localhost:8080/api/inventario/medicamentos \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Acetaminofén 500mg",
    "descripcion": "Analgésico y antipirético",
    "costo": 15000.0,
    "stock": 100
  }'
```

### Listar Todos los Empleados
```bash
curl http://localhost:8080/api/empleados
```

### Listar Pacientes Activos
```bash
curl http://localhost:8080/api/pacientes/activos
```

## Próximos Pasos (Pendientes)

Para completar el sistema según las especificaciones, faltan:

1. **Servicios y Controladores adicionales:**
   - ProcedimientoService/Controller
   - AyudaDiagnosticaService/Controller
   - EspecialidadService/Controller
   - OrdenService/Controller
   - FacturacionService/Controller

2. **Historia Clínica (MongoDB):**
   - Configurar MongoDB
   - Crear documento de HistoriaClinica
   - Repositorio y servicio MongoDB

3. **Lógica de Facturación:**
   - Cálculo de copagos
   - Generación de facturas
   - Aplicación de reglas de negocio

4. **Validaciones adicionales:**
   - Validación de reglas de órdenes
   - Control de acceso por roles
   - Validaciones de negocio complejas

## Estructura del Proyecto (29 archivos)

```
src/main/java/clinickol/clinicmanagement/
│
├── ClinicManagementApplication.java  # Main Spring Boot
│
├── controller/                       # 3 Controladores REST
│   ├── EmpleadoController.java
│   ├── MedicamentoController.java
│   └── PacienteController.java
│
├── domain/model/                     # 13 Entidades JPA
│   ├── enums/
│   │   ├── Gender.java
│   │   ├── Role.java
│   │   └── TipoOrden.java
│   ├── AyudaDiagnostica.java
│   ├── ContactoEmergencia.java
│   ├── Empleado.java
│   ├── Especialidad.java
│   ├── Medicamento.java
│   ├── Orden.java
│   ├── OrdenAyudaDiagnostica.java
│   ├── OrdenMedicamento.java
│   ├── OrdenProcedimiento.java
│   ├── Paciente.java
│   ├── Procedimiento.java
│   └── SeguroMedico.java
│
├── repository/                       # 7 Repositorios JPA
│   ├── AyudaDiagnosticaRepository.java
│   ├── EmpleadoRepository.java
│   ├── EspecialidadRepository.java
│   ├── MedicamentoRepository.java
│   ├── OrdenRepository.java
│   ├── PacienteRepository.java
│   └── ProcedimientoRepository.java
│
└── service/                          # 3 Servicios de Negocio
    ├── EmpleadoService.java
    ├── MedicamentoService.java
    └── PacienteService.java
```

## Arquitectura

**Patrón: Repository + Service + Controller (REST API)**

```
Cliente REST → Controller → Service → Repository → H2 Database
```

## Notas

- ✅ **Proyecto limpio**: 29 archivos Java organizados
- ✅ **Sin código heredado**: Eliminada arquitectura hexagonal antigua
- ✅ **Sin duplicados**: Todas las entidades únicas
- ✅ **Compilación exitosa**: BUILD SUCCESS
- El proyecto usa H2 in-memory database (los datos se pierden al reiniciar)
- Para cambiar a MySQL, modificar `application.properties` y agregar dependencia MySQL
- Las validaciones están implementadas con Jakarta Validation
- CORS está habilitado para facilitar pruebas desde frontend

## 🎨 Interfaz Web Implementada

El sistema incluye un **frontend completo** con:

✅ **Página de Login** (`/login.html`)
- Diseño moderno con gradientes
- Validación de campos en tiempo real
- Autenticación contra la API REST
- Mensajes de error descriptivos

✅ **Dashboard Dinámico** (`/dashboard.html`)
- Personalizado según rol de usuario
- Tarjetas interactivas por permisos
- Información del usuario logueado
- Opción de cerrar sesión

### Roles Soportados
- 👥 **Recursos Humanos** - Gestión de empleados
- 📋 **Personal Administrativo** - Pacientes y facturación  
- 💻 **Soporte de Información** - Inventarios
- 👩‍⚕️ **Enfermera** - Registro de visitas
- 👨‍⚕️ **Médico** - Historia clínica y órdenes

## Documentación Adicional

- `GUIA_LOGIN.md` - **Guía completa del sistema de login** 🔐
- `PROYECTO_COMPLETADO.md` - Resumen completo con ejemplos de uso
- `ESTRUCTURA_LIMPIA.md` - Detalles de la limpieza realizada
- `GUIA_IMPLEMENTACION.md` - Guía para completar módulos adicionales


