FROM eclipse-temurin:17-jdk-alpine as builder

WORKDIR /application

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

# Use the generated jar directly (no COPY!)
RUN java -Djarmode=layertools -jar target/*.jar extract


FROM eclipse-temurin:17-jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

# docker build -t spring-empresa-api .
# docker run spring-empresa-api
