FROM adoptopenjdk:17-jre

WORKDIR /app

COPY target/employee-v1.jar /app/employee-v1.jar

EXPOSE 8096

CMD ["java","-jar","employee-v1.jar"]