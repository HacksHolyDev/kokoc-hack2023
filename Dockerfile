FROM eclipse-temurin:17-jdk as builder

ADD . /src

WORKDIR /src

RUN rm src/main/resources/application.properties && chmod +x mvnw


RUN --mount=type=cache,target=/root/.m2,rw ./mvnw -B package -DskipTests


FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /src/target/app.jar app.jar

#ADD seed.json $APP_HOME/seed.json

