# Run spring boot in Docker
FROM openjdk:17-jdk-alpine
COPY ./target/*SNAPSHOT.jar /app.jar
ENV PORT 8080
EXPOSE $PORT
#VOLUME [ "${PWD}/log:/log" ]
WORKDIR /
ENTRYPOINT ["java","-jar","-Xmx1024M","-Dserver.port=${PORT}","app.jar"]