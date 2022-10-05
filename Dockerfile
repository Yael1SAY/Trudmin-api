FROM maven:3.6.3-openjdk-11 AS MAVEN_BUILD
# Se crea la carpeta app
RUN mkdir /home/app
# Copia el pom.xml a la carpeta creada
COPY pom.xml /home/app
# Copia el la carpeta src dentro de la carpeta app/src
COPY src /home/app/src
WORKDIR /home/app/
# Compila el proyecto y crea el .jar
RUN mvn install -DskipTests

FROM openjdk:11
# Variables para la conexion a la base de datos, los datos se obtienen desde del archivo .yml
ARG MYAPP_JDBC_URL
ARG MYAPP_JDBC_USER
ARG MYAPP_JDBC_PASS

ENV JAVA_OPTS=""
ARG JAR_FILE
# ADD ${JAR_FILE} /usr/local/lib/app.jar
COPY --from=MAVEN_BUILD ${JAR_FILE} /usr/local/lib/app.jar

VOLUME /tmp
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /usr/local/lib/app.jar" ]