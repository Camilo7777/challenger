# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Define el directorio de trabajo en el contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación al directorio de trabajo en el contenedor
COPY target/challenger-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicación escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]