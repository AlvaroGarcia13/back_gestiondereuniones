# Usa una imagen base con JDK
FROM eclipse-temurin:17-jdk

# Crea un directorio para la app
WORKDIR /app

# Copia el código fuente
COPY . .

# Empaqueta el proyecto con Maven (omitir tests)
RUN ./mvnw clean package -DskipTests

# Expone el puerto que usará Spring Boot
EXPOSE 8080

# Comando para ejecutar el jar
CMD ["java", "-jar", "target/gestiondeaplicaciones-0.0.1-SNAPSHOT.jar"]
