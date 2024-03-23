FROM openjdk:17

WORKDIR /app

COPY target/omkar/employee-v1.jar app/employee-v1.jar

EXPOSE 8096

CMD ["java","-jar","employee-v1.jar"]