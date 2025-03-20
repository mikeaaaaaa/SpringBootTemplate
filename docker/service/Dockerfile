# 使用官方的 Java 运行时环境作为基础镜像
FROM openjdk:8-jdk-alpine

ARG SERVICE_PROJECT_NAME
ARG SERVICE_PROJECT_VERSION
ARG SERVICE_JAR_NAME=$SERVICE_PROJECT_NAME-$SERVICE_PROJECT_VERSION.jar

ENV SERVICE_JAR_NAME=$SERVICE_JAR_NAME

# 设置工作目录
WORKDIR /app

# 定义可以被挂载的卷
VOLUME /app/logs
VOLUME /app/config/application.properties

# 复制文件和目录到容器中
COPY target/$SERVICE_JAR_NAME /app/$SERVICE_JAR_NAME

# 指定容器启动时运行的命令
ENTRYPOINT ["sh", "-c", "\"$(java -jar $SERVICE_JAR_NAME)\""]