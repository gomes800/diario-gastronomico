FROM openjdk:21-jdk-slim AS build

WORKDIR /app

COPY target/diario_gastronomico-0.0.1-SNAPSHOT.jar /app/diario-gastronomico.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "diario-gastronomico.jar"]
