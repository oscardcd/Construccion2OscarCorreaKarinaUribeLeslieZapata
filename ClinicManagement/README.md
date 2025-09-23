# Sistema de Gestión de Clínica

## Descripción

Sistema de gestión de información de clínica implementado con arquitectura hexagonal (Ports and Adapters) en Java con Spring Boot. El sistema permite la gestión de pacientes, personal médico, historias clínicas, órdenes médicas, signos vitales y facturación.

## Arquitectura

El proyecto implementa una arquitectura hexagonal que separa claramente:

- **Domain**: Entidades y lógica de negocio
- **Application**: Casos de uso y puertos (interfaces)
- **Adapters**: Implementaciones de persistencia y controladores

## Roles del Sistema

### 1. Recursos Humanos
- Crear y eliminar usuarios
- Gestionar permisos y roles
- Actualizar información del personal

### 2. Personal Administrativo
- Registrar y actualizar pacientes
- Gestionar información de seguros
- Programar citas
- Generar facturas

### 3. Enfermeras
- Registrar signos vitales
- Administrar medicamentos
- Realizar procedimientos
- Registrar observaciones

### 4. Médicos
- Crear historias clínicas
- Generar órdenes médicas
- Diagnosticar pacientes
- Prescribir tratamientos

### 5. Soporte de Información
- Gestionar inventario
- Mantener el sistema
- Resolver problemas técnicos

## Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** (Base de datos SQL)
- **Spring Data MongoDB** (Base de datos NoSQL)
- **H2 Database** (Desarrollo)
- **PostgreSQL** (Producción)
- **MongoDB** (Historia clínica)
- **Spring Security** (Autenticación y autorización)
- **Maven** (Gestión de dependencias)

## Estructura del Proyecto (Arquitectura Hexagonal)

```
src/main/java/com/mycompany/clinicmanagement/
├── domain/                          # 🧠 NÚCLEO DEL DOMINIO
│   ├── models/                      # Entidades y Value Objects
│   │   ├── User.java
│   │   ├── Patient.java
│   │   ├── MedicalRecord.java
│   │   ├── Order.java
│   │   ├── VitalSigns.java
│   │   └── Invoice.java
│   └── service/                     # Servicios de Dominio
│       ├── UserDomainService.java
│       ├── PatientDomainService.java
│       └── BillingDomainService.java
│
├── application/                     # ⚙️ CAPA DE APLICACIÓN
│   ├── port/                        # Puertos (Interfaces)
│   │   ├── *RepositoryPort.java     # Puertos de salida
│   │   └── *ServicePort.java        # Puertos de servicio
│   └── usecases/                    # Casos de Uso por Rol
│       ├── rh/                      # Recursos Humanos
│       ├── admin/                   # Personal Administrativo
│       ├── nurse/                   # Enfermeras
│       ├── doctor/                  # Médicos
│       ├── support/                 # Soporte de Información
│       └── billing/                 # Facturación
│
├── adapter/                         # 🔌 ADAPTADORES
│   ├── in/                          # Adaptadores de Entrada
│   │   └── web/                     # Controladores REST
│   │       ├── UserController.java
│   │       ├── PatientController.java
│   │       └── ...
│   └── out/                         # Adaptadores de Salida
│       └── persistence/             # Repositorios
│           ├── UserRepositoryAdapter.java
│           ├── PatientRepositoryAdapter.java
│           └── ...
│
├── config/                          # 🧾 CONFIGURACIÓN
│   ├── ApplicationConfig.java
│   └── SecurityConfig.java
│
└── ClinicManagement.java            # 🚀 Aplicación Principal
```

## Entidades Principales

### Usuario
- Gestión de usuarios del sistema
- Roles y permisos
- Autenticación

### Paciente
- Información personal
- Datos de contacto de emergencia
- Información de seguro médico

### Historia Clínica
- Registros médicos no estructurados
- Almacenados en MongoDB
- Clave: cédula del paciente + fecha

### Orden Médica
- Medicamentos, procedimientos, ayudas diagnósticas
- Almacenados en base de datos SQL
- Reglas de negocio específicas

