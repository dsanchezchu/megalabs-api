# Usar una imagen base de OpenJDK
FROM openjdk:21-jdk-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del repositorio al contenedor
COPY . .

# Instalar Maven y compilar el proyecto
RUN apt-get update && apt-get install -y maven && \
    ./mvnw clean package -DskipTests



# Crear una nueva imagen con el archivo JAR compilado
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo para el contenedor
WORKDIR /app

# Copiar el archivo JAR de la etapa de compilación
COPY --from=build /app/target/*.jar app.jar
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto en el que la aplicación escuchará
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]