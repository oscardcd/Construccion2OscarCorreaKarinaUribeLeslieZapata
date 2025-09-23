#!/bin/bash

# Script para ejecutar la aplicación en modo web
# Inicia el servidor REST en el puerto 8080

echo "🚀 Iniciando Sistema de Gestión de Clínica - Modo Web"
echo "=================================================="

# Verificar que Java esté instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java no está instalado. Por favor instala Java 17 o superior."
    exit 1
fi

# Verificar que Maven esté instalado
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven no está instalado. Por favor instala Maven 3.6 o superior."
    exit 1
fi

# Compilar el proyecto
echo "📦 Compilando proyecto..."
mvn clean compile -q

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar el proyecto"
    exit 1
fi

echo "✅ Compilación exitosa"

# Ejecutar la aplicación en modo web
echo "🌐 Iniciando servidor web en puerto 8080..."
echo "📡 API disponible en: http://localhost:8080/api"
echo "🔧 H2 Console disponible en: http://localhost:8080/api/h2-console"
echo ""
echo "Presiona Ctrl+C para detener el servidor"
echo ""

mvn spring-boot:run -Dspring-boot.run.arguments="web"
