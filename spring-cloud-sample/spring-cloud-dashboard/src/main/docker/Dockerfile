FROM bjrdc-dev/java:8-jdk-bjrdc-v1.0.1
ARG JAR_FILE
RUN cat /root/hosts >> /etc/hosts
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]