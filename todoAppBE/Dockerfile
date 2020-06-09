FROM openjdk:8-jdk-alpine
RUN apk add --update curl && rm -rf /var/cache/apk/*
EXPOSE 8090
ADD target/todoDemo.jar todoDemo.jar 
ENTRYPOINT ["java","-jar","/todoDemo.jar"]
