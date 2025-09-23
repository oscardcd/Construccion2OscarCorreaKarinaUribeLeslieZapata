#!/bin/bash

# Script para iniciar la aplicación de consola
# Uso: ./start-console.sh

echo "🏥 Sistema de Gestión de Clínica - Aplicación de Consola"
echo "========================================================"

# Verificar que Java esté instalado
if ! command -v java &> /dev/null; then
    echo "❌ Java no está instalado. Por favor instala Java 17 o superior."
    exit 1
fi

echo "✅ Java encontrado"

# Limpiar y compilar el proyecto
echo "🔨 Compilando el proyecto..."
if command -v mvn &> /dev/null; then
    mvn clean compile
elif [ -f "./mvnw" ]; then
    ./mvnw clean compile
else
    echo "❌ Maven no encontrado. Por favor instala Maven o usa el wrapper."
    exit 1
fi

if [ $? -ne 0 ]; then
    echo "❌ Error al compilar el proyecto"
    exit 1
fi

echo "✅ Proyecto compilado exitosamente"

# Iniciar la aplicación de consola
echo "🚀 Iniciando aplicación de consola..."
echo "========================================================"

if command -v mvn &> /dev/null; then
    mvn spring-boot:run -Dspring-boot.run.profiles=dev -Dspring.main.web-application-type=none
elif [ -f "./mvnw" ]; then
    ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev -Dspring.main.web-application-type=none
fi
