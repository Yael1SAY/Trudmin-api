FROM openjdk:11
#RUN addgroup -S desarrollo && adduser -S say -G desarrollo
#USER say:desarrollo
ENV JAVA_OPTS=""
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} trudmin-api-0.0.1-SNAPSHOT.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]