### Signos Vitales
- Presión arterial, temperatura, pulso, oxígeno
- Registrados por enfermeras
- Alertas por valores anormales

### Facturación
- Sistema de copagos
- Integración con seguros
- Reglas de facturación específicas

## Reglas de Negocio

### Facturación
- Copago fijo de $50,000 cuando la póliza está activa
- Límite anual de $1,000,000 en copagos
- Pago total cuando la póliza está inactiva

### Órdenes Médicas
- No se pueden mezclar ayudas diagnósticas con medicamentos/procedimientos
- Cada ítem tiene un número único dentro de la orden
- Validaciones específicas por tipo de orden

### Historia Clínica
- Almacenada como diccionario en MongoDB
- Clave principal: cédula del paciente
- Subclave: fecha de atención

## Configuración

### Base de Datos
- **H2**: Base de datos en memoria para desarrollo
- **PostgreSQL**: Base de datos de producción
- **MongoDB**: Almacenamiento de historias clínicas

### Seguridad
- Autenticación básica configurada
- Roles y permisos por funcionalidad
- Validaciones de acceso por rol

## Ejecución

### Requisitos
- Java 17 o superior
- Maven 3.6 o superior
- MongoDB (para historias clínicas)

### Modos de Ejecución

#### 🌐 Modo Web (API REST)
```bash
# Usando script (recomendado)
./start-web.sh

# O manualmente
mvn spring-boot:run
```

#### 💻 Modo Consola
```bash
# Usando script
./start-console.sh

# O manualmente
mvn spring-boot:run -Dspring-boot.run.arguments="console"
```

### Comandos de Desarrollo

```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar tests
mvn test

# Ejecutar tests con cobertura
mvn test jacoco:report

# Limpiar y compilar
mvn clean package
```

### Acceso
- **API REST**: http://localhost:8080/api
- **H2 Console**: http://localhost:8080/api/h2-console
- **Actuator**: http://localhost:8080/api/actuator
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html (próximamente)

## API Endpoints

### Usuarios (Recursos Humanos)
- `POST /api/users` - Crear usuario
- `PUT /api/users/{id}` - Actualizar usuario
- `DELETE /api/users/{id}` - Eliminar usuario
- `GET /api/users/statistics` - Estadísticas de usuarios

### Pacientes (Personal Administrativo)
- `POST /api/patients` - Registrar paciente
- `PUT /api/patients/{id}` - Actualizar paciente
- `GET /api/patients` - Buscar pacientes
- `PUT /api/patients/{id}/insurance` - Actualizar seguro

### Signos Vitales (Enfermeras)
- `POST /api/vital-signs` - Registrar signos vitales
- `GET /api/vital-signs/patient/{id}` - Obtener signos vitales del paciente
- `GET /api/vital-signs/abnormal` - Obtener signos vitales anormales

### Historias Clínicas (Médicos)
- `POST /api/medical-records` - Crear historia clínica
- `GET /api/medical-records/patient/{id}` - Obtener historial del paciente
- `PUT /api/medical-records/{id}` - Actualizar historia clínica

### Órdenes Médicas (Médicos)
- `POST /api/orders` - Crear orden médica
- `POST /api/orders/{id}/items` - Agregar ítem a orden
- `GET /api/orders/patient/{id}` - Obtener órdenes del paciente

### Facturación (Personal Administrativo)
- `POST /api/invoices` - Generar factura
- `GET /api/invoices/patient/{id}` - Obtener facturas del paciente
- `GET /api/invoices/statistics` - Estadísticas de facturación

## Desarrollo

### Patrones Utilizados
- **Arquitectura Hexagonal**: Separación clara de responsabilidades
- **Domain-Driven Design**: Modelado basado en el dominio de negocio
- **Repository Pattern**: Abstracción de acceso a datos
- **Use Case Pattern**: Encapsulación de lógica de negocio

### Próximos Pasos
1. Implementar adaptadores de persistencia
2. Crear controladores REST
3. Implementar servicios de dominio
4. Agregar tests unitarios e integración
5. Configurar CI/CD
6. Documentar API con Swagger

## Contribución

1. Fork el proyecto
2. Crear una rama para la feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit los cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crear un Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo `LICENSE` para más detalles.
