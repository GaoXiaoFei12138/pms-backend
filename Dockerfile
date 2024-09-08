# 使用 OpenJDK 官方镜像
FROM openjdk:20-jdk-slim
# 设置工作目录
WORKDIR /app

# 复制构建的 jar 文件
COPY target/personnel-management-system-0.0.1-SNAPSHOT.jar app.jar

# 启动服务
CMD ["java", "-jar", "app.jar"]