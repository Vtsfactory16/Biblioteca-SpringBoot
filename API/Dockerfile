# Creamos un contenedor a partir de la imagen openjdk:17
FROM openjdk:17

# Creación de un volumen en el contenedor
VOLUME /tmp

# Añade archivo jar al contenedor con el nombre 'app.jar'
ADD target/BibliotecaSpringBoot-0.0.1-SNAPSHOT.jar app.jar

# Ejecuta un comando en el servidor.
# Ejecutando touch en un archivo que ya existe, cambiamos su marca de tiempo al momento actual
RUN sh -c 'touch /app.jar'

# Puerto usado por el contenedor. Espera que esté disponible.
EXPOSE 8080

# Comando ejecutado al iniciarse el contenedor
# Ejecuta el jar con el comando 'java -jar'
# La opción '-Djava.security...' mejora la generación de números aleatorios ¿Para qué queremos eso?
ENTRYPOINT ["java", "-Djava.security.egd=file:dev/./urandom", "-jar", "/app.jar"]