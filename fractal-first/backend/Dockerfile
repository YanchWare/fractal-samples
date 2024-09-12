FROM eclipse-temurin:21-jre-alpine
COPY build/libs/backend.jar /app/
ENV AWS_REGION west-europe-3
ENTRYPOINT ["java","-jar", "/app/backend.jar"]
