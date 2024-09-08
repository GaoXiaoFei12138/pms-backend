# 使用 OpenJDK 官方镜像
FROM openjdk:20-jdk-slim AS build
WORKDIR /app
COPY target/personnel-management-system-0.0.1-SNAPSHOT.jar app.jar

FROM mysql:8.0
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=123456
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=123456

EXPOSE 3306

FROM openjdk:20-jdk-slim
COPY --from=build /app/app.jar /app/app.jar
COPY --from=build /app/application.properties /app/application.properties

CMD ["java", "-jar", "/app/my-spring-boot-app.jar"]
