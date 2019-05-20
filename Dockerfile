FROM maven:3.6.1-jdk-8-alpine AS build

RUN mkdir /app

WORKDIR /app

COPY . .

RUN mvn clean package

FROM openjdk:8-jdk-alpine as oraculo

RUN mkdir /app
WORKDIR /app

VOLUME /tmp
ARG JAR_FILE
COPY --from=build /app/target/Ruscigno.Guestlogix-0.0.1-SNAPSHOT.jar Ruscigno.Guestlogix-0.0.1-SNAPSHOT.jar

ENV TZ=America/Sao_Paulo
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/Ruscigno.Guestlogix-0.0.1-SNAPSHOT.jar"]