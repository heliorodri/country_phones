FROM gradle:7.3.1-jdk11
WORKDIR /app
ADD . .

USER root
RUN chown -R gradle /app
USER gradle

RUN ./gradlew clean build

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/build/libs/exercise-0.0.1-SNAPSHOT.jar"]