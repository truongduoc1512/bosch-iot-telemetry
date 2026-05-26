# ==========================================
# STAGE 1: Biên dịch ứng dụng (Build Stage)
# ==========================================
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /builder
COPY . .
RUN ./mvnw clean package -DskipTests

# ==========================================
# STAGE 2: Môi trường chạy thật (Runtime Stage)
# ==========================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /builder/target/*.jar app.jar
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENTRYPOINT ["java", "-jar", "app.jar"]