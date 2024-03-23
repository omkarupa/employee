FROM openjdk:17

WORKDIR /app

COPY target/employee.jar /app/employee.jar

EXPOSE 8096

CMD ["java","-jar","employee.jar"]