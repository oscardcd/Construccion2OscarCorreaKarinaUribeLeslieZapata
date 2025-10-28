# 🔐 Guía de Uso del Sistema de Login

## Vista de Login Implementada

Se ha creado un sistema completo de autenticación con interfaz web para el Sistema de Gestión Clínica.

## 📁 Archivos Creados

```
src/main/resources/static/
├── index.html       → Redirige automáticamente al login
├── login.html       → Página de inicio de sesión
└── dashboard.html   → Panel principal según rol de usuario
```

## 🚀 Cómo Usar

### Paso 1: Iniciar la Aplicación

```bash
mvn spring-boot:run
```

### Paso 2: Acceder al Sistema

Abrir en el navegador: **http://localhost:8080**

Se redirigirá automáticamente a la página de login.

### Paso 3: Crear un Usuario (Primera vez)

Antes de poder hacer login, debes crear un empleado usando la API:

**Ejemplo con curl:**
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCompleto": "Juan Administrador",
    "cedula": "1234567890",
    "correoElectronico": "juan@clinica.com",
    "telefono": "3001234567",
    "fechaNacimiento": "1990-01-15",
    "direccion": "Calle 123 #45-67",
    "rol": "RECURSOS_HUMANOS",
    "nombreUsuario": "jadmin",
    "contrasena": "Admin123@"
  }'
```

**Ejemplo con Postman/Thunder Client:**
- URL: `POST http://localhost:8080/api/empleados`
- Headers: `Content-Type: application/json`
- Body: (JSON del ejemplo anterior)

### Paso 4: Iniciar Sesión

En la página de login (**http://localhost:8080/login.html**):

1. **Cédula**: `1234567890`
2. **Contraseña**: `Admin123@`
3. Click en **"Iniciar Sesión"**

## 🎯 Características del Sistema de Login

### ✅ Página de Login (`login.html`)

- **Diseño moderno y responsivo** con gradientes
- **Validación de campos** en tiempo real
- **Mensajes de error** descriptivos
- **Spinner de carga** durante autenticación
- **Validación de cédula** (solo números, máx 10 dígitos)
- **Protección contra acceso múltiple** (detecta sesión activa)
- **Información de prueba** visible para usuarios

### ✅ Dashboard (`dashboard.html`)

- **Interfaz personalizada por rol**
- **Navbar con información del usuario**
- **Tarjetas interactivas** según permisos
- **Botón de cerrar sesión**
- **Diseño responsivo**

### 🔒 Roles y Permisos

El dashboard muestra diferentes opciones según el rol:

#### 👥 Recursos Humanos
- Gestión de Empleados
- Reportes de Personal
- Gestión de Roles

#### 📋 Personal Administrativo
- Registrar Paciente
- Agendar Citas
- Facturación
- Ver Pacientes

#### 💻 Soporte de Información
- Inventario Medicamentos
- Procedimientos
- Ayudas Diagnósticas
- Especialidades

#### 👩‍⚕️ Enfermera
- Registro de Visitas
- Administrar Medicamentos
- Ver Pacientes

#### 👨‍⚕️ Médico
- Historia Clínica
- Crear Órdenes
- Ver Pacientes
- Recetar Medicamentos

## 📝 Crear Usuarios de Prueba para Cada Rol

### Recursos Humanos
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCompleto": "Ana Recursos",
    "cedula": "1111111111",
    "correoElectronico": "ana.rrhh@clinica.com",
    "telefono": "3001111111",
    "fechaNacimiento": "1985-03-15",
    "direccion": "Calle 10 #20-30",
    "rol": "RECURSOS_HUMANOS",
    "nombreUsuario": "arecursos",
    "contrasena": "SecurePass1@"
  }'
```

### Personal Administrativo
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCompleto": "Carlos Admin",
    "cedula": "2222222222",
    "correoElectronico": "carlos.admin@clinica.com",
    "telefono": "3002222222",
    "fechaNacimiento": "1988-06-20",
    "direccion": "Avenida 20 #30-40",
    "rol": "PERSONAL_ADMINISTRATIVO",
    "nombreUsuario": "cadmin",
    "contrasena": "SecurePass2@"
  }'
```

### Médico
```bash
curl -X POST http://localhost:8080/api/empleados \
  -H "Content-Type: application/json" \
  -d '{
    "nombreCompleto": "Dr. Pedro Médico",
    "cedula": "3333333333",
    "correoElectronico": "pedro.medico@clinica.com",
    "telefono": "3003333333",
    "fechaNacimiento": "1980-09-10",
    "direccion": "Carrera 30 #40-50",
    "rol": "MEDICO",
    "nombreUsuario": "pmedico",
    "contrasena": "SecurePass3@"
  }'
```

## 🔐 Seguridad Implementada

### Frontend
- ✅ Validación de campos obligatorios
- ✅ Formato de cédula (solo números)
- ✅ Almacenamiento seguro en localStorage
- ✅ Verificación de sesión activa
- ✅ Redirección automática si no está autenticado
- ✅ Cierre de sesión limpio

### Notas de Seguridad
⚠️ **Importante**: Esta es una implementación básica para desarrollo.

Para producción, se recomienda:
- Implementar JWT (JSON Web Tokens)
- Hash de contraseñas con BCrypt
- HTTPS obligatorio
- Spring Security con autenticación
- Tokens de refresh
- Rate limiting
- CSRF protection

## 🌐 URLs del Sistema

| URL | Descripción |
|-----|-------------|
| http://localhost:8080 | Redirige al login |
| http://localhost:8080/login.html | Página de login |
| http://localhost:8080/dashboard.html | Dashboard principal |
| http://localhost:8080/api/empleados | API REST de empleados |
| http://localhost:8080/h2-console | Consola de base de datos |

## 🧪 Probar el Sistema

1. **Ejecutar aplicación**: `mvn spring-boot:run`
2. **Abrir navegador**: http://localhost:8080
3. **Crear empleado** (vía API o Postman)
4. **Hacer login** con las credenciales
5. **Explorar dashboard** según el rol

## 💡 Tips

- Los datos de sesión se guardan en localStorage del navegador
- Para "cerrar sesión" hacer click en el botón o borrar localStorage
- El sistema detecta si ya hay sesión activa y redirige al dashboard
- Las validaciones de contraseña se hacen en el cliente (mejora pendiente: backend)
- Cada rol ve diferentes opciones en el dashboard

## 🎨 Personalización

Para personalizar los colores del gradiente, editar en `login.html`:

```css
background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
```

## ✨ Características Adicionales

- 📱 **Diseño Responsivo**: Funciona en móviles, tablets y desktop
- 🎨 **Interfaz Moderna**: Gradientes, sombras y animaciones
- ⚡ **Rápido**: Sin frameworks pesados, solo HTML/CSS/JS
- 🔄 **SPA-like**: Transiciones suaves entre páginas
- 🎯 **UX Mejorada**: Feedback visual en cada acción

## 🚀 Proyecto Completo

El sistema ahora incluye:
- ✅ Backend API REST (Spring Boot)
- ✅ Base de datos H2
- ✅ Frontend de Login
- ✅ Dashboard dinámico por rol
- ✅ Autenticación básica

¡Todo listo para usar! 🎉

