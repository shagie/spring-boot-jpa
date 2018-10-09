FROM gradle:jdk8 AS builder

COPY --chown=gradle:gradle build.gradle /home/gradle/build.gradle
COPY --chown=gradle:gradle settings.gradle /home/gradle/settings.gradle
WORKDIR /home/gradle
RUN gradle dependencies

COPY --chown=gradle:gradle src /home/gradle/src
RUN gradle build

FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY --from=builder /home/gradle/build/libs/shagie-sb-jpa-0.1.0.jar /app/
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/shagie-sb-jpa-0.1.0.jar"]
