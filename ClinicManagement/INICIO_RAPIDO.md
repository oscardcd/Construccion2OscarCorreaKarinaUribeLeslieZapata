# 🚀 Inicio Rápido - Sistema de Gestión Clínica

## En 3 Pasos

### 1️⃣ Ejecutar Aplicación
```bash
mvn spring-boot:run
```

Espera a ver:
```
╔═══════════════════════════════════════════════════════╗
║   CLINIC MANAGEMENT SYSTEM - API REST                ║
║   Server running on: http://localhost:8080           ║
║   H2 Console: http://localhost:8080/h2-console       ║
╚═══════════════════════════════════════════════════════╝
```

### 2️⃣ Crear Usuario (Primera vez)

Abre otra terminal y ejecuta:

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

### 3️⃣ Acceder al Sistema

Abre tu navegador en: **http://localhost:8080**

**Credenciales:**
- Cédula: `1234567890`
- Contraseña: `Admin123@`

¡Listo! Ya puedes usar el sistema 🎉

---

## 📱 URLs Importantes

| URL | Descripción |
|-----|-------------|
| http://localhost:8080 | Página principal (login) |
| http://localhost:8080/dashboard.html | Dashboard después de login |
| http://localhost:8080/api | API REST base |
| http://localhost:8080/h2-console | Base de datos H2 |

---

## 🔧 Comandos Útiles

```bash
# Compilar
mvn clean compile

# Ejecutar
mvn spring-boot:run

# Empaquetar
mvn clean package

# Ver logs
tail -f logs/application.log
```

---

## 🎯 Próximos Pasos

1. ✅ **Explorar Dashboard** según tu rol
2. ✅ **Crear más empleados** con diferentes roles
3. ✅ **Registrar pacientes** (rol Administrativo)
4. ✅ **Gestionar inventario** (rol Soporte)
5. ✅ **Revisar API REST** en `README.md`

---

## 💡 Tips Rápidos

- **Olvidé mi contraseña**: No hay recuperación, crear nuevo usuario vía API
- **No puedo hacer login**: Verificar que el empleado existe en `/api/empleados`
- **Dashboard vacío**: Verificar que el rol esté asignado correctamente
- **Error 404**: Asegúrate que el servidor esté corriendo en puerto 8080

---

## 📚 Más Información

- `README.md` - Documentación completa
- `GUIA_LOGIN.md` - Sistema de login detallado
- `PROYECTO_COMPLETADO.md` - Endpoints y ejemplos

¿Necesitas ayuda? Revisa los archivos de documentación 📖

