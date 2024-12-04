# Usar una imagen base de OpenJDK
FROM openjdk:21-jdk-slim AS build

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar los archivos del repositorio al contenedor
COPY . .

# Dar permisos de ejecución al archivo mvnw
RUN chmod +x ./mvnw

# Instalar Maven (si es necesario) y compilar el proyecto
RUN ./mvnw clean package -DskipTests

# Usar una imagen base de OpenJDK para la ejecución
FROM openjdk:21-jdk-slim

# Directorio de trabajo en el contenedor de ejecución
WORKDIR /app

# Copiar el archivo .jar desde la etapa de construcción
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto que usará la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]